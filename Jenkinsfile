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
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/trainmefordevsecops/sample-maven-deployment.git']]])
                  }
            }
    }
    stage('SAST'){
      steps{
        sh 'echo SAST stage'
       }
    }

    
    stage('Build-and-Tag') {
    /* This builds the actual image; synonymous to
         * docker build on the command line */
      steps{    
        sh 'echo Build and Tag'
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
