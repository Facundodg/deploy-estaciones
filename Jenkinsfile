pipeline {
agent any 

   tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "maven3.8.8"
        jdk 'java20'
        dockerTool 'docker-2'
    }

    environment {

        HORA_DESPLIEGUE = sh(returnStdout: true, script: "date '+%A %W %Y %X'").trim()

        GITHUB_MONOLITO_URL = "https://github.com/Facundodg/deploy-estaciones.git"

        GITHUB_CREDENCIALES = "github-test-1"
        DOCKERHUB_CREDENCIALES = "dockerhub"
        KUBERNETES_CREDENCIALES = "k8s-jenkins-account-15"
        SONARQUBE_CREDENCIALES = 'sonarqube'

        IDENTIFICADOR_UNICO_BUILD = 'gestor-estaciones-monolito' //agregado a mano, ver si se hace de otra manera

        CANAL_SLACK = "#canal-slack"            // TODO: Por reemplazar
        CORREO_A_NOTIFICAR = "dim@gmail.com"    // TODO: Por reemplazar

        CARPETA_APLICACION = './'

    }


   stages {

      // stage('Tools initialization') {
      //     steps {
      //         script {

                  // DOCKER_VERSION = sh(returnStdout: true, script: 'docker version')
                  // JAVA_VERSION = sh(returnStdout: true, script: 'java -version')
                  // MAVEN_VERSION = sh(returnStdout: true, script: 'mvn -v')
                  // NODEJS_VERSION = sh(returnStdout: true, script: 'npm -v')

                  // sh "echo 'Hora despliegue: ${HORA_DESPLIEGUE}'"
                  // sh "echo 'Docker version: ${DOCKER_VERSION}'"
                  // sh "echo 'Java version: ${JAVA_VERSION}'"
                  // sh "echo 'Maven version:  ${MAVEN_VERSION}'"
                  // sh "echo 'Rama a clonar:  ${RAMA_PARA_CLONAR}'"
      //         }
      //     }
      // }

      stage('Tools initialization') {
          steps {
              script {
                  DOCKER_VERSION = sh(returnStdout: true, script: 'docker version').trim()
                  JAVA_VERSION = sh(returnStdout: true, script: 'java -version').trim()
                  MAVEN_VERSION = sh(returnStdout: true, script: 'mvn -v').trim()
                  NODEJS_VERSION = sh(returnStdout: true, script: 'npm -v').trim()

                  echo "Docker version: ${DOCKER_VERSION}"
                  echo "Java version: ${JAVA_VERSION}"
                  echo "Maven version: ${MAVEN_VERSION}"
                  echo "Node.js version: ${NODEJS_VERSION}"
              }
          }
      }

      
      stage('Build Package') {  
               
          steps{

            sh "echo 'Hora despliegue: ${HORA_DESPLIEGUE}'"
            sh "echo 'Docker version: ${DOCKER_VERSION}'"
            sh "echo 'Java version: ${JAVA_VERSION}'"
            sh "echo 'Maven version:  ${MAVEN_VERSION}'"
            sh "echo 'Rama a clonar:  ${RAMA_PARA_CLONAR}'"

            sh "echo prueba"
            sh "mvn -version"
            sh "mvn clean package -DskipTests"

            }
         
      }

     stage('Build and push to DockerHub') {
          
        steps {
            script {

                dir ("${CARPETA_APLICACION}"){
                    // Verifica si existe un archivo Dockerfile en la subcarpeta actual
                    if (!fileExists("Dockerfile")) {
                        error "Dockerfile not found"
                    }

                    withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDENCIALES}", usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {

                        // Construye la imagen de Docker usando el nombre y la versión obtenidos
                        sh "docker build -t \$DOCKERHUB_USERNAME/${IDENTIFICADOR_UNICO_BUILD} ."

                        // Sube la imagen a DockerHub
                        sh 'echo $DOCKERHUB_USERNAME'
                        sh 'echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin'
                        sh "docker push \$DOCKERHUB_USERNAME/${IDENTIFICADOR_UNICO_BUILD}"
                    }
                }

            }
        }
          
     }


 }
}
