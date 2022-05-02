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
        stage('Build core') {
            steps {
                dir('core') {
                    script {
                        sh "mkdir -p ${WORKDIR}/pfm-core-cache"
                        sh "docker run --rm -v ./:/app -v ${WORKDIR}/pfm-core-cache:/cache -e GRADLE_USER_HOME=/cache -w /app gradle:7-jdk18-alpine gradle build"
                    }
                }
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts artifacts: '**/build/libs/*.jar'
            }
        }
    }
}
