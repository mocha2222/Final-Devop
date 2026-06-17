pipeline {

    agent any

    tools {
        maven 'Maven'
    }

    triggers {
        pollSCM('H/5 * * * *')
    }

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                bat 'ansible-playbook deploy.yml'
            }
        }
    }

    post {

        failure {
            emailext(
                to: 'srengty@gmail.com',
                subject: 'Jenkins Build Failed',
                body: "Build failed. Check Jenkins."
            )
        }
    }
}