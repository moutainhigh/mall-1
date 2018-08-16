pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '30'))
        disableConcurrentBuilds()
    }
    environment {
        DOCKER_MODULE_NAME = 'cb-admin'
        DOCKER_IMAGE_NAME = '192.168.0.22/crystal-ball/cb-admin:car-mall'
        DOCKER_LEADER_IP = '192.168.0.22'
        DOCKER_LEADER_PORT = '22'
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'
        DOCKER_WORKSPACE = '~/dockerfile/cb-admin'
        DOCKER_STACK_NAME = 'crystal-ball-cb-admin'
    }

    stages {
        stage('Build') {
          steps {
            sh 'chmod +x ./gradlew'
            sh './gradlew clean build'
            archiveArtifacts artifacts:'${DOCKER_MODULE_NAME}/build/libs/*.war', fingerprint: true
          }
        }
        stage('Docker build') {
            steps {
                sh 'docker build -t ${DOCKER_IMAGE_NAME} -f dockerfile/Dockerfile .'
            }
        }

        stage('Docker push') {
            steps {
                sh 'docker login -udengcg -pDengcg0727 192.168.0.22'
                sh 'docker push ${DOCKER_IMAGE_NAME}'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}