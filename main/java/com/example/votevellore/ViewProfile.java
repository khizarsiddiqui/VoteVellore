package com.example.votevellore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewProfile extends AppCompatActivity {
    Button b2;
    EditText e1,e2;
    String vid;
    DBConnection db;
    TextView t1,t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();//Getting voter id from home page
        if(b!=null)
        {
            vid =(String) b.get("voterid");
        }
        b2=(Button)findViewById(R.id.button2);
        e1=(EditText)findViewById(R.id.npass);
        e2=(EditText)findViewById(R.id.cpass);
        t1=(TextView)findViewById(R.id.textView6);
        t2=(TextView)findViewById(R.id.textView7);
        t3=(TextView)findViewById(R.id.textView8);

        db=new DBConnection(this);
        Cursor cursor=db.getUserprofile(vid);
        if(cursor.moveToFirst())
        {
            String name=cursor.getString(1);
            String aid=cursor.getString(2);
            t1.setText("Name: "+name);
            t2.setText("NIC ID: "+aid);
            t3.setText("Voter ID: "+vid);
        }
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    updatePass();
            }
        });
    }
    public void updatePass()
    {

        String p=e1.getText().toString();
        String cp=e2.getText().toString();
        Boolean updated=db.updatePassword(vid,p);

        if((!(p.equals(""))) && (!(cp.equals("")))){
           if(p.equals(cp)){
               if(updated==true)
                   Toast.makeText(this,"Password updated!!",Toast.LENGTH_LONG).show();
               else
                   Toast.makeText(this,"Error updating password!!",Toast.LENGTH_LONG).show();
           }else{
               Toast.makeText(this,"Both passwords must be same!!",Toast.LENGTH_LONG).show();
           }
        }else{
            Toast.makeText(this,"All fields are required!!",Toast.LENGTH_LONG).show();
        }
    }
}