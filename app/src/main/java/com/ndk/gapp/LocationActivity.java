package com.ndk.gapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashMap;


public class LocationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    Spinner spinner;
    private FirebaseDatabase mFirebaseDatabase,mFirebaseDatabaseMsg;
    private FirebaseStorage mFirebaseStorage,mFirebaseStoragemsg;
    private DatabaseReference mLocationDbReference,mLocationDbReferencemsg;

    ArrayList<Model_Location> td;
    private FirebaseAuth mfirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthstateListener;

    private ChildEventListener mChildEventListener;
    String localat;
    String loclog;
    Model_Location LocationInfo;
    private String LocattionSelected;

    SharedPreferences sh;

    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sh = this.getSharedPreferences("Login_data",MODE_PRIVATE);
        mfirebaseAuth = FirebaseAuth.getInstance();
        spinner = (Spinner) findViewById(R.id.Spnr_location);

        // Spinner click listener
        //spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        mFirebaseStorage = FirebaseStorage.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mLocationDbReference = mFirebaseDatabase.getReference().child("db_location");


        mFirebaseStoragemsg = FirebaseStorage.getInstance();
        mFirebaseDatabaseMsg=FirebaseDatabase.getInstance();
        mLocationDbReferencemsg= mFirebaseDatabaseMsg.getReference().child("db_Current_user");

        //td= new ArrayList<Model_Location>();


        //FirebaseDatabase database = FirebaseDatabase.getInstance();

        //DatabaseReference myRef = database.getReference("db_Location");

        //mLocationDbReference.child("Id").setValue(1);
        //mLocationDbReference.child("Location").setValue("White Field");


        ///myRef.child("latitude").setValue("12.9698");
        // myRef.child("logitude").setValue("77.7500");
        // myRef.child("Name").setValue("Nidheesh");




        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(this);


        td = new ArrayList<Model_Location>();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //if (td != null && !td.isEmpty()) {
                LocattionSelected = td.get(position).getLocname();
                localat=td.get(position).getLat();
                loclog=td.get(position).getLog();
                //Log.v("msg", ""+latinc+"__"+latinc123);
                // priority_id = Project_proirity.get(position).getprioity_Id();
                //}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        addChildEventListener();
      /*  LocationInfo = new Model_Location(id, Locname,Loc,Logi,status);
        mLocationDbReference.push().setValue(LocationInfo);

       // mMessageEditText.setText("");
        mLocationDbReference.addChildEventListener(mChildEventListener);*/


        mAuthstateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {


                FirebaseUser user = firebaseAuth.getCurrentUser();


                if (user != null) {

                } else {
                    //user is signed out
                    // Choose authentication providers
                    //onSignedOutCleanUp();
                  /*  List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.EmailBuilder().build(),
                            new AuthUI.IdpConfig.GoogleBuilder().build());

                    // Create and launch sign-in intent
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(providers)
                                    .setIsSmartLockEnabled(false)
                                    .build(),
                            1);*/
                }


            }


        };


    }

    public void addChildEventListener() {


        mChildEventListener = new ChildEventListener() {
            private ArrayList<Model_Location> listaPedido;

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Model_Location ml = new Model_Location();
                //td.clear();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                   childDataSnapshot.child("id").getValue();
                    if (childDataSnapshot.getKey().equals("id"))
                    {
                        ml.setId(String.valueOf(childDataSnapshot.getValue()));
                    }
                    if (childDataSnapshot.getKey().equals("locname"))
                    {
                        ml.setLocname(String.valueOf(childDataSnapshot.getValue()));
                    }
                     if (childDataSnapshot.getKey().equals("log"))
                    {
                        ml.setLog(String.valueOf(childDataSnapshot.getValue()));
                    }
                    if (childDataSnapshot.getKey().equals("lat"))
                    {
                        ml.setLat(String.valueOf(childDataSnapshot.getValue()));
                    }
                   // String areaName = childDataSnapshot.child("id").getValue(String.class);
                   // Log.v("msg123456", "area___" + areaName);
                }
                td.add(ml);

                //Log.v("msg",""+td);   //gives the value for given keyname
                ArrayList<String> str=new ArrayList<String>();
                //str.clear();
                for (Model_Location td1 : td) {
                    Log.v("msg", "" + td1.getLat());
                    //ArrayList<String> str=new ArrayList<String>();
                    str.add(td1.locname);
                    //Toast.makeText(LocationActivity.this, ""+td1.getId(), Toast.LENGTH_SHORT).show();
                }
                CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, td);
                spinner.setAdapter(customAdapter);

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

        mLocationDbReference.addChildEventListener(mChildEventListener);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_notifications:
               // sh.getBoolean("status",false);

                //mLocationDbReferencemsg.child("Email_id").push().setValue(sh.getString("Email_id",""));
                //mLocationDbReferencemsg.child("status").push().setValue(sh.getBoolean("status",false));
                //mLocationDbReferencemsg.child("Logitude").push().setValue(localat);
               // mLocationDbReferencemsg.child("Latitude").push().setValue(loclog);

                HashMap<String,String> student=new HashMap<>();

                student.put("Email_id",sh.getString("Email_id",""));

                student.put("status", String.valueOf(sh.getBoolean("status",false)));
                student.put("Logitude", localat);
                student.put("Latitude", loclog);


                mLocationDbReferencemsg.push().setValue(student);


                Intent i = new Intent(LocationActivity.this, ChatActivity.class);
                startActivity(i);

                return true;
        }
        return false;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        td.clear();

    }
}
