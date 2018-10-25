pipeline {
    agent any

    environment {
        BUILD_TYPE = buildType()
    }

    stages {
        stage("Checkout") {
            node {
                def notify = load "ci/notify.groovy"
                notify.send()
            }
            environment {
                CONFIG_URL = "https://s3.amazonaws.com/devfest/$BUILD_TYPE"
                CONFIG_PROP = "key.properties"
                CONFIG_KEY = "${BUILD_TYPE}.jks"
            }
            steps {
                script {
                    def notify = load "ci/notify.groovy"
                    notify.send()

                    sh "chmod +x ./gradlew"
                    sh "mkdir -p config"
                    sh "curl -o config/$CONFIG_PROP $CONFIG_URL/$CONFIG_PROP"
                    sh "curl -o config/$CONFIG_KEY $CONFIG_URL/$CONFIG_KEY"
                }
            }
        }

        stage("Static Analysis") {
            steps {
                echo "Static Analysis"
            }
        }

        stage("Build") {
            steps {
                echo "Static Build"
            }
        }

        stage("Test") {
            steps {
                echo "Testing"
            }
        }

        stage("Deploy") {
            steps {
                echo "Deploy"
            }
        }
    }

    post {
        always {
            echo "I have finished"
        }

        success {
            echo "I succeeeded!"

            deleteDir()
        }

        unstable {
            echo "I am unstable :/"
        }

        failure {
            echo "I failed :("

            deleteDir()
        }

        changed {
            echo "I changed!"
        }
    }
}

def buildType() {
    def branch = env.BRANCH_NAME
    def name

    if (isMaster())
        name = "release"
    else if (isStage())
        name = "stage"
    else
        name = "develop"

    name
}

def isMaster() {
    return env.BRANCH_NAME == "master"
}

def isStage() {
    return env.BRANCH_NAME == "stage"
}

def isDevelop() {
    return env.BRANCH_NAME == "develop"
}
