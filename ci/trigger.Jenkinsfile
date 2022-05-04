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
    }
}
