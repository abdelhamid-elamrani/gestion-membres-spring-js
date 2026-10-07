pipeline{
    agent any
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
                sh 'docker build -t gest-membres-spboot-js .'
            }
            post{
                success {
                    echo 'Image genérée avec succès!'
                }
            }
        }

    }

}