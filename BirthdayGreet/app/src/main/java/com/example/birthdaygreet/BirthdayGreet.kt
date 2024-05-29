package com.example.birthdaygreet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_birthday_greet.*

class BirthdayGreet : AppCompatActivity() {

    companion object{
        const val NAME_EXTRA="nameExtra";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday_greet)
        supportActionBar?.hide()

        val name = intent.getStringExtra(NAME_EXTRA);

        birthdayText.setText("Happy Birthday\n$name!")
    }
}