pipeline {
agent any 

   tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "maven3.6.3"
    }

   stages {

        stage('Clone Source Code') {
            steps {
                // Get some code from a GitHub repository
               sh 'Githup repo'
            }
            }
    }
    stage('SAST'){
      steps{
        sh 'echo SAST stage'
       }
    }

    
   stage('Build Package') {  
            
    steps{
                
                // check maven version 
                sh "mvn -version"
                   // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
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
