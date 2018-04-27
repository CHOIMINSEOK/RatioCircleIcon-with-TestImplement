package com.example.lf_wannabe.ratiocircleicon

import android.support.test.annotation.UiThreadTest
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val testRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    var progresses: ArrayList<Float> = arrayListOf(10f, 20f, 30f, 40f, 50f, 60f, 70f, 80f, 90f)

    @UiThreadTest
    @Test
    fun useAppContext() {
        // Context of the app under test.

        progresses.forEach {
            setProcess(it)
            testRule.activity.findViewById<RatioCircleIcon>(R.id.ratio_icon).invalidate()
        }
    }


    private fun setProcess(progress: Float) {
        testRule.activity.findViewById<RatioCircleIcon>(R.id.ratio_icon).progress = progress
//        Thread.sleep(500)

//        testRule.activity.findViewById<RatioCircleIcon>(R.id.ratio_icon).invalidate()
    }
}
