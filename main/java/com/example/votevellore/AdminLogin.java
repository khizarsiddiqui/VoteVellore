package com.example.votevellore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    Button b1;
    EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        b1=(Button)findViewById(R.id.button2);
        e1=(EditText)findViewById(R.id.auser);
        e2=(EditText)findViewById(R.id.apass);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin_login();
            }
        });
    }
    public void admin_login(){
        String user=e1.getText().toString().trim();
        String pass=e2.getText().toString().trim();
        if(user.equals("admin") && pass.equals("admin"))
        {
            Toast.makeText(this,"Logging in!",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, AdminHome.class);
            startActivity(intent);
        }else {
            Toast.makeText(this,"Invalid Details!!",Toast.LENGTH_LONG).show();
        }
    }


}