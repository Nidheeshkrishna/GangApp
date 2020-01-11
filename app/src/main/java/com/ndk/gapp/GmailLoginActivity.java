package com.ndk.gapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;

public class GmailLoginActivity extends AppCompatActivity {

    TextInputEditText edt_email, edt_password,edt_username;
    private String password,username;
    private String email;
    private Button signInButton, fb, phonelogin;
    String email_id;
    private FirebaseAuth mAuth;
    private String mUsername;
    private ChildEventListener mChildEventListener;

    boolean status;
    SharedPreferences.Editor myEdit;
    // Storing data into SharedPreferences
    SharedPreferences sharedpreferences;

    // Creating an Editor object
// to edit(write to the file)



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_gmail);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // toolbar.setBackgroundColor(R.color.colorred);
        sharedpreferences = getSharedPreferences("Login_data", Context.MODE_PRIVATE);

        edt_email = findViewById(R.id.edt_email);
        //edt_username=findViewById(R.id.edt_userName);
        signInButton = findViewById(R.id.btn_Login_gmail);
        //fbButton=(LoginButton) findViewById(R.id.btn_fb_login);
        edt_password = findViewById(R.id.edt_password);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // FacebookSdk.sdkInitialize(this.getApplicationContext());
        if (user != null) {


            //onSignedInInitializer(user.getDisplayName());
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            email_id= user.getEmail();
            boolean emailVerified = user.isEmailVerified();
            Toast.makeText(GmailLoginActivity.this,""+user.isEmailVerified(), Toast.LENGTH_SHORT).show();
           // Log.e(String.valueOf(GmailLoginActivity.this),"Name"+name);
            status=true;
            Uri photoUrl = user.getPhotoUrl();


            myEdit = sharedpreferences.edit();
            myEdit.putString("Email_id",user.getEmail());
            myEdit.putBoolean("status",status);
            myEdit.commit();



            // Check if user's email is verified


            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }

        signInButton.setOnClickListener(new View.OnClickListener() {


            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                email = edt_email.getText().toString();
                password = edt_password.getText().toString();
                //username=edt_username.getText().toString();
                signin(email,password);
                // toolbar.setBackground(colorAccent).color.colorAccent);
            }


        });
    }

        public void signin(String eid, String pass) {
            mAuth.signInWithEmailAndPassword(eid,pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                status=true;

                                Intent i = new Intent(GmailLoginActivity.this, LocationActivity.class);
                                startActivity(i);

                            } else {
                                // If sign in fails, display a message to the user.
                                signup(email,password);

                            }


                        }
                    });

        }

    private void signup(String email, String password) {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d(TAG, "createUserWithEmail:success");
                                status=true;
                                FirebaseUser user = mAuth.getCurrentUser();
                                //updateUI(user);
                            } else {

                            }

                            // ...
                        }
                    });


        }


    /*private void onSignedInInitializer(String displayName) {
        mUsername = displayName;
        if (mChildEventListener == null)
            addChildEventListener();
        .addChildEventListener(mChildEventListener);

    }*/
}
