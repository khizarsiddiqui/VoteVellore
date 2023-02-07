package com.example.votevellore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ViewParties extends AppCompatActivity {
    DBConnection controllerdb = new DBConnection(this);
    SQLiteDatabase db;

    private ArrayList<String> Name = new ArrayList<String>();
    private ArrayList<String> CName = new ArrayList<String>();

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_parties);
        lv = (ListView) findViewById(R.id.lvParties);
    }
    @Override
    protected void onResume() {
        displayData();
        super.onResume();
    }
    private void displayData() {
        db = controllerdb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM  Parties",null);

        Name.clear();
        CName.clear();

        if (cursor.moveToFirst()) {
            do {
                Name.add(cursor.getString(cursor.getColumnIndex("name")));
                CName.add(cursor.getString(cursor.getColumnIndex("candidate")));
            } while (cursor.moveToNext());
        }
        CustomAdapter ca = new CustomAdapter(ViewParties.this, Name,CName);
        lv.setAdapter(ca);
        //code to set adapter to populate list
        cursor.close();
    }
}