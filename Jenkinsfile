#!groovy

pipeline {
    agent any
    tools {
        jdk 'Java 11'
        maven 'Maven 3.6.3'
        docker 'Docker'
    }
    stages {
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
                    // Comprueba versión de Docker
                    def dockerVersion = sh script: 'docker version'
                    echo "Versión Docker: ${dockerVersion}"

                    // Obtiene la lista de subcarpetas en el directorio actual
                    def subdirectorios = sh(returnStdout: true, script: 'ls -d */').trim().split('\n')

                    // Comprueba versión del proyecto
                    def versionProyecto = sh script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true
                    echo "Versión de proyecto: ${versionProyecto}"

                    // Iterar sobre cada subcarpeta
                    for (subdirectorio in subdirectorios) {

                        // Muestra subdirectorio
                        echo "Subdirectorio: ${subdirectorio}"

                        // Verifica si existe un archivo Dockerfile en la subcarpeta actual
                        if (fileExists("${subdirectorio}Dockerfile")) {
//                            def artifactId = sh script: 'mvn help:evaluate -Dexpression=project.artifactId -Dchild=${subdirectorio} -q -DforceStdout', returnStdout: true

                            // Obtiene artifactId de los pom hijos
                            def artifactId = sh(script: "mvn help:evaluate -Dexpression=project.artifactId -f ${subdirectorio}/pom.xml -q -DforceStdout", returnStdout: true).trim()
                            echo "Proyecto hijo: ${artifactId}"


                            // Construye la imagen de Docker usando el nombre y la versión obtenidos
//                            docker.build("${artifactId}:${versionProyecto}", "${subdirectorio}")
                            sh "docker build -t ${artifactId}:${versionProyecto} ${subdirectorio}"
                        }
                    }
                }
            }
        }

    }
}
