package com.ldiego.app

import android.widget.TextView
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    lateinit var activity:MainActivity

    @Before
    fun setUp() {
        activity = Robolectric.setupActivity(MainActivity::class.java)
    }

    @Test
    fun `Should view buildType`() {
        val txtBuildType = activity.findViewById<TextView>(R.id.txtBuildType)

        assertEquals(txtBuildType.text, BuildConfig.BUILD_TYPE)
    }

    @Test
    fun `Should view version`() {
        val pi = activity.packageManager.getPackageInfo(activity.packageName, 0)
        val txtVersion = activity.findViewById<TextView>(R.id.txtVersion)

        assertEquals(txtVersion.text, pi.versionName)
    }
}
