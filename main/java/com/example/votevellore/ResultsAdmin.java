package com.example.votevellore;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ResultsAdmin extends AppCompatActivity {
    DBConnection controllerdb = new DBConnection(this);
    SQLiteDatabase db;
    private ArrayList<String> Name = new ArrayList<String>();
    private ArrayList<String> Count = new ArrayList<String>();
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_admin);
        lv = (ListView) findViewById(R.id.lvresults);
    }
    protected void onResume() {
        displayData();
        super.onResume();
    }
    private void displayData() {
        db = controllerdb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT partyname,count(partyname) FROM  Votes group by partyname order by count(partyname) desc",null);

        Name.clear();
        Count.clear();

        if (cursor.moveToFirst()) {
            do {
                Name.add(cursor.getString(cursor.getColumnIndex("partyname")));
                Count.add(cursor.getString(cursor.getColumnIndex("count(partyname)")));
            } while (cursor.moveToNext());
        }
        CustomAdapter ca = new CustomAdapter(ResultsAdmin.this, Name,Count);
        lv.setAdapter(ca);
        //code to set adapter to populate list
        cursor.close();
    }
}