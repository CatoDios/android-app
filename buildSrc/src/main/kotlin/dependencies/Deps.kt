package dependencies

private const val androidGradleVersion = "3.2.1"
private const val kotlinVersion = "1.2.71"

private const val supportVersion = "28.0.0"
private const val constraintLayoutVersion = "1.1.3"

private const val junitVersion = "4.12"
private const val runnerVersion = "1.0.2"
private const val espressoVersion = "3.0.2"


object Deps {
    object BuildPlugins {
        val androidGradle = "com.android.tools.build:gradle:$androidGradleVersion"
        val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    }

    object Libs {
        val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
        val appcompat = "com.android.support:appcompat-v7:$supportVersion"
        val constraintLayout = "com.android.support.constraint:constraint-layout:$constraintLayoutVersion"
    }

    object TestLibs {
        val junit = "junit:junit:$junitVersion"
        val runner = "com.android.support.test:runner:$runnerVersion"
        val espresso = "com.android.support.test.espresso:espresso-core:$espressoVersion"
    }
}
