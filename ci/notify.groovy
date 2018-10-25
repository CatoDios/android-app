def send(String buildStatus = "STARTED") {
    def code = ""

    if (buildStatus == "STARTED") {
        color = "#FFFF00"
    } else if (buildStatus == "SUCCESS") {
        color = "#00FF00"
    } else {
        color = "#FF0000"
    }

    slackSend (
        channel: "#ci",
        color: code,
        message: "BUILD $buildStatus: ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
        channel: "#ci",
        tokenCredentialId: "k5Fu4i2e2Wky4MYMAfef5zSO"
    )
}

return this
