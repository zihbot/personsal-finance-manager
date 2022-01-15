pipeline {
    agent any

    stages {
        stage('Cleanup') {
            steps {
                catchError (stageResult: 'SUCCESS', buildResult: null) {
                    sh 'docker stop $(docker ps -aq -f "name=pmf-*")'
                }
                catchError (stageResult: 'SUCCESS', buildResult: null) {
                    sh 'docker rm $(docker ps -aq -f "name=pmf-*")'
                }
            }
        }
        stage('Deploy core') {
            steps {
                script {
                    def core_image = docker.image 'pfm-core'
                    def core_container = core_image.run('-p 35682:8080 --name pmf-core')
                }
            }
        }
    }
}