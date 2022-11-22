pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/zihbot/personsal-finance-manager.git'
            }
        }
        stage('Build poscketbase core') {
            steps {
                sh "cp -p ${WORKSPACE}/ci/pocketbase/Dockerfile ${WORKSPACE}/pocketbase/"
                dir('pocketbase') {
                    sh "mkdir -p ${WORKSPACE}/pocketbase/out"
                    sh "docker build . -v ${WORKSPACE}/pocketbase/out:/out"
                    sh "cp pb_schema.json out/"
                }
            }
        }
        stage('Archive') {
            steps {
                dir('pocketbase') {
                    sh "zip -r pbcore.zip out"
                    archiveArtifacts artifacts: 'pbcore.zip'
                }
            }
        }
    }
}
