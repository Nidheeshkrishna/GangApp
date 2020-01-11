package com.ndk.gapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Model_Location> {
    //
    private Context context;
    private ArrayList<Model_Location> assignd_managers=new ArrayList<Model_Location>();

    //private ArrayList<ReviewDataTask> Filterd_data;


    //private adapterData_assignedto.ArrayFilter mFilter;

   /* public Spinner_adapter(@NonNull Context context, int resource, ArrayList<ReviewDataTask> assignd_managers_List) {

        super(context, resource);
        this.context=context;
        //this.mOriginalValues=assignd_managers_List;
        this.assignd_managers = assignd_managers_List;
        //this.Filterd_data = new ArrayList<Spinner_adapter>(assignd_managers_List);
    }*/

    public CustomAdapter(Context applicationContext, int rs, ArrayList<Model_Location> td) {
        super(applicationContext,rs);
        this.context=applicationContext;
        this.assignd_managers=td;
    }


    @Override
    public int getCount() {
        return assignd_managers.size();
    }

    @Override
    public Model_Location getItem(int position) {
        return assignd_managers.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.itemlayout, parent, false);
        }
        Model_Location Owners = getItem(position);
        TextView strName = (TextView) view.findViewById(R.id.textview_item);
        strName.setText(Owners.getLocname());
        return view;
    }
}
