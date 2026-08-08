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

        stage('Package') {
            steps {
                echo 'Packaging application'
                bat 'mvn package -DskipTests'
            }
        }
    }
}