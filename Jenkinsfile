pipeline {
    agent any

    stages {
        stage('build') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'github-auth', 
                                 passwordVariable: 'GRGIT_PASS', 
                                 usernameVariable: 'GRGIT_USER')]) {
                    sh """#!/bin/bash
                       ./gradlew candidate
                    """
                }
            }    
        }
    }
}