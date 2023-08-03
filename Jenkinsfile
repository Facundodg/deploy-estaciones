#!groovy

/*
    CREDENCIALES NECESARIAS
    - SonarQube     (Token de acceso)
    - Github        (Usuario y clave)
    - DockerHub     (Usuario y clave)
    - Kubernetes    (Token del service account de Jenkins)

    HERRAMIENTAS NECESARIAS
    - Java 20
    - Docker 24.0.2 (Cualquier versión que soporte --password-stdin)
    - Maven 3.9.3

    PLUGINS NECESARIOS
    - Slack
    - Docker
    - Kubernetes
    - JUnit
    - EmailText

    CONSIDERACIONES
    * Se debe configurar como multibranch pipeline
    * Deben existir las ramas master y develop
 */

def ARTIFACT_ID
def IDENTIFICADOR_PROYECTO
def IDENTIFICADOR_UNICO_BUILD
def RAMA_PARA_CLONAR

pipeline {
    agent any

    options {
        skipDefaultCheckout true
    }

    tools {
        jdk 'Java 20'
        dockerTool 'Docker 24.0.2'
        maven 'Maven 3.9.3'
    }

    environment {
        HORA_DESPLIEGUE = sh(returnStdout: true, script: "date '+%A %W %Y %X'").trim()

        GITHUB_DESPLIEGUE_URL = "https://github.com/dim-desarrollo/gestor-estaciones-despliegue"
        GITHUB_MONOLITO_URL = "https://github.com/dim-desarrollo/gestor-estaciones"

        GITHUB_CREDENCIALES = "github"
        DOCKERHUB_CREDENCIALES = "dockerhub"
        KUBERNETES_CREDENCIALES = "k8s-jenkins-account-15"
        SONARQUBE_CREDENCIALES = 'sonarqube'

        CANAL_SLACK = "#canal-slack"            // TODO: Por reemplazar
        CORREO_A_NOTIFICAR = "dim@gmail.com"    // TODO: Por reemplazar

        CARPETA_APLICACION = 'monolito'
        CARPETA_DESPLIEGUE = 'despliegue'
    }

    stages {
        stage('Tools initialization') {
            steps {
                script {
                    if (env.BRANCH_NAME){
                        RAMA_PARA_CLONAR = env.BRANCH_NAME
                    }
                    else{
                        RAMA_PARA_CLONAR = 'develop'
                    }

                    DOCKER_VERSION = sh(returnStdout: true, script: 'docker version')
                    JAVA_VERSION = sh(returnStdout: true, script: 'java -version')
                    MAVEN_VERSION = sh(returnStdout: true, script: 'mvn -v')

                    sh "echo 'Hora despliegue: ${HORA_DESPLIEGUE}'"
                    sh "echo 'Docker version: ${DOCKER_VERSION}'"
                    sh "echo 'Java version: ${JAVA_VERSION}'"
                    sh "echo 'Maven version:  ${MAVEN_VERSION}'"
                    sh "echo 'Rama a clonar:  ${RAMA_PARA_CLONAR}'"
                }
            }
        }

        stage('Git checkout') {
            steps{

                script {
                    checkout scmGit(branches: [[name: "${RAMA_PARA_CLONAR}"]], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: "${CARPETA_APLICACION}"]], userRemoteConfigs: [[credentialsId: "${GITHUB_CREDENCIALES}", url: "${GITHUB_MONOLITO_URL}"]])
                }
            }
        }

        stage('Build Maven') {
            steps {
                dir("${CARPETA_APLICACION}"){
                    script {
                        PROYECTO_VERSION = sh(returnStdout: true, script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout')
                        ARTIFACT_ID = sh(script: "mvn help:evaluate -Dexpression=project.artifactId -f pom.xml -q -DforceStdout", returnStdout: true).trim()
                        IDENTIFICADOR_PROYECTO = "${ARTIFACT_ID}:${PROYECTO_VERSION}"
                        IDENTIFICADOR_UNICO_BUILD = "${IDENTIFICADOR_PROYECTO}.${BUILD_NUMBER}"

                        sh "echo 'Versión Proyecto: ${PROYECTO_VERSION}'"
                        sh "echo 'ArtifactID Proyecto: ${ARTIFACT_ID}'"
                        sh "echo 'Identificador Proyecto: ${IDENTIFICADOR_PROYECTO}'"
                        sh "echo 'Identificador Único Build: ${IDENTIFICADOR_UNICO_BUILD}'"

                        sh 'mvn clean package -DskipTests'
                    }
                }
            }
        }

        stage('Test') {
            steps {
                dir("${CARPETA_APLICACION}"){
                    sh 'mvn test'
                    sh 'mvn integration-test'
                }
            }
        }

        stage('SonarQube Analysis') {
            environment {
                SONAR_SCANNER_HOME = tool 'SonarQube 4.8.0'
                SONAR_SERVER = 'SonarQube'
                SONAR_HOST_IP = '172.25.0.3'                    // IP interna de Docker, debido a que SonarQube corre en un contenedor
                SONAR_PORT = '9000'
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

        stage('SonarQube Quality Gate') {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build and push to DockerHub') {
            when{
                anyOf{
                    branch 'master'
                    branch 'develop'
                }
            }
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
                            sh 'echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USERNAME --password-stdin'
                            sh "docker push \$DOCKERHUB_USERNAME/${IDENTIFICADOR_UNICO_BUILD}"
                        }
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            when {
                anyOf {
                    branch 'master'
                    branch 'develop'
                }
            }

            environment {
                RECURSOS_YML = "${RAMA_PARA_CLONAR} == 'master' ? 'prod' : 'dev'"
                KUBE_SERVIDOR = "172.20.255.15:8445"
            }

            steps {
                script {
                    // Clona el repositorio de despliegue
                    checkout scmGit(branches: [[name: 'master']], extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: "${CARPETA_DESPLIEGUE}"]], userRemoteConfigs: [[credentialsId: "${GITHUB_CREDENCIALES}", url: "${GITHUB_DESPLIEGUE_URL}"]])

                    dir ("${CARPETA_DESPLIEGUE}"){
                        // Actualiza el archivo de despliegue
                        withCredentials([username(credentialsId: "${DOCKERHUB_CREDENCIALES}", usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                            sh "sed -i 's+\$DOCKERHUB_USERNAME.*+\$DOCKERHUB_USERNAME/${ARTIFACT_ID}:${IDENTIFICADOR_UNICO_BUILD}+g' ${CARPETA_DESPLIEGUE}/general/monolito.yaml"
                        }

                        withCredentials([string(credentialsId: "${KUBERNETES_CREDENCIALES}", variable: 'KUBE_TOKEN')]) {
                            // sh "kubectl --kubeconfig=$KUBE_CONFIG apply -f ${FOLDER}"
                            // sh 'kubectl --token $KUBE_TOKEN --server ${SEVER} --insecure-skip-lts-verify=true apply -f ${FOLDER}'
                            sh "kubectl --token \$KUBE_TOKEN --server ${KUBE_SERVIDOR} apply -R -f ${RECURSOS_YML}/"
                        }
                    }
                }
            }
        }
    }
}

post {
    always {
        // Desconexión de Docker
        sh 'docker logout'


        // Mensaje por correo
        emailext(
                to: "${CORREO_A_NOTIFICAR}",
                subject: "[BuildResult][${currentBuild.currentResult}] - Job '${IDENTIFICADOR_UNICO_BUILD})",
                body: '''${SCRIPT, template="email.groovy.template"}''',
                attachLog: true
        )


        // Almacena test de Maven. TODO: Probar si funciona
        script {
            junit 'target/surefire-reports/*.xml'
        }
    }

    success {
        enviarMensajeSlack('general', "ÉXITO en el Job '${IDENTIFICADOR_UNICO_BUILD}'")
    }

    failure {
        cleanWs()
        enviarMensajeSlack('general', "FALLO en el Job '${IDENTIFICADOR_UNICO_BUILD}'")
    }
}


def enviarMensajeSlack(String canalSlack, String mensaje) {
    slackSend(
            channel: "#${canalSlack}",
            color: "good",              // Color de mensaje (opcciones: good, warning, danger)
            message: "${mensaje}"
    )
}