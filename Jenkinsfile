pipeline {
    agent any
    tools {
        jdk 'jdk17'
    }
    stages {
        stage('Build') {
            when {
                branch "main"
            }
            steps {
                // Run Gradle to build without the tests.
                sh "./gradlew build"
            }
        }
        stage('Publish') {
            when {
                branch "main"
            }
            steps {
                archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            }
        }
    }
}
