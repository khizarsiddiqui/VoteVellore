package com.example.votevellore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddParty extends AppCompatActivity {
    Button b1;
    EditText e1,e2;
    DBConnection db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_party);
        b1=(Button)findViewById(R.id.addpartyb);
        e1=(EditText) findViewById(R.id.cname);
        e2=(EditText) findViewById(R.id.pname);
        db=new DBConnection(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callInsert();
            }
        });
    }
    public void callInsert()
    {
        String cn=e1.getText().toString();
        String pn=e2.getText().toString();
        Boolean checkinsert=db.insertParty(pn,cn);
        if((!(cn.equals("")))&&(!(pn.equals("")))){
            if(checkinsert){
                Toast.makeText(this,"Party Added!!",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, "Error adding party!!", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "All fields are required!!", Toast.LENGTH_LONG).show();
        }
    }
}