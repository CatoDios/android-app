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
          args '-it --network host -v ${PWD}:/app -v ${HOME}/.gradle:/root/.gradle'
          reuseNode true
        }
      }
      steps {
        withEnv(["CI=true"]) {
          sh "./gradlew clean --build-cache"
          withSonarQubeEnv("SonarQube") {
            sh "./gradlew sonarqube -Dsonar.host.url=$SONAR_HOST_URL -Dsonar.login=$SONAR_AUTH_TOKEN -Dsonar.branch=$BUILD_TYPE --build-cache"
          }
        }
      }
    }

    stage("Build") {
      agent {
        docker {
          image "android:latest"
          args '-it --network host -v ${PWD}:/app -v ${HOME}/.gradle:/root/.gradle'
          reuseNode true
        }
      }
      steps {
        withEnv(["CI=true"]) {
          sh "./gradlew assemble${BUILD_TYPE.capitalize()} --build-cache"
        }
      }
    }

    stage("Test") {
      when {
        expression { isDevelop() }
      }
      agent {
        docker {
          image "android:latest"
          args '-it --network host -v ${PWD}:/app -v ${HOME}/.gradle:/root/.gradle'
          reuseNode true
        }
      }
      steps {
        withEnv(["CI=true"]) {
          sh "./gradlew jacocoTestReport --build-cache"
        }
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

        archiveArtifacts "**/outputs/apk/**/*.apk"

        reportSaveTest()
        reportSaveCoverage()

        def notify = load "ci/notify.groovy"
        notify.send("SUCCESS")

        // deleteDir()
      }
    }

    unstable {
      echo "I am unstable :/"
    }

    failure {
      script {
        echo "I failed :("

        def notify = load "ci/notify.groovy"
        notify.send("FAILED")

        // deleteDir()
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

def reportSaveTest() {
  try {
    publishHTML target: [
      allowMissing: false,
      alwaysLinkToLastBuild: false,
      keepAll: true,
      reportDir: "app/build/reports/test/test${BUILD_TYPE.capitalize()}UnitTest/html",
      reportFiles: "index.html",
      reportName: "Test"
    ]
  }catch(e) {
    echo "ERROR -> ${e.message}"
  }
}

def reportSaveCoverage() {
  try {
    publishHTML target: [
      allowMissing: false,
      alwaysLinkToLastBuild: false,
      keepAll: true,
      reportDir: "app/build/reports/coverage/jacocoTestReport/html",
      reportFiles: "index.html",
      reportName: "Code Coverage"
    ]
  }catch(e) {
    echo "ERROR -> ${e.message}"
  }
}
