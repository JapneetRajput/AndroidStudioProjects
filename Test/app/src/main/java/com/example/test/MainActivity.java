package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {

    EditText player1;
    EditText player2;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        player1 = findViewById(R.id.editTextTextPersonName);
        player2 = findViewById(R.id.editTextTextPersonName2);
        submit = findViewById(R.id.button2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String player1Name = player1.getText().toString();
                String player2Name = player2.getText().toString();

                Intent intent = new Intent (PlayerSetup.this, GameDisplay.class);
                intent.putExtra("PLAYER_NAMES", new String[] {player1Name, player2Name});
                startActivity(intent);


            }
        });
    }
}


