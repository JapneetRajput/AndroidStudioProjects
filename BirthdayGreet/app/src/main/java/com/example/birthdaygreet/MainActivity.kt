package com.example.birthdaygreet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

    }

    fun createBirthdayCard(view: View) {
//        val name = nameInput.editableText.toString()
//        Toast.makeText(this, "Name : $name",Toast.LENGTH_LONG).show()

        val name = nameInput.editableText.toString()

        val birthdayGreetIntent = Intent(applicationContext, BirthdayGreet::class.java)
        birthdayGreetIntent.putExtra(BirthdayGreet.NAME_EXTRA,name)
        startActivity(birthdayGreetIntent)
//        finish() // Destroys the previous Activity
    }
}