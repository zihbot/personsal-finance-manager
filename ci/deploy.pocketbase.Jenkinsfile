
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
                sh "rm -f -R /usr/share/personal-finance-manager/pb_public"
                step ([$class: 'CopyArtifact',
                    projectName: 'pfm-build-client',
                    filter: '**/dist.zip',
                    flatten: true,
                    target: 'ci/client']);
                dir('ci/client') {
                    sh "unzip -o dist.zip"
                    sh "cp -r ./dist/ /usr/share/personal-finance-manager/pb_public/"
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

