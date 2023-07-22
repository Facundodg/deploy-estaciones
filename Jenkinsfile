#!groovy

pipeline {

    agent any
    tools {
        jdk 'Java 20'
        dockerTool 'Docker latest'
        maven 'Maven 3.9.3'
    }

    environment {
        HORA_DESPLIEGUE = sh(returnStdout: true, script: "date '+%A %W %Y %X'").trim()
        NOMBRE_APLICACION = "gestor-estaciones"

        GITHUB_CREDENCIALES = "github"
        GITHUB_URL = "https://github.com/dim-desarrollo/${NOMBRE_APLICACION}"
        GITHUB_RAMA = "*/master"

    }

    stages {
        stage('Tools initialization') {
            steps{
                script{
                    env.DOCKER_VERSION = sh(returnStdout: true, script: 'docker version')
                    env.JAVA_VERSION = sh(returnStdout: true, script: 'java -version')
                    env.MAVEN_VERSION = sh(returnStdout: true, script: 'mvn -v')
                    env.PROYECTO_VERSION = sh(returnStdout: true, script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout')
                    env.ARTIFACT_ID = sh(script: "mvn help:evaluate -Dexpression=project.artifactId -f pom.xml -q -DforceStdout", returnStdout: true).trim()

                    sh "echo 'Hora despliegue: ${env.HORA_DESPLIEGUE}'"
                    sh "echo 'Docker version: ${env.DOCKER_VERSION}'"
                    sh "echo 'Java version: ${env.JAVA_VERSION}'"
                    sh "echo 'Maven version:  ${env.MAVEN_VERSION}'"
                    sh "echo 'Maven Artifact ID: ${env.ARTIFACT_ID}'"
                }
            }
        }

        stage('Build Maven') {
            steps {
                script{

                    checkout scmGit(branches: [[name: "${env.GITHUB_RAMA}"]], extensions: [], userRemoteConfigs: [[credentialsId: "${env.GITHUB_CREDENCIALES}", url: "${env.GITHUB_URL}"]])
                    sh 'mvn clean package install -DskipTests'
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

                        // Obtiene artifact_id del proyecto
                        echo "Proyecto: ${env.ARTIFACT_ID}"

                        // Construye la imagen de Docker usando el nombre y la versión obtenidos
                        sh "docker build -t ${env.ARTIFACT_ID}:${env.PROYECTO_VERSION} ."

                        withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                            sh 'echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin' 
                            sh "docker push $DOCKERHUB_USERNAME/${env.ARTIFACT_ID}:${env.PROYECTO_VERSION}"
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

        stage('Deploy Kubernetes0') {
            steps{
                script{
                    echo "TODO - KUBERNETES"
                }
            }
        }
    }

    post{
        always{
            sh 'docker logout'
        }
    }
}