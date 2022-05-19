package com.task.oneday.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.task.oneday.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getPreferences(MODE_PRIVATE)
        when (prefs.getInt(getString(R.string.THEME_KEY), -1)) {
            1 -> setTheme(R.style.Theme_OneDay_NoActionBarGreenApiL)
            2 -> setTheme(R.style.Theme_OneDay_NoActionBar)
            3 -> setTheme(R.style.Theme_Blue)
            4 -> setTheme(R.style.Theme_Red)
            5 -> setTheme(R.style.Theme_OneDay_NoActionBarGreenApiL)
            else -> setTheme(R.style.Theme_OneDay)
        }
        setContentView(R.layout.activity_main)
        savedInstanceState.let {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.container_activity_main, MainFragment())
            }
        }
    }
}