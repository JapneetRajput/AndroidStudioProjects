package com.example.matplotlib;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.textfield.TextInputEditText;

public class MatPlotLib extends AppCompatActivity {

    TextInputEditText inputX,inputY;
    Button submit;
    ImageView image;
    String x_data,y_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat_plot_lib);
        getSupportActionBar().hide();

        inputX = findViewById(R.id.xData);
        inputY = findViewById(R.id.yData);
        submit = findViewById(R.id.submit);
        image = findViewById(R.id.imageView);

        if(!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        Python py = Python.getInstance();
        PyObject pyObj = py.getModule("mat");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                x_data = inputX.getText().toString();
                y_data = inputY.getText().toString();

//                PyObject obj = pyObj.callAttr("main",x_data,y_data);
//
//                String str = obj.toString();
//
//                byte data[] = android.util.Base64.decode(str, Base64.DEFAULT);
//
//                Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length);
//
//                image.setImageBitmap(bmp);
            }
        });
    }
}