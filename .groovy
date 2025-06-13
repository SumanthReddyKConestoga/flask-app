pipeline {
  agent any
  triggers {
    pollSCM('H/5 * * * *')        // every 5 minutes
  }
  stages {
    stage('Checkout') {
      steps {
        // wrap your git step in parentheses & steps block
        git(
          url: 'https://github.com/SumanthReddyKConestoga/flask-app.git',
          branch: 'Assignment01C&O',
          credentialsId: 'github-pat'
        )
      }
    }
    stage('Build') {
      steps {
        sh 'echo "🚀 Building application…"' 
      }
    }
    stage('Test') {
      steps {
        sh 'echo "✅ Running tests…"' 
      }
    }
    // Optional deploy stage
    stage('Deploy') {
      when { expression { false } }
      steps {
        sh 'echo "📦 Deploying (skipped)…"'
      }
    }
  }
  post {
    success {
      mail to: 'team@yourcompany.com',
           subject: "✅ ${env.JOB_NAME} #${env.BUILD_NUMBER} succeeded",
           body: "View at ${env.BUILD_URL}"
    }
    failure {
      mail to: 'team@yourcompany.com',
           subject: "❌ ${env.JOB_NAME} #${env.BUILD_NUMBER} failed",
           body: "View at ${env.BUILD_URL}"
    }
  }
}
