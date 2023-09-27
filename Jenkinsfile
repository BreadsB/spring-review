pipeline {
    agent any

    tools {
        jdk 'java-17'
    }

    stages {
        stage('Build') {
            steps {
                bat "gradle :notes:clean build"
            }
        }

        stage('Test') {
            steps {
                bat "gradle :notes:test"
            }
        }
    }
}