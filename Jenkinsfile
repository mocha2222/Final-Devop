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
                to: "${env.GIT_COMMITTER_EMAIL}",
                cc: "srengty@gmail.com",
                subject: "Jenkins Build Failed",
                body: """
Build failed.

Project: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}

Commit:
${env.GIT_COMMIT}

Please check Jenkins.
"""
            )
        }


        success {

            echo "Build and deployment completed successfully"
        }
    }
}