#!groovy
// -noinspection GroovyAssignabilityCheck

def ARTIFACT_ID
def IDENTIFICADOR_PROYECTO

pipeline {
    agent any
    tools {
        jdk 'Java 20'
        dockerTool 'Docker 24.0.2'
        maven 'Maven 3.9.3'
    }

    environment {
        HORA_DESPLIEGUE = sh(returnStdout: true, script: "date '+%A %W %Y %X'").trim()

        GITHUB_MONOLITO_URL = "https://github.com/dim-desarrollo/gestor-estaciones"
        GITHUB_MONOLITO_RAMA = "master"

        GITHUB_DESPLIEGUE_RAMA = "master"
        GITHUB_DESPLIEGUE_URL = "https://github.com/dim-desarrollo/gestor-estaciones-despliegue"

        GITHUB_CREDENCIALES = "github"
        DOCKERHUB_CREDENCIALES = "dockerhub"
    }

    stages {
        stage ('Cleanup Workspace'){
            steps{
            //    cleanWs();
                echo 'TODO - Clean workspace'
            }
        }

        stage('Tools initialization') {
            steps{
                script{
                    DOCKER_VERSION = sh(returnStdout: true, script: 'docker version')
                    JAVA_VERSION = sh(returnStdout: true, script: 'java -version')
                    MAVEN_VERSION = sh(returnStdout: true, script: 'mvn -v')
                    PROYECTO_VERSION = sh(returnStdout: true, script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout')

                    ARTIFACT_ID = sh(script: "mvn help:evaluate -Dexpression=project.artifactId -f pom.xml -q -DforceStdout", returnStdout: true).trim()
                    IDENTIFICADOR_PROYECTO = "${ARTIFACT_ID}:${PROYECTO_VERSION}"

                    sh "echo 'Hora despliegue: ${HORA_DESPLIEGUE}'"
                    sh "echo 'Versión Proyecto: ${PROYECTO_VERSION}'"
                    sh "echo 'Docker version: ${DOCKER_VERSION}'"
                    sh "echo 'Java version: ${JAVA_VERSION}'"
                    sh "echo 'Maven version:  ${MAVEN_VERSION}'"
                    sh "echo 'Maven Artifact ID: ${ARTIFACT_ID}'"
                }
            }
        }

        stage('Build Maven') {
            steps {
                script{
                    dir('monolito'){
                        git credentialsId: "${GITHUB_CREDENCIALES}", url: "${GITHUB_MONOLITO_URL}", branch: "${GITHUB_MONOLITO_RAMA}"
                        // checkout scmGit(branches: [[name: "${GITHUB_MONOLITO_RAMA}"]], extensions: [], userRemoteConfigs: [[credentialsId: "${GITHUB_CREDENCIALES}", url: "${GITHUB_MONOLITO_URL}"]]) 
                        sh 'mvn clean package install -DskipTests'
                    }
                }
            }
        }

        stage ('SonarQube Analysis'){
            environment{
                SONAR_SCANNER_HOME = tool 'SonarQube 4.8.0'
                SONAR_SERVER = 'SonarQube'
                SONAR_HOST_IP = '172.25.0.3'
                SONAR_PORT = '9000'
            }

            steps{
                withSonarQubeEnv(installationName: "${SONAR_SERVER}", credentialsId: 'sonarqube'){
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

        stage('SonarQube Quality Gate'){
            steps{
                timeout(time: 1, unit: 'HOURS'){
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
                            DOCKER_TAG = "${IDENTIFICADOR_PROYECTO}.${BUILD_NUMBER}"
                            sh "docker build -t \$DOCKERHUB_USERNAME/${DOCKER_TAG} ."

                            // Sube la imagen a DockerHub
                            sh 'echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin' 
                            sh "docker push \$DOCKERHUB_USERNAME/${DOCKER_TAG}"
                        }
                    }
                }
            }

        stage('Test') {
            steps{
                script{
                    echo "TODO - TEST"
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps{
                script{
                    sh 'cd ..'
                    sh 'pwd' // TODO: Borrar

                    dir('despliegue'){
                        git credentialsId: "${GITHUB_CREDENCIALES}", url: "${GITHUB_DESPLIEGUE_URL}", branch: "${GITHUB_DESPLIEGUE_RAMA}"

                        withCredentials([string(credentialsId: 'k8s-cluster-config', variable: 'KUBE_CONFIG')]){
                            // sh 'kubectl --kubeconfig=$KUBE_CONFIG apply -f ./dev/basedatos'
                            // sh 'kubectl --kubeconfig=$KUBE_CONFIG apply -f ./dev/general'
                        }
                    }
                }
            }
        }
    }
}

post{
    always {
        // Desconexión de h 'docker logout'
        sh 'docker logout'

        def mensaje_slack = "Mensaje de Slack"
        slackSend(channel: '#your-slack-channel', message: "${mensaje_slack}")

        emailext (
                to: 'octallanillo@gmail.com',
                subject: "[BuildResult][${currentBuild.currentResult}] - Job '${env.JOB_NAME}' (${BUILD_NUMBER})",
                body: '''${SCRIPT, template="email.groovy.template"}''',
                attachLog: true
            )
    }

    success {
    }

    failure {
    }

}