pipeline {
    agent any

    stages {
        stage('build') {
            steps {
                sh """#!/bin/bash
                ./gradlew clean build
                """
            }
        }
    }

    post {
    	success {
            recordCoverage(
            	tools: [
            		[
            			parser: 'JACOCO',
            			pattern: '**/jacocoTestReport.xml'
        			]
        		], 
            	id: 'jacoco', 
            	name: 'Jacoco Coverage'
        	)
    	}
    }
}