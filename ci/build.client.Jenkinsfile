pipeline {
    agent any
    environment {
        SONAR_TOKEN = credentials('sonar-zihbot-eu')
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/zihbot/personsal-finance-manager.git'
            }
        }
        stage('Build client') {
            steps {
                sh "mkdir -p ${WORKSPACE}/../pfm-client-cache"
                sh "docker run --rm -v ${WORKSPACE}/client:/app -v ${WORKSPACE}/../pfm-client-cache:/app/node_modules -w /app zihbot/node-java:latest /bin/bash -c 'yarn install --frozen-lockfile --prefer-offline && yarn build && SONAR=${SONAR_TOKEN} yarn sonar'"
            }
        }
        stage('Archive') {
            steps {
                dir('client') {
                    sh "zip -r dist.zip dist"
                    archiveArtifacts artifacts: 'dist.zip'
                }
            }
        }
    }
}
