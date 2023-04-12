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
                script{
                    def version_value = sh(returnStdout: true, script: "cat build.gradle | grep -o 'version=[^,]*'").trim()
                    def version = version_value.split(/=/)[1]
                    sh "cp build/libs/laptimes.${version}.jar build/libs/laptimes-latest.jar"
                }
            }
        }
    }
}
