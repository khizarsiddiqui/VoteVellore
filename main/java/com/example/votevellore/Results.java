package com.example.votevellore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class Results extends AppCompatActivity {
    TextView t1,t2;
    DBConnection db;
    String vid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        t1=(TextView)findViewById(R.id.textView15);
        t2=(TextView)findViewById(R.id.textView17);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();//Getting voter id from home page
        if(b!=null)
        {
            vid =(String) b.get("voterid");
        }
        db=new DBConnection(Results.this);
        Cursor cursor=db.getUserVoted(vid);
        if(cursor.moveToFirst())
        {
            String name=cursor.getString(0);
            t1.setText(name);
        }
        Cursor pcursor=db.getLeading();
        if(cursor.moveToFirst())
        {
            String name=cursor.getString(0);
            t2.setText(name);
        }
    }

}