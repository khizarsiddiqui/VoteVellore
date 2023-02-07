package com.example.votevellore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VotingPage extends AppCompatActivity {
    public  ArrayList<String> parties = new ArrayList<>();
    DBConnection dbc;
    SQLiteDatabase db;
    Button b1;
    public static String selected;
    CheckBox cb;
    String vid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_page);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();//Getting voter id from home page
        if(b!=null)
        {
            vid =(String) b.get("voterid");
        }
        dbc=new DBConnection(VotingPage.this);
        getParties();
        ConstraintLayout l=(ConstraintLayout) findViewById(R.id.ballot);
        RadioGroup r=(RadioGroup)findViewById(R.id.rgvote);
        r.setOrientation(RadioGroup.VERTICAL);
        RadioGroup.LayoutParams rl;
        for(int i=0;i<parties.size();i++){
            RadioButton r1=new RadioButton(this);
            r1.setText(parties.get(i));
            r1.setTextSize(25);
        //    r1.setButtonDrawable(R.drawable.radio_button_selector);

         //   r1.setPadding(100,0,0,0);
           // r1.setGravity(Gravity.CENTER_VERTICAL);
            rl=new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT,RadioGroup.LayoutParams.MATCH_PARENT);
            r.addView(r1,rl);
        }
        r.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId=r.getCheckedRadioButtonId();
                RadioButton rb= (RadioButton) findViewById(selectedId);
                selected = rb.getText().toString();
                Toast.makeText(getBaseContext(),selected,Toast.LENGTH_LONG).show();
            }
        });
        b1=(Button)findViewById(R.id.button15);
        cb=(CheckBox)findViewById(R.id.checkBox);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callInsertVote();
            }
        });
    }
    public void callInsertVote(){

        if((!(selected.equals("")))){
            if(cb.isChecked()){
                Boolean checkinsert=dbc.insertVote(vid,selected);
                if(checkinsert){
                    Toast.makeText(this,"Thank you for Voting",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this, "Seems like you already voted!!", Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(this, "Please check the checkbox", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "You haven't selected your party!", Toast.LENGTH_LONG).show();
        }
    }
    //To get party names from database and store it in the string array
    public void getParties(){
        db = dbc.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM  Parties",null);

        if (cursor.moveToFirst()) {
            do {
                parties.add(cursor.getString(cursor.getColumnIndex("name")));
            } while (cursor.moveToNext());
        }
    }
}