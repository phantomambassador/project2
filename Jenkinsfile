pipeline {
    agent any

    tools {
        maven 'maven 3.9.10'
        jdk 'JAVA JDK 17'
    }

    stages {
        stage("Test") {
            steps {
                echo "Start Test"
                bat "mvn test"
            }
        }
    }
}