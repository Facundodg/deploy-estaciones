pipeline {
agent any 

   tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "maven3.8.8"
        jdk 'java20'
    }

   stages {
      
      stage('Build Package') {  
               
          steps{

             sh "echo prueba"
             sh "mvn -version"
             sh "mvn clean package -DskipTests"

            }
         
      }

     stage('Post-to-dockerhub') {
          
           steps {
              sh 'echo post to dockerhub repo'
           }
          
     }

    stage('SECURITY-IMAGE-SCANNER'){
       
         steps {
           sh 'echo scan image for security'
        }
       
    }

    stage('Pull-image-server') {
       
         steps {
            sh 'echo pulling image ...'
          }
       
    }
    
    stage('DAST') {
       
      steps  {
         sh 'echo dast scan for security'
        }
       
    }
 }
}
