package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class FPS extends AppCompatActivity {
    EditText mass, inches, feet;
    TextView display,tv;
    Button calculate;
    ImageButton Switch;
    double m,bmi,f,i,h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fps);
        mass = findViewById(R.id.mass);
        calculate = findViewById(R.id.calculate);
        display = findViewById(R.id.display);
        tv = findViewById(R.id.tv);
        Switch = findViewById(R.id.Switch);
        feet = findViewById(R.id.feet);
        inches = findViewById(R.id.inches);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = mass.getText().toString();
                String s2 = feet.getText().toString();
                String s3 = inches.getText().toString();
                if(s1.equals("")||s2.equals("")||s3.equals(""))
                    Toast.makeText(FPS.this, "All fields are mandatory.", Toast.LENGTH_SHORT).show();
                else {
                    String M = mass.getText().toString();
                    String F = feet.getText().toString();
                    String I = inches.getText().toString();
                    m = Double.parseDouble(M);
                    f=Double.parseDouble(F);
                    i = Double.parseDouble(I);
                    h = i + f*12;
                    bmi = (m * 703) / (h * h);
                    display.setText(getString(R.string.bmi,bmi));
                    if (bmi < 18.5) {
                        tv.setText("You are Underweight.");
                        Toast.makeText(FPS.this, "You have Minimal Health Risk.", Toast.LENGTH_SHORT).show();
                    }
                    else if (bmi < 24.9 && bmi >= 18.5) {
                        tv.setText("You are Normal.");
                        Toast.makeText(FPS.this, "You have Minimal Health Risk.", Toast.LENGTH_SHORT).show();
                    }
                    else if (bmi < 29.9 && bmi >= 24.9) {
                        tv.setText("You are Overweight.");
                        Toast.makeText(FPS.this, "You have Increased Health Risk.", Toast.LENGTH_SHORT).show();
                    }
                    else if (bmi < 34.9 && bmi >= 29.9) {
                        tv.setText("You are Obese.");
                        Toast.makeText(FPS.this, "You have High Health Risk.", Toast.LENGTH_SHORT).show();
                    }
                    else if (bmi < 39.9 && bmi <= 34.9) {
                        tv.setText("You are Severely Obese!");
                        Toast.makeText(FPS.this, "You have Very High Health Risk!", Toast.LENGTH_SHORT).show();
                    }
                    else if (bmi >= 40) {
                        tv.setText("You are Morbidly Obese!");
                        Toast.makeText(FPS.this, "You have Extremely High Health Risk!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}