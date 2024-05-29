package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textview;
    private EditText editText, editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        textview=findViewById(R.id.textView4);
        editText=findViewById(R.id.Number1);
        editText1=findViewById(R.id.Number2);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          String s=editText.getText().toString();
                                          int a=Integer.parseInt(s);
                                          s=editText1.getText().toString();
                                          int b=Integer.parseInt(s);
                                          int c=a+b;
                                          textview.setText("The sum is: "+c);
                                      }
                                  }
        );

    }
}