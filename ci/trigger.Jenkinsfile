pipeline {
    agent any

    stages {
        stage('Start core build') {
            when {
                changeset "core/**"
            }
            steps {
                build job: 'pfm-build-core'
            }
        }
    }
}
