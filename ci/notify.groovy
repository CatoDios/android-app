def send(String buildStatus = "STARTED") {
    def code = "#FF0000"

    if (buildStatus == "STARTED") {
        color = "#FFFF00"
    } else if (buildStatus == "SUCCESS") {
        color = "#00FF00"
    } else {
        color = "#FF0000"
    }

    slackSend (
        color: code,
        message: "BUILD $buildStatus: ${env.JOB_NAME} [${env.BUILD_NUMBER}] <br/> DEMO"
    )
}

return this
