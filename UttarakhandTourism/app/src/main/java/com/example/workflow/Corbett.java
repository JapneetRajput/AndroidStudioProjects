package com.example.workflow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Corbett extends AppCompatActivity {

    TextView stay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corbett);
        getSupportActionBar().hide();
        stay=findViewById(R.id.stay);
        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CorbettAccomodation.class));
//                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Corbett.this,dashboard .class));
        finish();
    }
}