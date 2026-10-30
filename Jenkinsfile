pipeline {
    agent any

    environment {
        STUDENT_NAME = 'Ruslan Omargali'
        STUDENT_ID   = '37858'
        IMAGE_NAME   = 'omargali-ruslan-student-api'
        IMAGE_TAG    = 'v1.0-omargali-ruslan-37858'
    }

    triggers {
        pollSCM('H/2 * * * *')
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    stages {
        stage('Checkout') {
            steps {
                echo "=== Pipeline of ${STUDENT_NAME} (ID ${STUDENT_ID}) ==="
                checkout scm
                sh 'git log -1 --oneline'
            }
        }

        stage('Build') {
            steps {
                echo "Building Spring Boot app for ${STUDENT_NAME}"
                sh 'chmod +x gradlew'
                sh './gradlew clean bootJar -x test --no-daemon'
            }
        }

        stage('Test') {
            steps {
                echo "Running tests for ${STUDENT_NAME}"
                sh './gradlew test --no-daemon'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
                }
            }
        }

        stage('Docker Build') {
            steps {
                echo "Building image ${IMAGE_NAME}:${IMAGE_TAG}"
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} -t ${IMAGE_NAME}:latest ."
            }
        }

        stage('Verify') {
            steps {
                sh "docker image inspect ${IMAGE_NAME}:${IMAGE_TAG} --format 'Image OK: {{.RepoTags}} size={{.Size}} created={{.Created}}'"
                sh "docker images ${IMAGE_NAME}"
            }
        }
    }

    post {
        success {
            echo "SUCCESS: ${STUDENT_NAME} — image ${IMAGE_NAME}:${IMAGE_TAG} is ready"
        }
        failure {
            echo "FAILED: pipeline of ${STUDENT_NAME} stopped. Check the stage logs above."
        }
    }
}
