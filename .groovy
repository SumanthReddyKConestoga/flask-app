pipeline {
    agent any
    environment {
        GITHUB_CREDS = credentials('github-pat')
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'Assignment',C&O
                    url: 'https://github.com/SumanthReddyKConestoga/SumanthReddyKConestoga/Skonannagari0660@conestogac.on.ca/.git',
                    credentialsId: 'github-pat'
            }
        }
        stage('Build') {
            steps {
                echo 'Simulating build step (e.g., compiling code)'
            }
        }
        stage('Test') {
            steps {
                echo 'Running basic tests'
            }
        }
    }
    post {
        success {
            mail to: 'Skonannagari0660@conestogac.on.ca',
                 subject: "Build Success: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Good news! Jenkins build succeeded.\n\nURL: ${env.BUILD_URL}"
        }
        failure {
            mail to: 'Skonannagari0660@conestogac.on.ca',
                 subject: "Build Failure: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Alert! Jenkins build failed.\n\nURL: ${env.BUILD_URL}"
        }
    }
}
