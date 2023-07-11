#!groovy

pipeline {
    agent any
    tools {
        jdk 'Java 11'
        maven 'Maven 3.6.3'
        docker 'Docker'

        environment {
            HORA_DESPLIEGUE = sh(returnStdout: true, script: "date '+%A %W %Y %X'").trim()
            PROYECTO_VERSION = sh(returnStdout: true, script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout')
            DOCKER_VERSION = sh(returnStdout: true, script: 'docker version')
            MAVEN_VERSION = sh(returnStdout: true, script: 'mvn --version')
            JAVA_VERSION = sh(returnStdout: true, script: 'java -version')
        }
    }
    stages {
        stage('Tools initialization') {
            sh $ { env.DOCKER_VERSION }
            sh $ { env.MAVEN_VERSION }
            sh $ { env.JAVA_VERSION }
        }

        stage('Build Maven') {
            steps {
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'dim-github', url: 'https://github.com/dim-desarrollo/gestor-estaciones']])
                // sh 'mvn clean package install -X -DskipTests'
                sh 'mvn clean package install -DskipTests'
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                        // Verifica si existe un archivo Dockerfile en la subcarpeta actual
                        if (fileExists("Dockerfile")) {

                            // Obtiene artifactId de los pom hijos
                            def artifactId = sh(script: "mvn help:evaluate -Dexpression=project.artifactId -f pom.xml -q -DforceStdout", returnStdout: true).trim()
                            echo "Proyecto hijo: ${artifactId}"

                            // Construye la imagen de Docker usando el nombre y la versión obtenidos
                            sh "docker build -t ${artifactId}:${PROYECTO_VERSION} ${subdirectorio}"
                        }
                    }
                }
            }
        }

        stage('Test') {

        }

        stage('Deploy Kubernetes0') {

        }
    }

}
