pipeline {
    agent any

    environment {
        BUILD_TYPE = buildType()
    }

    stages {
        stage("Checkout") {
            environment {
                CONFIG_URL = "https://s3.amazonaws.com/devfest/"
                CONFIG_PROP = "key.properties"
            }
            steps {
                sh "chmod +x ./gradlew"
                sh "mkdir -p config"
                sh "curl -o config/$CONFIG_PROP $CONFIG_URL/$BUILD_TYPE/$CONFIG_PROP"
                sh "curl -o config/${BUILD_TYPE}.jks $CONFIG_URL/$BUILD_TYPE/${BUILD_TYPE}.jks"
            }
        }
    }

    post {
        always {
            echo "I have finished"
        }

        success {
            echo "I succeeeded!"
        }

        unstable {
            echo "I am unstable :/"
        }

        failure {
            echo "I failed :("
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
        dir = "release"
    else if (isStage())
        dir = "stage"
    else
        dir = "develop"

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
