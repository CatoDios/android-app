package config

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Config {
    object Build {
        val scanTerms = "https://gradle.com/terms-of-service"
        val scanTermsAgree = "yes"
    }

    object Sonar {
        val projectKey = "com.ldiego.app"
        val projectName = "App - Android -"
        val projectDescription = "App mobile architecture"
        val modules = "app"
        val sources = "src/main/kotlin"
        val sourceEncoding = "UTF-8"
        val exclusions = "**/*test*/**, build/**, *.iml, **/*generated*"
        val test = "src/test/kotlin"
        val testInclusions  = "**/*test*/**"
        val coveragePlugin = "jacoco"
    }

    object App {
        val name = "app"
        val minSdkVersion = 21
        val compileSdkVersion = 28
        val targetSdkVersion = 28
        val applicationId = "com.ldiego.app"
        val versionCode = 1
        val versionName = "1.0"
        val multiDexEnabled = true
        val useSupportLibrary = true
        val testBuildType = "develop"
    }

    object Abi {
        val enable = true
        val includes = arrayOf("armeabi-v7a", "arm64-v8a", "x86")
        val universalApk = false
    }

    object Language {
        val enable = false
    }

    object Packaging {
        val excludes = arrayOf("LICENSE.txt", "META-INF/DEPENDENCIES", "META-INF/ASL2.0", "META-INF/NOTICE", "META-INF/LICENSE")
    }

    object Lint {
        val quiet = true
        val abortOnError = true
        val ignoreWarnings = true
        val checkAllWarnings = true
        val warningsAsErrors = false
        val noLines = true
        val showAll = true
        val file = "lint.xml"
        val xmlOutput = "reports/lint-app.xml"
        val htmlOutput = "reports/lint-app.html"
    }

    object SigningConfigs {
        val env = "config/key.properties"

        val storeFile = "store.file"
        val storePassword = "store.password"
        val keyAlias = "key.alias"
        val keyPassword = "key.password"
    }

    object BuildTypes {
        private val current = LocalDateTime.now()
        private val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")

        object Develop {
            val appIdSuffix = ".dev"
            val versionSuffix = "-nb.${current.format(formatter)}"
            val debuggable = true
            val shrinkResources = false
            val minify = false
            val testCoverageEnabled = true
            val label = "develop"
            val color = "#007200"
        }

        object Stage {
            val appIdSuffix = ".stage"
            val versionSuffix = "-rc.${current.format(formatter)}"
            val debuggable = false
            val shrinkResources = true
            val minify = true
            val label = "stage"
            val color = "#6600CC"
        }

        object Release {
            val appIdSuffix = ""
            val versionSuffix = ""
            val debuggable = false
            val shrinkResources = true
            val minify = true
            val label = ""
            val colorEnable = false
        }
    }

    object TestOptions {
        val reportsDir = "reports/coverage"
        val includeNoLocationClasses = true
        val execution = "ANDROID_TEST_ORCHESTRATOR"
        val animationsDisabled = true
        val includeAndroidResources = true
        val xml = true
        val html = true
        val filters = listOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*", "**/*Test*.*", "android/**/*.*", "**/*$[0-9].*")
        val executionData = listOf("jacoco/testDevelopUnitTest.exec", "outputs/code-coverage/connected/*coverage.ec")
    }

}
