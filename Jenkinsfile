#!groovy
// -noinspection GroovyAssignabilityCheck

/*
    CONSIDERACIONES
    * Se debe configurar como multibranch pipeline
    * Deben existir las ramas master y develop
 */

def ARTIFACT_ID
def IDENTIFICADOR_PROYECTO
def DOCKER_TAG

pipeline {
    agent any

    options {
        skipDefaultCheckout true
    }

    tools {
        jdk 'Java 20'
        dockerTool 'Docker 24.0.2'
        maven 'Maven 3.9.3'
    }

    environment {
        HORA_DESPLIEGUE = sh(returnStdout: true, script: "date '+%A %W %Y %X'").trim()

        GITHUB_DESPLIEGUE_URL = "https://github.com/dim-desarrollo/gestor-estaciones-despliegue"
        GITHUB_MONOLITO_URL = "https://github.com/dim-desarrollo/gestor-estaciones"

        GITHUB_CREDENCIALES = "github"
        DOCKERHUB_CREDENCIALES = "dockerhub"
        KUBERNETES_CREDENCIALES = "k8s-jenkins-account"
    }

    stages {
        stage('Tools initialization') {
            steps {
                script {
                    DOCKER_VERSION = sh(returnStdout: true, script: 'docker version')
                    JAVA_VERSION = sh(returnStdout: true, script: 'java -version')
                    MAVEN_VERSION = sh(returnStdout: true, script: 'mvn -v')
                    PROYECTO_VERSION = sh(returnStdout: true, script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout')

                    ARTIFACT_ID = sh(script: "mvn help:evaluate -Dexpression=project.artifactId -f pom.xml -q -DforceStdout", returnStdout: true).trim()
                    IDENTIFICADOR_PROYECTO = "${ARTIFACT_ID}:${PROYECTO_VERSION}"
                    DOCKER_TAG = "${IDENTIFICADOR_PROYECTO}.${BUILD_NUMBER}"

                    sh "echo 'Hora despliegue: ${HORA_DESPLIEGUE}'"
                    sh "echo 'Versión Proyecto: ${PROYECTO_VERSION}'"
                    sh "echo 'Docker version: ${DOCKER_VERSION}'"
                    sh "echo 'Java version: ${JAVA_VERSION}'"
                    sh "echo 'Maven version:  ${MAVEN_VERSION}'"
                }
            }
        }

        stage('Git checkout') {
            script {
                checkout scmGit(branches: [[name: "${BRANCH_NAME}"]], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'monolito']], userRemoteConfigs: [[credentialsId: "${GITHUB_CREDENCIALES}", url: "${GITHUB_MONOLITO_URL}"]])
            }
        }

        stage('Build Maven') {
            steps {
                script {
                    sh 'mvn clean package install -DskipTests'
                }
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
                sh 'mvn integration-test'
            }
        }

        stage('SonarQube Analysis') {
            when {
                branch 'develop'
            }

            environment {
                SONAR_SCANNER_HOME = tool 'SonarQube 4.8.0'
                SONAR_SERVER = 'SonarQube'
                SONAR_HOST_IP = '172.25.0.3'        // IP interna de Docker
                SONAR_PORT = '9000'
            }

            steps {
                withSonarQubeEnv(installationName: "${SONAR_SERVER}", credentialsId: 'sonarqube') {
                    sh "${SONAR_SCANNER_HOME}/bin/sonar-scanner \
                        -Dsonar.projectName=${ARTIFACT_ID} \
                        -Dsonar.projectVersion=${PROYECTO_VERSION} \
                        -Dsonar.host.url=http://${SONAR_HOST_IP}:${SONAR_PORT} \
                        -Dsonar.sources=src/ \
                        -Dsonar.projectKey=${IDENTIFICADOR_PROYECTO} \
                        -Dsonar.language=java \
                        -Dsonar.java.binaries=. \
                        -Dsonar.sourceEncoding=UTF-8"
                }
            }
        }

        stage('SonarQube Quality Gate') {
            when {
                branch 'develop'
            }

            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build and push to DockerHub') {
            steps {
                script {
                    // Verifica si existe un archivo Dockerfile en la subcarpeta actual
                    if (!fileExists("Dockerfile")) {
                        error "Dockerfile not found"
                    }

                    withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDENCIALES}", usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {

                        // Construye la imagen de Docker usando el nombre y la versión obtenidos
                        sh "docker build -t \$DOCKERHUB_USERNAME/${DOCKER_TAG} ."

                        // Sube la imagen a DockerHub
                        sh 'echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin'
                        sh "docker push \$DOCKERHUB_USERNAME/${DOCKER_TAG}"
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            when {
                anyOf {
                    branch 'master'
                    branch 'develop'
                }
            }

            steps {
                environment {
                    CARPETA_DESPLIEGUE = BRANCH_NAME == 'master' ? 'prod' : 'dev'
                    KUBE_SERVIDOR = ""
                }

                script {
                    sh 'cd ..'
                    sh 'pwd' // TODO: Borrar

                    dir('despliegue') {
                        checkout scmGit(branches: [[name: "${BRANCH_NAME}"]], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'despliegue']], userRemoteConfigs: [[credentialsId: "${GITHUB_CREDENCIALES}", url: "${GITHUB_DESPLIEGUE_URL}"]])

                        withCredentials([string(credentialsId: "${KUBERNETES_CREDENCIALES}", variable: 'KUBE_TOKEN')]) {
                            // sh "kubectl --kubeconfig=$KUBE_CONFIG apply -f ${FOLDER}"
//                            sh 'kubectl --token $KUBE_TOKEN --server ${SEVER} --insecure-skip-lts-verify=true apply -f ${FOLDER}'
                            sh "kubectl --token \$KUBE_TOKEN --server ${KUBE_SERVIDOR} apply -f ${CARPETA_DESPLIEGUE}/"
                        }
                    }
                }
            }
        }
    }
}

post {
    always {
        // Desconexión de Docker
        sh 'docker logout'

        // Mensajes para Slack
        def mensaje_slack = "Mensaje de Slack"
        slackSend(channel: '#your-slack-channel', message: "${mensaje_slack}")

        // Mensaje por correo
        emailext(
                to: 'octallanillo@gmail.com',
                subject: "[BuildResult][${currentBuild.currentResult}] - Job '${env.JOB_NAME}' (${BUILD_NUMBER})",
                body: '''${SCRIPT, template="email.groovy.template"}''',
                attachLog: true
        )

        // Almacena test de Maven
        script{
            junit 'target/surefire-reports/*.xml'
        }
    }

    success {
    }

    failure {
        cleanWs()
    }

}