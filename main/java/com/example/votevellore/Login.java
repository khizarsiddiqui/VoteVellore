package com.example.votevellore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button b1;
    EditText e1,e2;
    DBConnection db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1=(EditText)findViewById(R.id.aaadhar);
        e2=(EditText)findViewById(R.id.avoter);
        b1=(Button)findViewById(R.id.button2);
        db=new DBConnection(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });
    }
    public void openHome(){
        String v=e1.getText().toString();
        String p=e2.getText().toString();
        if((!(v.equals("")))&&(!(p.equals("")))){
            Boolean ifexisting=db.LoginCheck(v,p);
            if(ifexisting){
                Toast.makeText(this,"Logging in!!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, Home.class);
                intent.putExtra("voterid",v);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Not registered or Invalid details!!",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"All fields are required!!",Toast.LENGTH_LONG).show();
        }
    }
}