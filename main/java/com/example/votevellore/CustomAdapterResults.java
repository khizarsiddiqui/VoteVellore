package com.example.votevellore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterResults extends BaseAdapter {
    private Context mContext;
    DBConnection controldb;
    SQLiteDatabase db;


    private ArrayList<String> Name = new ArrayList<String>();
    private ArrayList<String> Count = new ArrayList<String>();

    public CustomAdapterResults(Context  context,ArrayList<String> Name, ArrayList<String> Count)
    {
        this.mContext = context;

        this.Name = Name;
        this.Count=Count;
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
        final CustomAdapterResults.viewHolder holder;
        controldb =new DBConnection(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.single_item_results, null);
            holder = new CustomAdapterResults.viewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.rparty);
            holder.count = (TextView) convertView.findViewById(R.id.count_party);
            convertView.setTag(holder);
        } else {
            holder = (CustomAdapterResults.viewHolder) convertView.getTag();
        }
        holder.name.setText(Name.get(position));
        holder.count.setText(Count.get(position));
        return convertView;
    }
    public class viewHolder {
        TextView name;
        TextView count;
    }
}

