def send(String buildStatus = "STARTED") {
  def colorCode

  if (buildStatus == "STARTED") {
    colorCode = "#FFFF00"
  } else if (buildStatus == "SUCCESS") {
    colorCode = "#00FF00"
  } else if(buildStatus == "STOPPED") {
    colorCode = "#949393"
  } else {
    colorCode = "#FF0000"
  }

  slackSend (
    color: colorCode,
    message: "BUILD $buildStatus: ${env.JOB_NAME} [${env.BUILD_NUMBER}] (${env.RUN_DISPLAY_URL})"
  )
}

return this
