#!groovy
// -noinspection GroovyAssignabilityCheck
def ARTIFACT_ID

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

        GITHUB_DESPLIEGUE_URL = "master"
        GITHUB_DESPLIEGUE_RAMA = "https://github.com/dim-desarrollo/gestor-estaciones-despliegue"

        GITHUB_CREDENCIALES = "github"
        DOCKERHUB_CREDENCIALES = "dockerhub"
    }

    stages {
        stage('Tools initialization') {
            steps{
                script{
                    DOCKER_VERSION = sh(returnStdout: true, script: 'docker version')
                    JAVA_VERSION = sh(returnStdout: true, script: 'java -version')
                    MAVEN_VERSION = sh(returnStdout: true, script: 'mvn -v')
                    PROYECTO_VERSION = sh(returnStdout: true, script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout')

                    ARTIFACT_ID = sh(script: "mvn help:evaluate -Dexpression=project.artifactId -f pom.xml -q -DforceStdout", returnStdout: true).trim()

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
                    dir("${ARTIFACT_ID}-monolito"){
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
            }
            steps{
            sh "echo 'TODO - SonarQube, Server: ${SONAR_SERVER}'"
                withSonarQubeEnv("${SONAR_SERVER}") {
                    // sh "${SONAR_SCANNER_HOME}/bin/sonar-scanner \
                    //     -Dsonar.projectName=${NOMBRE_PROYECTO_MONOLITO} \
                    //     -Dsonar.projectVersion=${PROYECTO_VERSION} \
                    //     -Dsonar.host.url=http://localhost:9000 \
                    //     -Dsonar.sources=src/ "
                        // -Dsonar.login=your_sonarqube_token"
                }
                // }
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
                            DOCKER_TAG_COMPLETO = "${ARTIFACT_ID}:${PROYECTO_VERSION}.${BUILD_NUMBER}"
                            sh "docker build -t \$DOCKERHUB_USERNAME/${DOCKER_TAG_COMPLETO} ."

                            // Sube la imagen a DockerHub
                            sh 'echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin' 
                            sh "docker push \$DOCKERHUB_USERNAME/${DOCKER_TAG_COMPLETO}"
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

                    dir("${ARTIFACT_ID}-despliegue"){
                        git credentialsId: "${GITHUB_CREDENCIALES}", url: "${}", branch: "${GITHUB_DESPLIEGUE_RAMA}"

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