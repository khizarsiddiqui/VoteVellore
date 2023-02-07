package com.example.votevellore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;

public class DBConnection extends SQLiteOpenHelper {
    Context context;

    public DBConnection(@Nullable Context context) {
        super(context,"Voters.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Voters(_id INTEGER PRIMARY KEY AUTOINCREMENT,aadhar text unique not null, voterid text unique not null)");
        db.execSQL("create Table Users(_id INTEGER PRIMARY KEY AUTOINCREMENT,name text not null, aadharid text unique not null, voterid text unique not null, password text not null)");
        db.execSQL("create Table Parties(_id INTEGER PRIMARY KEY AUTOINCREMENT,name text not null unique, candidate text not null)");
        db.execSQL("create Table Votes(_id INTEGER PRIMARY KEY AUTOINCREMENT,voterid text not null unique, partyname text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Voters");
        db.execSQL("drop Table if exists Users");
        db.execSQL("drop Table if exists Parties");
        db.execSQL("drop Table if exists Votes");
        // create new tables
        onCreate(db);
    }
    //Adding a new voter by admin
    public Boolean insertVoter(String aadhar, String voterid){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("aadhar",aadhar);
        contentValues.put("voterid",voterid);
        long result=db.insert("Voters",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return  true;
        }
    }

    //Adding a new vote
    public Boolean insertVote(String voterid, String pname){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("voterid",voterid);
        contentValues.put("partyname",pname);
        long result=db.insert("Votes",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return  true;
        }
    }

    //Adding a new party by admin
    public Boolean insertParty(String name, String cname){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("candidate",cname);
        long result=db.insert("Parties",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return  true;
        }
    }
    //checking whether voter already in database when inserting by admin
    public boolean checkVoterinDB(String aadhar, String voter) throws SQLException
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mcursor = db.rawQuery("SELECT * FROM Voters WHERE aadhar =? or voterid =?", new String[]{aadhar,voter});
        if (mcursor!=null)
        {
            if (mcursor.getCount()>0)
            {
                return true;
            }
        }
        return false;
    }

    //user registration
    public Boolean addUser(String name,String aadhar, String voterid, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("aadharid",aadhar);
        contentValues.put("voterid",voterid);
        contentValues.put("password",password);
        long result=db.insert("Users",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return  true;
        }
    }
    //checking whether voter already in database when when registering
    public boolean checkAVinDB(String aadhar, String voter) throws SQLException
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mcursor = db.rawQuery("SELECT * FROM Voters WHERE aadhar =? and voterid =?", new String[]{aadhar,voter});
        if (mcursor!=null)
        {
            if (mcursor.getCount()>0)
            {
                return true;
            }
        }
        return false;
    }

    //user registered check
    public boolean checkUserinDB(String aadhar, String voter) throws SQLException
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mcursor = db.rawQuery("SELECT * FROM Users WHERE aadharid =? or voterid =?", new String[]{aadhar,voter});
        if (mcursor!=null)
        {
            if (mcursor.getCount()>0)
            {
                return true;
            }
        }
        return false;
    }
    //user registered check
    public boolean LoginCheck(String voter, String psw) throws SQLException
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mcursor = db.rawQuery("SELECT * FROM Users WHERE voterid =? and password =?", new String[]{voter,psw});
        if (mcursor!=null)
        {
            if (mcursor.getCount()>0)
            {
                return true;
            }
        }
        return false;
    }

    //view users profile
    public Cursor getUserprofile(String voterid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mcursor = db.rawQuery("SELECT * FROM Users WHERE voterid =?", new String[]{voterid});
        if (mcursor!=null)
        {
            if (mcursor.getCount()==1)
            {
                return mcursor;
            }
        }
        return null;
    }

    //Update password
    public Boolean updatePassword(String voterid, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password",pass);
        Cursor cursor=db.rawQuery("SELECT * from Users where voterid=?",new String[]{voterid});

        long result=db.update("Users",cv,"voterid=?",new String[]{voterid});
        if(result==-1){
            return false;
        }else {
            return true;
        }
    }
    //view user voted party
    public Cursor getUserVoted(String voterid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mcursor = db.rawQuery("SELECT partyname FROM Votes WHERE voterid =?", new String[]{voterid});
        if (mcursor!=null)
        {
            if (mcursor.getCount()==1)
            {
                return mcursor;
            }
        }
        return null;
    }
    public Cursor getLeading(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mcursor = db.rawQuery("SELECT partyname FROM Votes group by partyname order by count(partyname) desc ", null);
        if (mcursor!=null)
        {
            if (mcursor.getCount()==1)
            {
                return mcursor;
            }
        }
        return null;
    }

}
