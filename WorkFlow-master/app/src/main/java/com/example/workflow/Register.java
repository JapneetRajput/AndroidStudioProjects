package com.example.workflow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText Pass,Rpass;
    AutoCompleteTextView Name, Sname, User, Email;
    Button register,login;
    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        Pass = findViewById(R.id.Password);
        Rpass = findViewById(R.id.RPassword);
        Name = findViewById(R.id.FirstName);
        Sname = findViewById(R.id.surname);
        User = findViewById(R.id.Username);
        Email = findViewById(R.id.Email);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        db=new DbHandler(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=User.getText().toString().trim();
                String password=Pass.getText().toString().trim();
                String rpassword=Rpass.getText().toString().trim();
                String fname=Name.getText().toString().trim();
                String sname=Sname.getText().toString().trim();
                String email=Email.getText().toString().trim();
                if(username.equals("")||password.equals("")||rpassword.equals("")||fname.equals("")||sname.equals("")||email.equals(""))
                    Toast.makeText(Register.this, "* All fields are mandatory", Toast.LENGTH_SHORT).show();
                else if(password.equals(rpassword)){
                    if(db.checkusername(username)){
                        db.addEmployee(fname,sname,username,password,email);
                        Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(Register.this, "Username already exists! Please Login", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Register.this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}