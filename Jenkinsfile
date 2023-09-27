pipeline {
    agent any

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