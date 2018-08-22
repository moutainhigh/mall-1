pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '30'))
        disableConcurrentBuilds()
    }
    environment {
        DOCKER_MODULE_NAME = 'cb-admin'
        DOCKER_IMAGE_NAME = '192.168.0.22/crystal-ball/cb-admin:car-mall'
        DOCKER_LEADER_IP = '192.168.0.206'
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
            archiveArtifacts artifacts:'cb-admin/build/libs/*.war', fingerprint: true
          }
        }
        stage('Docker build') {
            steps {
                sh 'docker login -udengcg -pDengcg0727 192.168.0.22'
                sh 'docker build -t ${DOCKER_IMAGE_NAME} -f ${DOCKER_MODULE_NAME}/dockerfile/Dockerfile .'
            }
        }

        stage('Docker push') {
            steps {
                sh 'docker push ${DOCKER_IMAGE_NAME}'
            }
        }

        stage('Deploy') {
            steps {
                script {
                    timeout(5) {
                        input "Should we deploy this image?"
                    }
                }

                withCredentials([sshUserPrivateKey(credentialsId: 'jenkins-ssh-private-key', keyFileVariable: 'SSH_KEY_FOR_JENKINS', passphraseVariable: '', usernameVariable: 'USERNAME')]) {
                    sh 'scp -i ${SSH_KEY_FOR_JENKINS} -o StrictHostKeyChecking=no -P ${DOCKER_LEADER_PORT} ./${DOCKER_MODULE_NAME}/dockerfile/${DOCKER_COMPOSE_FILE} ${USERNAME}@${DOCKER_LEADER_IP}:${DOCKER_WORKSPACE}'
                    sh 'ssh -i ${SSH_KEY_FOR_JENKINS} -o StrictHostKeyChecking=no -p ${DOCKER_LEADER_PORT} ${USERNAME}@${DOCKER_LEADER_IP} "docker login -udengcg -pDengcg0727 192.168.0.22 && docker-compose -f ${DOCKER_WORKSPACE}/${DOCKER_COMPOSE_FILE} up -d "'
                }

            }
        }
    }
}