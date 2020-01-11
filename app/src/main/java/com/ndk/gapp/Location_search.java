package com.ndk.gapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.ndk.gapp.Adapters.user_adapter;
import com.ndk.gapp.Entities.MyListData;

import java.util.ArrayList;

public class Location_search extends AppCompatActivity {
    private FirebaseDatabase mFirebaseLocationSearch;
    private FirebaseStorage mFirebaseStorageLocation;
    private DatabaseReference mLocationDbReferenceLocation;
    RecyclerView recyclerView;
    private ChildEventListener mChildEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locion_user);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //recyclerView=findViewById(R.id.recycler_view);

        mFirebaseStorageLocation = FirebaseStorage.getInstance();
        mFirebaseLocationSearch = FirebaseDatabase.getInstance();
        mLocationDbReferenceLocation = mFirebaseLocationSearch.getReference().child("db_location");

        addChildEventListener();

    }
    public void addChildEventListener() {


        mChildEventListener = new ChildEventListener() {
            private ArrayList<MyListData> td;
            private ArrayList<Model_Location> listaPedido;

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                MyListData ml = new MyListData();
                //td.clear();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    childDataSnapshot.child("id").getValue();


                    if (childDataSnapshot.getKey().equals("Email_id"))
                    {
                        ml.setEmail_id(String.valueOf(childDataSnapshot.getValue()));
                    }
                    if (childDataSnapshot.getKey().equals("Latitude"))
                    {
                        ml.setLatitude(String.valueOf(childDataSnapshot.getValue()));
                    }
                    if (childDataSnapshot.getKey().equals("Logitude"))
                    {
                        ml.setLogitude(String.valueOf(childDataSnapshot.getValue()));
                    }
                    if (childDataSnapshot.getKey().equals("status"))
                    {
                        ml.setStatus(String.valueOf(childDataSnapshot.getValue()));
                    }
                    // String areaName = childDataSnapshot.child("id").getValue(String.class);
                    // Log.v("msg123456", "area___" + areaName);
                }
                td.add(ml);

                //Log.v("msg",""+td);   //gives the value for given keyname
                ArrayList<String> str=new ArrayList<String>();
                //str.clear();
                for (MyListData td1 : td) {
                    //Log.v("msg", "" + td1.getLat());
                    //ArrayList<String> str=new ArrayList<String>();
                    //str.add(td1.locname);
                    //Toast.makeText(LocationActivity.this, ""+td1.getId(), Toast.LENGTH_SHORT).show();
                }
                user_adapter uAdapter = new user_adapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, td);
                //spinner.setAdapter(customAdapter);

            }

            public ArrayList<Model_Location> getListaPedido() {
                return listaPedido;
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //listaPedido = new ArrayList<Model_Location>();
                // Model_Location p = dataSnapshot.getValue(Model_Location.class);
                // listaPedido.add(p);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        };

        mLocationDbReferenceLocation.addChildEventListener(mChildEventListener);
    }


}
