#!groovy

pipeline {

    agent any
    tools {
        jdk 'Java 20'
        dockerTool 'Docker 24.0.2'
        maven 'Maven 3.9.3'
    }

    environment {
        SONAR_SCANNER_HOME = tool 'SonarQube 4.8.0'

        HORA_DESPLIEGUE = sh(returnStdout: true, script: "date '+%A %W %Y %X'").trim()
        NOMBRE_PROYECTO_MONOLITO = "gestor-estaciones"
        NOMBRE_PROYECTO_DESPLIEGUE= "${NOMBRE_PROYECTO_MONOLITO}-despliegue"

        GITHUB_MONOLITO_URL = "https://github.com/dim-desarrollo/${NOMBRE_PROYECTO_MONOLITO}"
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
                    git credentialsId: "${GITHUB_CREDENCIALES}", url: "${GITHUB_MONOLITO_URL}", branch: "${GITHUB_MONOLITO_RAMA}", directory: "${NOMBRE_PROYECTO_MONOLITO}"
                    // checkout scmGit(branches: [[name: "${GITHUB_MONOLITO_RAMA}"]], extensions: [], userRemoteConfigs: [[credentialsId: "${GITHUB_CREDENCIALES}", url: "${GITHUB_MONOLITO_URL}"]]) 
                    sh 'mvn clean package install -DskipTests'
                }
            }
        }

        stage ('SonarQube Analysis'){
            steps{

            sh "echo 'SonarQube'"
                // withSonarQube'SonarQube') {
                //     sh "${SONAR_SCANNER_HOME}/bin/sonar-scanner \
                //         -Dsonar.projectKey=your_project_key \
                //         -Dsonar.projectName=${NOMBRE_PROYECTO_MONOLITO} \
                //         -Dsonar.projectVersion=${PROYECTO_VERSION} \
                //         -Dsonar.host.url=http://localhost:9000 \
                //         -Dsonar.login=your_sonarqube_token"
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
                            // DOCKER_TAG_COMPLETO = "${ARTIFACT_ID}:${PROYECTO_VERSION}.${BUILD_NUMBER}"
                            DOCKER_TAG_COMPLETO = "${ARTIFACT_ID}:${PROYECTO_VERSION}"
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
                    git credentialsId: "${GITHUB_CREDENCIALES}", url: "${}", branch: "${GITHUB_DESPLIEGUE_RAMA}", directory: "${NOMBRE_PROYECTO_DESPLIEGUE}"

                    sh 'pwd' // TODO: Borrar

                    withCredentials([string(credentialsId: 'k8s-cluster-config', variable: 'KUBE_CONFIG')]){
                        // sh 'kubectl --kubeconfig=$KUBE_CONFIG apply -f ./dev/basedatos'
                        // sh 'kubectl --kubeconfig=$KUBE_CONFIG apply -f ./dev/general'
                    }
                }
            }
        }
    }
}

post{
    always{
        sh 'docker logout'
    }

    success {
        emailext (
                to: 'octallanillo@gmail.com',
                subject: "[BuildResult][${currentBuild.currentResult}] - Job '${JOB_NAME}' (${BUILD_NUMBER})",
                body: '''${SCRIPT, template="email.groovy.template"}''',
                attachLog: true
        )
    }

    failure {
        emailext (
                to: 'octallanillo@gmail.com',
                subject: "[BuildResult][${currentBuild.currentResult}] - Job '${JOB_NAME}' (${BUILD_NUMBER})",
                body: '''${SCRIPT, template="email.groovy.template"}''',
                attachLog: true
        )
    }

}