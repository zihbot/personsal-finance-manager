
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
                    projectName: 'pfm-build-pbcore',
                    filter: '**/pbcore.zip',
                    flatten: true,
                    target: 'ci/pocketbase']);
                dir('ci/pocketbase') {
                    sh "unzip -o dist.zip"
                    sh "unzip -o pbcore.zip"
                    sh "cp out/* ./"
                    sh "mkdir -p pb_public"
                    sh "cp -r dist/* ./pb_public/"
                }
            }
        }
        stage('Start containers') {
            steps {
                dir('ci/pocketbase') {
                    sh "docker-compose up --build -d"
                }
            }
        }
        stage('Migrate schema') {
            steps {
                sh "docker exec pocketbase_pocketbase_1 pfm-pocketbase schema"
            }
        }
    }
}

