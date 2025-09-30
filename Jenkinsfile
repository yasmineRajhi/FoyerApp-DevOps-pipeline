pipeline {
    agent any
    tools {
        jdk 'JAVA_HOME'
        maven 'mvn'
    }
    environment {
        DOCKER_IMAGE = "yasmine/foyer-project:1.0.0"
        NEXUS_URL = "http://192.167.33.10:8087"
        NEXUS_REPO = "maven-releases"
        SONARQUBE_LOGIN = "admin"
        SONARQUBE_PASSWORD = "Admin123456-"
    }
    stages {

        stage('GIT') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/YasmineRajhi/foyer-project.git'
            }
        }

        stage('Compile Stage') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=${SONARQUBE_LOGIN} -Dsonar.password=${SONARQUBE_PASSWORD} -Dmaven.test.skip=true"
            }
        }

        stage('Nexus Deployment') {
            steps {
                script {
                    def artifactExists = sh(
                        script: "curl -s -o /dev/null -w \"%{http_code}\" -u admin:admin \"${NEXUS_URL}/repository/${NEXUS_REPO}/tn/esprit/tpfoyer/0.0.1/foyer-project-0.0.1.jar\"",
                        returnStdout: true
                    ).trim()

                    if (artifactExists != '200') {
                        sh 'mvn deploy -Dmaven.test.skip=true'
                    } else {
                        echo 'Artifact already exists on Nexus; skipping deployment.'
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    def imageExists = sh(
                        script: "curl -s -o /dev/null -w \"%{http_code}\" https://hub.docker.com/v2/repositories/yasmine/foyer-project/tags/1.0.0/",
                        returnStdout: true
                    ).trim()

                    if (imageExists != '200') {
                        withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                            sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
                            sh "docker push ${DOCKER_IMAGE}"
                        }
                    } else {
                        echo 'Docker image already exists on Docker Hub; skipping push.'
                    }
                }
            }
        }

        stage('Deploy with ArgoCD') {
            steps {
                script {
                    echo "Trigger ArgoCD sync for deployment..."
                    // Assuming you have argocd CLI installed on agent and configured
                    sh "argocd app sync foyer-project-app"
                }
            }
        }
    }
}
