package com.example.workflow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class dashboard extends AppCompatActivity {
    ImageView img1,img2;
    TextView kedarnathTV,corbettTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dashboard);
        kedarnathTV=findViewById(R.id.kedarnathTV);
        corbettTv=findViewById(R.id.corbettTV);
        img1=findViewById(R.id.imageView4);
        img2=findViewById(R.id.imageView5);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Places.class));
                finish();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Corbett.class));
                finish();
            }
        });
        kedarnathTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Places.class));
                finish();
            }
        });
        corbettTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Corbett.class));
                finish();
            }
        });
    }
}