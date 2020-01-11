package com.ndk.gapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ndk.gapp.Entities.MyListData;
import com.ndk.gapp.R;

import java.util.ArrayList;

public class user_adapter extends RecyclerView.Adapter<user_adapter.ViewHolder>{
    private ArrayList<MyListData>  listdata;
Context context;
    // RecyclerView recyclerView;


    public user_adapter(Context applicationContext, int res, ArrayList<MyListData> td) {
        this.listdata=td;
        this.context=applicationContext;

    }

    @NonNull
    @Override
    public user_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull user_adapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
