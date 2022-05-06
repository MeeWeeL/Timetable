package com.meeweel.timetable.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.meeweel.timetable.databinding.ActivityMainBinding
import com.meeweel.timetable.ui.fragmenthome.HomeFragment

class MainActivity : AppCompatActivity() {

    lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(bind.container.id, HomeFragment())
                .commitNow()
        }
    }
}