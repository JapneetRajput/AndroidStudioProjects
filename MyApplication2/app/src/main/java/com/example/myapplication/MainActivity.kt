package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigationrail.NavigationRailView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigationRail: NavigationRailView = findViewById(R.id.navigationRail)
        navigationRail.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.files -> {
                    Toast.makeText(this, "Files", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.images -> {
                    Toast.makeText(this, "Images", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.music -> {
                    Toast.makeText(this, "Music", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.videos -> {
                    Toast.makeText(this, "Videos", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}