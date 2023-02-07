package com.example.votevellore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    Button b1;
    DBConnection db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        e1=(EditText)findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.aadhar);
        e3=(EditText)findViewById(R.id.voter);
        e4=(EditText)findViewById(R.id.password);
        e5=(EditText)findViewById(R.id.cpass);
        b1=(Button)findViewById(R.id.button);
        db=new DBConnection(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUser();
            }
        });
    }
    public void insertUser(){
        String name=e1.getText().toString();
        String a=e2.getText().toString();
        String v=e3.getText().toString();
        String p=e4.getText().toString();
        String cp=e5.getText().toString();
        if((!(name.equals(""))) && (!(a.equals(""))) && (!(v.equals(""))) && (!(p.equals(""))) && (!(cp.equals("")))){
            if(p.equals(cp)){
                Boolean ifexisting=db.checkUserinDB(a,v);
               if(ifexisting==false){
                   Boolean ifexists=db.checkAVinDB(a,v);
                   Boolean checkinsert=db.addUser(name,a,v,p);
                   if(ifexists && checkinsert){
                       Toast.makeText(this,"You have been successfully registered!!",Toast.LENGTH_LONG).show();
                       Intent intent = new Intent(this, Login.class);
                       startActivity(intent);
                   }else{
                       Toast.makeText(this,"Sorry your NIC and voter id are not valid or dont match!!",Toast.LENGTH_LONG).show();
                   }
               }else{
                   Toast.makeText(this,"You are already registered, go to Login!!",Toast.LENGTH_LONG).show();
               }
            }else{
                Toast.makeText(this,"Passwords dont match!!",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"All fields are required!!",Toast.LENGTH_LONG).show();
        }
    }
}