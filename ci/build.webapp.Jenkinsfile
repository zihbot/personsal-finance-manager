pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/zihbot/personsal-finance-manager.git'
            }
        }
        stage('Build webapp') {
            steps {
                sh "mkdir -p ${WORKSPACE}/../pfm-webapp-cache"
                sh "docker run --rm -v ${WORKSPACE}/webapp:/app -v ${WORKSPACE}/../pfm-webapp-cache:/app/node_modules -w /app node:18-buster-slim /bin/bash -c 'yarn install --frozen-lockfile --prefer-offline && yarn build'"
            }
        }
        stage('Archive') {
            steps {
                dir('webapp') {
                    sh "zip -r dist.zip dist"
                    archiveArtifacts artifacts: 'dist.zip'
                }
            }
        }
    }
}
