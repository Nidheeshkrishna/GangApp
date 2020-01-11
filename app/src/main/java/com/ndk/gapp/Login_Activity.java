package com.ndk.gapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
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


import com.google.android.gms.common.api.GoogleApiClient;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Login_Activity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText edt_email, edt_password;
    private Button signInButton, fb, phonelogin;
    //LoginButton fbButton;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 1;
    //private FirebaseAuth mAuth;
    String mobno, email, password;
    String idToken;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    //private CallbackManager callbackManager;
    //private CallbackManager mCallbackManager;
    AlertDialog.Builder alertDialogBuilder;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    //AccessToken accessToken;
    String code="";

    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mVerificationId;
    private FirebaseAuth mAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        alertDialogBuilder = new AlertDialog.Builder(this);

        //edt_email = findViewById(R.id.edt_email);
        signInButton = findViewById(R.id.sign_in_button);
        //fbButton=(LoginButton) findViewById(R.id.btn_fb_login);
        phonelogin = findViewById(R.id.btn_sign_iotp);
        //edt_password = findViewById(R.id.edt_password);


        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // FacebookSdk.sdkInitialize(this.getApplicationContext());
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            Log.e("hghgfffcdd",name);

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
        signInButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                //email = edt_email.getText().toString();
               // password = edt_password.getText().toString();
                //signin(email, password);
                // toolbar.setBackground(colorAccent).color.colorAccent);
                Intent i = new Intent(Login_Activity.this, GmailLoginActivity.class);
                i.putExtra("OTP",code);
                startActivity(i);
                //}
            }
        });
        //facebook login ///////////

        phonelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //edt_password.setVisibility(View.GONE);
                //if(edt_email.getText().length()>1) {
               // mobno = edt_email.getText().toString();

                //sendVerificationCode(mobno);
                //String code = edt_password.getText().toString().trim();
                //verifyVerificationCode(code);

                Intent i = new Intent(Login_Activity.this, PhoneLoginActivity.class);
                i.putExtra("OTP",code);
                startActivity(i);
                //}


            }
        });



      /*  fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                *//*callbackManager = CallbackManager.Factory.create();

                fbButton.setReadPermissions("email");

                fbButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookAccessToken(loginResult.getAccessToken());
                        Toast.makeText(Login_Activity.this, "Authentication Passed.", Toast.LENGTH_SHORT).show();
                        // App code
                    }
                    @Override
                    public void onCancel() {
                        // App code
                    }
                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });*//*
                fblogin();
                //LoginManager.getInstance().logInWithReadPermissions(Login_Activity.this, Arrays.asList("public_profile,email"));
            }
        });*/


    }






   /* public void fblogin() {
        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        // fbButton = findViewById(R.id.buttonFacebookLogin);
        fbButton.setReadPermissions("email", "public_profile");
        fbButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                //handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });
// ...


    }*/

    /*private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);
        // [START_EXCLUDE silent]
        //showProgressBar();
        // [END_EXCLUDE]

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Login_Activity.this, "Authentication Passed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(Login_Activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // [START_EXCLUDE]
                        //hideProgressBar();
                        // [END_EXCLUDE]
                    }
                });
    }*/








    // [START resend_verification]
    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }

    // [START sign_in_with_phone]






}
