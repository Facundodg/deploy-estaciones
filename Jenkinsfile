def ARTIFACT_ID
def IDENTIFICADOR_PROYECTO
def IDENTIFICADOR_UNICO_BUILD
def RAMA_PARA_CLONAR

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

    stage('Iniciando variables') {

        steps {

            dir("${CARPETA_APLICACION}"){

                script {

                    PROYECTO_VERSION = sh(returnStdout: true, script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout')
                    ARTIFACT_ID = sh(script: "mvn help:evaluate -Dexpression=project.artifactId -f pom.xml -q -DforceStdout", returnStdout: true).trim()
                    IDENTIFICADOR_PROYECTO = "${ARTIFACT_ID}:${PROYECTO_VERSION}"
                    IDENTIFICADOR_UNICO_BUILD = "${IDENTIFICADOR_PROYECTO}.${BUILD_NUMBER}"

                }

            }


        }

    }

    stage('SonarQube Analysis') {

    environment {

        SONAR_SCANNER_HOME = tool 'sonarScaner' //nombre en la configuracion de las tools de jenkins 
        SONAR_SERVER = 'sonarqube' 
        SONAR_HOST_IP = '172.17.0.4' // IP interna de Docker de SonarQube, debido a que SonarQube corre en un contenedor (docker inspect nombre_contenedo_SonarQube)
        SONAR_PORT = '9000' //puerto donde esta trabajando el contenedor
        SONAR_SRC = 'src/'
        SONAR_ENCODING = 'UTF-8'

    }


    steps {
            dir("${CARPETA_APLICACION}"){
                withSonarQubeEnv(installationName: "${SONAR_SERVER}", credentialsId: "${SONARQUBE_CREDENCIALES}") {
                    sh "${SONAR_SCANNER_HOME}/bin/sonar-scanner \
                        -Dsonar.projectName=${ARTIFACT_ID} \
                        -Dsonar.projectVersion=${PROYECTO_VERSION} \
                        -Dsonar.projectKey=${IDENTIFICADOR_PROYECTO} \
                        -Dsonar.host.url=http://${SONAR_HOST_IP}:${SONAR_PORT} \
                        -Dsonar.sources=${SONAR_SRC} \
                        -Dsonar.java.binaries=. \
                        -Dsonar.sourceEncoding=${SONAR_ENCODING}"
                }
            }
        }
    }   

      stage('Tools initialization') {
          steps {
              script {

                    if (env.BRANCH_NAME){

                        RAMA_PARA_CLONAR = env.BRANCH_NAME

                    }
                    else{

                        RAMA_PARA_CLONAR = 'develop'
                        
                    }


                  DOCKER_VERSION = sh(returnStdout: true, script: 'docker version').trim()
                  JAVA_VERSION = sh(returnStdout: true, script: 'java -version').trim()
                  MAVEN_VERSION = sh(returnStdout: true, script: 'mvn -v').trim()

                  echo "Docker version: ${DOCKER_VERSION}"
                  echo "Java version: ${JAVA_VERSION}"
                  echo "Maven version: ${MAVEN_VERSION}"
              }
          }
      }

      
      stage('Build Maven') {  
               
            steps{

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


    stage('Deploy to Kubernetes') {

        when { //when: Es una directiva condicional que controla cuándo debe ejecutarse este stage
            anyOf { // anyOf: significa que el stage se ejecutará si al menos una de estas condiciones es verdadera

                branch 'master'
                branch 'develop'

                expression{
                    return (RAMA_PARA_CLONAR == 'develop' || RAMA_PARA_CLONAR == 'master') // condicional
                }
            }
        }

    }

   }


 }
