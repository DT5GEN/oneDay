package com.task.oneday.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.task.oneday.R

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_THEME = "KEY_THEME"
    }

    private var savedTheme: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.getInt(KEY_THEME, -1)?.takeIf {
            it != -1
        }?.let {
            setTheme(it)
        }
        setContentView(R.layout.activity_main)

//        findViewById<Button>(R.id.btn_theme1)?.setOnClickListener {
//            savedTheme = R.style.Theme_OneDay_NoActionBar
//            recreate()
//        }
//
//        findViewById<Button>(R.id.btn_theme2)?.setOnClickListener {
//            savedTheme = R.style.Theme_OneDay_NoActionBarGreenApiL
//            recreate()
//        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        savedTheme?.let { outState.putInt(KEY_THEME, it) }

    }
}