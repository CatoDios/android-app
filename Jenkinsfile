pipeline {
    agent any

    environment {
        BUILD_TYPE = buildType()
    }

    stages {
        stage("Checkout") {
            steps {
                def url = getEnv()
                sh "chmod +x ./gradlew"
                sh "curl -O $url/key.properties"
                sh "curl -O $url/key.jks"
            }
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

def getEnv() {
    def dir
    if (isMaster())
        dir = "release"
    else if (isStage())
        dir = "stage"
    else
        dir = "develop"

    "https://s3.amazonaws.com/devfest/${dir}"
}
