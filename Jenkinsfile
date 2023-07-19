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

        PROYECTO_VERSION = "0.0.1"
        DOCKER_VERSION = "latest"
        MAVEN_VERSION = "3.9.3"
        JAVA_VERSION = "20"
        
        GITHUB_CREDENCIALES = "github"
        GITHUB_URL = "https://github.com/dim-desarrollo/gestor-estaciones"
        GITHUB_RAMA = "*/master"
    }

    stages {
        stage('Tools initialization') {
            steps{
                script{
                    sh "echo 'Hora despliegue: ${HORA_DESPLIEGUE}'"
                    // sh "echo ${DOCKER_VERSION}"
                    // sh "echo ${MAVEN_VERSION}"
                    // sh "echo ${JAVA_VERSION}"

                    env.DOCKER_VERSION = sh(returnStdout: true, script: 'docker version')
                    env.JAVA_VERSION = sh(returnStdout: true, script: 'java -version')
                    env.MAVEN_VERSION = sh(returnStdout: true, script: 'mvn -v')
                    env.PROYECTO_VERSION = sh(returnStdout: true, script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout')
                }
            }
        }

        stage('Build Maven') {
            steps {
                script{

                    // checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'dim-github', url: 'https://github.com/dim-desarrollo/gestor-estaciones']])
                    checkout scmGit(branches: [[name: "${env.GITHUB_RAMA}"]], extensions: [], userRemoteConfigs: [[credentialsId: "${env.GITHUB_CREDENCIALES}", url: "${env.GITHUB_URL}"]])
                    sh 'mvn clean package install -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                        // Verifica si existe un archivo Dockerfile en la subcarpeta actual
                        if (!fileExists("Dockerfile")) {
                            error "Dockerfile not found"
                        }

                        // Obtiene artifact_id del proyecto
                        def ARTIFACT_ID = sh(script: "mvn help:evaluate -Dexpression=project.artifactId -f pom.xml -q -DforceStdout", returnStdout: true).trim()
                        echo "Proyecto: ${ARTIFACT_ID}"

                        // Construye la imagen de Docker usando el nombre y la versión obtenidos
                        sh "docker build -t ${artifact_id}:${env.PROYECTO_VERSION} ."
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
}