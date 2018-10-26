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
      agent {
        docker {
          image "android:latest"
          args '-it --rm -v $PWD:/app'
        }
      }
      steps {
        sh "./gradlew clean"
        withSonarQubeEnv {
          sh "./gradlew sonarqube -Dsonar.host.url=$SONAR_HOST_URL -Dsonar.login=$SONAR_AUTH_TOKEN -Dsonar.branch=$BUILD_TYPE"
        }
      }
    }

    stage("Build") {
      agent {
        docker {
          image "android:latest"
          args '-it --rm -v $PWD:/app'
        }
      }
      steps {
        sh "./gradlew assemble${BUILD_TYPE.capitalize()}"
      }
    }

    stage("Test") {
      when {
        expression { isDevelop() }
      }
      agent {
        docker {
          image "android:latest"
          args '-it --rm -v $PWD:/app'
        }
      }
      steps {
        sh "./gradlew jacocoTestReport"
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
      script {
        echo "I succeeeded!"

        def notify = load "ci/notify.groovy"
        notify.send("SUCCESS")

        deleteDir()
      }
    }

    unstable {
      echo "I am unstable :/"
    }

    failure {
      script {
        echo "I failed :("

        def notify = load "ci/notify.groovy"
        notify.send("SUCCESS")

        deleteDir()
      }
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
