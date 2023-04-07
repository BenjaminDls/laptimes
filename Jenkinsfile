pipeline {
    agent any
    stages {
        stage('Build') {
            when {
                branch "master"
            }
            steps {
                // Run Gradle to build without the tests.
                sh "./gradlew build"
            }
        }
        stage('Results') {
            when {
                branch "master"
            }
            steps {
                archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            }
        }
    }
}
