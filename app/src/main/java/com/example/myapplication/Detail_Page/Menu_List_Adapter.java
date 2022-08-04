package com.example.myapplication.Detail_Page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Menu_List_Adapter extends BaseAdapter {
    Context context;
    LayoutInflater mLayoutInflater;
    ArrayList<String> menu;

    public Menu_List_Adapter(Context context, ArrayList<String> menu){
        this.context = context;
        this.menu = menu;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
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
        return null;
    }
}
