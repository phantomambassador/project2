// Project 2 CI/CD Pipeline
pipeline {
    agent any

    tools {
        maven 'maven 3.9.10'
        jdk 'JAVA JDK 17'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code'
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                echo 'Compiling project'
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running JUnit tests'
                bat 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube analysis'

                withSonarQubeEnv('SonarQube') {
                    bat 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=jenkins-project2 -Dsonar.projectName="Jenkins Project 2"'
                }
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging application'
                bat 'mvn package -DskipTests'
            }
        }
    }
}