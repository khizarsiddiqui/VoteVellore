package com.example.votevellore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddVoter extends AppCompatActivity {
    Button b1;
    EditText e1,e2;
    DBConnection db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_voter);
        b1=(Button)findViewById(R.id.button2);
        e1=(EditText) findViewById(R.id.aaadhar);
        e2=(EditText) findViewById(R.id.avoter);
        db=new DBConnection(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callInsert();
            }
        });
    }
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public void callInsert(){
        String aadharTXT=e1.getText().toString();
        String voterTXT=e2.getText().toString();

        boolean isNumericA = isNumeric(aadharTXT);
        boolean isNumericV = isNumeric(voterTXT);
        int flag=0;
        if(isNumericA==false)
        {
            flag=flag+1;
            Toast.makeText(this,"NIC must be numeric",Toast.LENGTH_LONG).show();
        }
        if(aadharTXT.length()<13 || aadharTXT.length()>13){
            Toast.makeText(this,"Exact 13 digits allowed in NIC number",Toast.LENGTH_LONG).show();
            flag=1;
        }
        if(isNumericV==false)
        {
            flag=flag+1;
            Toast.makeText(this,"Voter must be numeric",Toast.LENGTH_LONG).show();
        }
        if(voterTXT.length()<7 || voterTXT.length()>7){
            Toast.makeText(this,"Exact 7 digits allowed in voter id ",Toast.LENGTH_LONG).show();
            flag=flag+1;
        }
            if(flag==0){
                Boolean ifexists=db.checkVoterinDB(aadharTXT,voterTXT);
                Boolean checkinsert=db.insertVoter(aadharTXT,voterTXT);
                if(ifexists){
                    Toast.makeText(this,"Voter already exists in database!!",Toast.LENGTH_LONG).show();
                }else{
                    if(checkinsert){
                        Toast.makeText(this,"Voter Added!!",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(this, "Error adding voter!!", Toast.LENGTH_LONG).show();
                    }
                }
            }
    }
}