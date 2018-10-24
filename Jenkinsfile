pipeline {
    agent any

    environment {
        BUILD_TYPE = buildType()
    }

    stages {
        stage("Checkout") {
            environment {
                CONFIG_URL = "https://s3.amazonaws.com/devfest/$BUILD_TYPE"
                CONFIG_PROP = "key.properties"
                CONFIG_KEY = "${BUILD_TYPE}.jks"
            }
            steps {
                sh "chmod +x ./gradlew"
                sh "mkdir -p config"
                sh "curl -o config/$CONFIG_PROP $CONFIG_URL/$CONFIG_PROP"
                sh "curl -o config/$CONFIG_KEY $CONFIG_URL/$CONFIG_KEY"
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
