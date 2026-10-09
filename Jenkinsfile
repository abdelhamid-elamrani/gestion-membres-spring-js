pipeline{
    agent any
    environment {
        EC2_IP = '98.93.127.218'
    }
    stages{
        stage('Fetch code'){
            steps{
                git branch: 'main',url: 'https://github.com/abdelhamid-elamrani/gestion-membres-spring-js.git'
            }
            post{
                success {
                    echo 'Code recuperé avec succès!'
                }
            }
        }

        stage('Build'){
            steps{
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
            post{
                success {
                    echo 'Code build avec succès!'
                }
            }
        }

        stage('Test'){
            steps{
                sh './mvnw test'
            }
            post{
                success {
                    echo 'Section test réussie!'
                }
            }
            
        }

        stage('Docker Build'){
            steps{
                sh 'docker build -t elamrani7/gest-membres-spboot-js:latest .'
            }
            post{
                success {
                    echo 'Image genérée avec succès!'
                }
            }
        }

        stage('Docker Push'){
            steps{
                    withCredentials([
                        usernamePassword(
                            credentialsId: 'dockerhub-creds',
                            usernameVariable: 'DOCKER_USER',
                            passwordVariable: 'DOCKER_TOKEN'
                        )
                    ]) {
                        sh 'printf "%s" "$DOCKER_TOKEN" | docker login -u "$DOCKER_USER" --password-stdin'
                        sh 'docker push elamrani7/gest-membres-spboot-js:latest'
                    }
            }
        }

        stage('Deploy') {
            steps {
                withCredentials([
                    sshUserPrivateKey(
                        credentialsId: 'ec2-ssh-key',
                        keyFileVariable: 'SSH_KEY',
                        usernameVariable: 'SSH_USER'
                    )
                ]) {
                    sh 'ssh  -o StrictHostKeyChecking=no -i "$SSH_KEY" "$SSH_USER"@"$EC2_IP" "cd ~/gestion-membres && docker compose pull && docker compose up -d"'
                }
            }
        }

        

}
}