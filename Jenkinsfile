#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('Set Dev Variable') {
            when {
                expression {
                    return env.BRANCH_NAME.contains("dev")
                }
            }
            steps {
                sh 'export BRANCH=Snapshot'
            }
        }
        stage('Clean') {
            steps {
                echo 'Cleaning Project'
                sh 'chmod +x gradlew'
                sh './gradlew clean'
            }
        }
        stage('Setup') {
            steps {
                echo 'Setting up Workspace'
                sh './gradlew setupdecompworkspace'
            }
        }
        stage('Build and Deploy') {
            steps {
                echo 'Building and Deploying to Maven'
                sh './gradlew build ' + branch + '--refresh-dependencies uploadArchives'
            }
        }
    }
    post {
        always {
            archive 'build/libs/**.jar'
        }
    }
}