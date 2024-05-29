package com.example.pythoncompilerkotlin

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.chaquo.python.PyObject

import com.chaquo.python.Python

import com.chaquo.python.android.AndroidPlatform


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        val run = findViewById<Button>(R.id.run)
        val input = findViewById<TextInputEditText>(R.id.input)
        val output = findViewById<TextView>(R.id.output)

        if (!Python.isStarted()) Python.start(AndroidPlatform(this))
    }
}