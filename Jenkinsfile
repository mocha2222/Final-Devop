pipeline {

    agent any


    tools {

        jdk 'JDK21'

        maven 'Maven'

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

                to: "srengty@gmail.com",

                subject: "Jenkins Build Failed",

                body: """
Build failed.

Project: ${env.JOB_NAME}

Build: ${env.BUILD_NUMBER}

${env.BUILD_URL}

"""

            )

        }



        success {

            echo "Build and deployment completed successfully"

        }

    }

}