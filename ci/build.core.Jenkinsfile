pipeline {
    environment {
        dockerImage = ''
    }
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/zihbot/personsal-finance-manager.git'
            }
        }
        stage('Build') {
            steps {
                dir('core') {
                    script {
                        dockerImage = docker.build 'pfm-core'
                    }
                }
            }
        }
    }
}
