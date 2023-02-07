package com.example.votevellore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openRegister();
            }
        });
        b2=(Button)findViewById(R.id.button4);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
        b3=(Button)findViewById(R.id.button10);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdminLogin();
            }
        });
    }
    public void openRegister(){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
    public void openLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    public void openAdminLogin(){
        Intent intent = new Intent(this, AdminLogin.class);
        startActivity(intent);
    }
}