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
                build job: 'pfm-build-core'
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
                build job: 'pfm-build-webapp'
            }
        }
    }
}
