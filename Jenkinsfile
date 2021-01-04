pipeline {
    agent {
		label 'mac'
	}

    stages {
        stage('Test') {
            steps {
				checkout scm
				sh './gradlew test'
            }
        }
    }
}