
pipeline {
    agent any

    stages {
        stage('Stop containers') {
            steps {
                dir('ci/pocketbase') {
                    sh "docker-compose down"
                }
            }
        }
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/zihbot/personsal-finance-manager.git'
                step ([$class: 'CopyArtifact',
                    projectName: 'pfm-build-client',
                    filter: '**/dist.zip',
                    flatten: true,
                    target: 'ci/pocketbase']);
                step ([$class: 'CopyArtifact',
                    projectName: 'pfm-build-client',
                    filter: '**/pbcore.zip',
                    flatten: true,
                    target: 'ci/pocketbase']);
                dir('ci/pocketbase') {
                    sh "unzip -o dist.zip"
                    sh "unzip -o pbcore.zip"
                    sh "cp out/* ./"
                    sh "mkdir -p pb_public"
                    sh "cp dist/* ./pb_public/"
                }
            }
        }
        stage('Start containers') {
            steps {
                dir('ci/pocketbase') {
                    sh "docker-compose up -d"
                }
            }
        }
    }
}

