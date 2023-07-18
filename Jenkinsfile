#!groovy

def PROYECTO_VERSION = ""

pipeline {

    agent any
    tools {
        jdk 'Java 20'
        dockerTool 'Docker latest'
        maven 'Maven 3.9.3'
    }

    environment {
        HORA_DESPLIEGUE = sh(returnStdout: true, script: "date '+%A %W %Y %X'").trim()
        // DOCKER_VERSION = sh(returnStdout: true, script: 'docker version')
        // MAVEN_VERSION = sh(returnStdout: true, script: 'mvn -v')
        // JAVA_VERSION = sh(returnStdout: true, script: 'java -version')
    }

    stages {
        stage('Tools initialization') {
            steps{
                sh "echo 'HORA DESPLIEGUE: ${HORA_DESPLIEGUE}'"
                // sh "echo ${DOCKER_VERSION}"
                // sh "echo ${MAVEN_VERSION}"
                // sh "echo ${JAVA_VERSION}"
            }
        }

        stage('Build Maven') {
            steps {
                // checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'dim-github', url: 'https://github.com/dim-desarrollo/gestor-estaciones']])
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'github', url: 'https://github.com/dim-desarrollo/gestor-estaciones']])
                sh 'mvn clean package install -DskipTests'
                PROYECTO_VERSION = sh(returnStdout: true, script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout')
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                        // Verifica si existe un archivo Dockerfile en la subcarpeta actual
                        if (fileExists("Dockerfile")) {

                            // Obtiene artifact_id del proyecto
                            def artifact_id = sh(script: "mvn help:evaluate -Dexpression=project.artifactId -f pom.xml -q -DforceStdout", returnStdout: true).trim()
                            echo "Proyecto: ${artifact_id}"

                            // Construye la imagen de Docker usando el nombre y la versión obtenidos
                            sh "docker build -t ${artifact_id}:${PROYECTO_VERSION} ."
                        }
                        else{
                            error "Dockerfile not found"
                        }
                    }
                }
            }

        // stage('Test') {
        //     steps{
        //         // TODO
        //     }
        // }

        // stage('Deploy Kubernetes0') {
        //     steps{
        //         // TODO
        //     }
        // }
    }
}