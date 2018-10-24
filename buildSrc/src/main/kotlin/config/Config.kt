package config

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Config {
    object Build {
        val scanTerms = "https://gradle.com/terms-of-service"
        val scanTermsAgree = "yes"
    }

    object App {
        val name = "app"
    }

    object Android {
        val minSdkVersion = 21
        val compileSdkVersion = 28
        val targetSdkVersion = 28
        val applicationId = "com.ldiego.app"
        val versionCode = 1
        val versionName = "1.0"
        val multiDexEnabled = true
        val useSupportLibrary = true
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

}
