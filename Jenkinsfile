pipeline {
    agent any

    stages {
        stage('build') {
            steps {
                sh """#!/bin/bash
                ./gradlew candidate
                """
            }
        }
    }
}