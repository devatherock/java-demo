pipeline {
    agent any

    stages {
        stage('build') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'github-auth', 
                                 passwordVariable: 'GRGIT_PASS', 
                                 usernameVariable: 'GRGIT_USER')]) {
                    sh '''#!/bin/bash
                       git remote add auth https://$GRGIT_PASS@github.com/devatherock/java-demo.git
                       ./gradlew candidate
                    '''
                }
            }    
        }
    }
}