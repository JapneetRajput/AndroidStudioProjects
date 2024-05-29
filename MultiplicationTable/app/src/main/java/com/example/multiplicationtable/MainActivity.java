package com.example.multiplicationtable;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private View view;
    private EditText editText;
    private TextView textview1, textview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextTextPersonName);
        textview1 = findViewById(R.id.textView3);
        textview2 = findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                int a = Integer.parseInt(s);
                textview1.setText("The multiplication table of " + a + " :");
                StringBuilder table = new StringBuilder();
                for(int i = 1; i<=10; i++){
                    table.append(a + " * " + i + " = " + a*i +"\n");
                }
                textview2.setText(table);
            }
        });
    }
}