pipeline {
  agent any
  triggers {
    pollSCM('H/5 * * * *')       // every 5 minutes
  }
  stages {
    stage('Checkout') {
      steps {
        checkout([
          $class: 'GitSCM',
          branches: [[name: '*/Assignment01C&O']],             // or */Assignment01C&O
          userRemoteConfigs: [[
            url: 'https://github.com/SumanthReddyKConestoga/flask-app.git',
            credentialsId: 'github-pat'
          ]]
        ])
      }
    }
    stage('Build') {
      steps {
        bat 'echo 🚀 Building application…'
      }
    }
    stage('Test') {
      steps {
        bat 'echo ✅ Running tests…'
      }
    }
  }
  post {
    success {
      // ← this echo is mandatory, it’s “the step” for the success branch
      bat 'echo ✅ BUILD PASSED: ${env.JOB_NAME} #${env.BUILD_NUMBER}'
    }
    failure {
      // ← similarly, you need at least one step here
      bat 'echo ❌ BUILD FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}'
    }
  }
}
