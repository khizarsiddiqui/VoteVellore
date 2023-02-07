package com.example.votevellore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    DBConnection controldb;
    SQLiteDatabase db;


    private ArrayList<String> Name = new ArrayList<String>();
    private ArrayList<String> CName = new ArrayList<String>();

    public CustomAdapter(Context  context,ArrayList<String> Name, ArrayList<String> CName)
    {
        this.mContext = context;

        this.Name = Name;
        this.CName=CName;
    }
    @Override
    public int getCount() {
        return Name.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final    viewHolder holder;
        controldb =new DBConnection(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.single_item, null);
            holder = new viewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.party_name);
            holder.cname = (TextView) convertView.findViewById(R.id.cand_name);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.name.setText(Name.get(position));
        holder.cname.setText(CName.get(position));
        return convertView;
    }
    public class viewHolder {
        TextView name;
        TextView cname;
    }
}
