pipeline {
    agent any

    environment {
        BUILD_TYPE = buildType()
    }

    stages {
        stage("Checkout") {
            steps {
                url = "https://s3.amazonaws.com/devfest/${env.BUILD_TYPE}"
                prop = "key.properties"
                key = "${env.BUILD_TYPE}.jks"

                sh "chmod +x ./gradlew"
                sh "mkdir -p config"
                sh "curl -o config/${prop} $url/${prop}"
                sh "curl -o config/${key} $url/${key}"
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
