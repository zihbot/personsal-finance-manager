pipeline {
    agent any

    stages {
        stage('Start core build') {
            when {
                anyOf {
                    changeset "core/**"
                    changeset "ci/build.core.Jenkinsfile"
                }
            }
            steps {
                sh "echo disabled core build"
                //build job: 'pfm-build-core'
            }
        }
        stage('Start pocketbase build') {
            when {
                anyOf {
                    changeset "pocketbase/**"
                    changeset "ci/build.pbcore.Jenkinsfile"
                }
            }
            steps {
                build job: 'pfm-build-pbcore'
            }
        }
        stage('Start webapp build') {
            when {
                anyOf {
                    changeset "webapp/**"
                    changeset "ci/build.webapp.Jenkinsfile"
                }
            }
            steps {
                sh "echo disabled webapp build"
                //build job: 'pfm-build-webapp'
            }
        }
        stage('Start client build') {
            when {
                anyOf {
                    changeset "client/**"
                    changeset "ci/build.client.Jenkinsfile"
                }
            }
            steps {
                build job: 'pfm-build-client'
            }
        }
        stage('Start deploy') {
            steps {
                //build job: 'pfm-deploy-dev'
                build job: 'pfm-deploy-pocketbase'
            }
        }
    }
}
