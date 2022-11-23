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
                sh "mkdir -p ${WORKSPACE}/../pfm-pbcore-cache"
                sh "mkdir -p ${WORKSPACE}/pocketbase/out"
                sh "docker run --rm -v ${WORKSPACE}/pocketbase:/usr/src/myapp -v ${WORKSPACE}/../pfm-pbcore-cache:/go/pkg/mod -w /usr/src/myapp golang:1.19-buster go build -v -o ./out/pfm-pocketbase"
                dir('pocketbase') {
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
