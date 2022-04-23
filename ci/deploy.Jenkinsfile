
pipeline {
    agent any

    environment {
        POSTGRES_PASS = credentials('pfm-postgres')
    }

    stages {
        stage('Cleanup') {
            steps {
                catchError (stageResult: 'SUCCESS', buildResult: null) {
                    sh 'docker stop $(docker ps -aq -f "name=pfm-*")'
                }
                catchError (stageResult: 'SUCCESS', buildResult: null) {
                    sh 'docker rm $(docker ps -aq -f "name=pfm-*")'
                }
            }
        }
        stage('Deploy db') {
            steps {
                script {
                    def db_image = docker.image 'postgres'
                    def db_container = db_image.run('--rm --name pfm-db -p 35633:5432 -e POSTGRES_PASSWORD=$POSTGRES_PASS_PSW')
                }
            }
        }
        stage('Deploy core') {
            steps {
                script {
                    def core_image = docker.image 'pfm-core'
                    def core_container = core_image.run('--rm -p 35682:8080 --name pfm-core -e spring.datasource.password=$POSTGRES_PASS_PSW')
                }
            }
        }
    }
}

