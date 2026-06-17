pipeline {
    agent any

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
                ansiblePlaybook(
                    playbook: 'deploy.yml'
                )
            }
        }
    }

    post {

        success {
            archiveArtifacts artifacts: 'target/*.jar'
        }

        failure {
            emailext(
                subject: 'Build Failed',
                body: 'Build Failed',
                to: 'srengty@gmail.com'
            )
        }
    }
}