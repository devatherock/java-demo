pipeline {
    agent any

    stages {
        stage('build') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'github-auth', 
                                 passwordVariable: 'GRGIT_PASS', 
                                 usernameVariable: 'GRGIT_USER')]) {
                    sh '''#!/bin/bash
                       git config url."https://${GRGIT_PASS}@github.com".insteadOf https://github.com
                       ./gradlew candidate
                    '''
                }
            }    
        }
    }
}