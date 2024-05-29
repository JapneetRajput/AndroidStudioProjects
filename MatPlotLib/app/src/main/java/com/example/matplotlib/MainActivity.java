package com.example.matplotlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText input1,input2;
    Button button,matPlotLib;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        matPlotLib = findViewById(R.id.matPlotLib);
        input1 = findViewById(R.id.firstInput);
        input2 = findViewById(R.id.secondInput);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        if(!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        Python py = Python.getInstance();
        PyObject pyObj = py.getModule("script");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PyObject obj=pyObj.callAttr("main",input1.getText().toString(),input2.getText().toString());
                textView.setText(obj.toString());
            }
        });

        matPlotLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MatPlotLib.class));
                finish();
            }
        });
    }
}