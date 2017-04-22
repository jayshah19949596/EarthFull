package com.example.jaysh.earthfull;

import android.support.v7.app.AppCompatActivity;

import com.example.jaysh.earthfull.data.*;
import com.example.jaysh.earthfull.volunteer.VolunteerHome;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LogInHome extends AppCompatActivity implements View.OnClickListener {

    Button buttonSignIn, buttonSignUp, buttonLoginProfile;
    DBMgr DBMgr;
    public static Cursor userCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Log in Home", "onCreate() - LogIn");
        setContentView(R.layout.log_in_home);

        DBMgr = DBMgr.getInstance(this);
        DBMgr = DBMgr.open();

        buttonSignIn = (Button) findViewById(R.id.button_sign_in);
        buttonSignUp = (Button) findViewById(R.id.button_sign_up);

        buttonSignIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_sign_in:
                signIn();
                break;

            case R.id.button_sign_up:
                signUp();
                break;
        }
    }

    public void signUp(){
        Intent intentSignUP = new Intent(getApplicationContext(),
                SignUp.class);
        startActivity(intentSignUP);
    }

    public void signIn() {
        final Dialog dialog = new Dialog(LogInHome.this);
        dialog.setContentView(R.layout.activity_log_in);
        dialog.setTitle("Login");
        buttonLoginProfile = (Button) dialog.findViewById(R.id.button_log_in);
        buttonLoginProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Toast.makeText(LogInHome.this,
//                        "User Name or Password does not match",
//                        Toast.LENGTH_LONG).show();

                final EditText editTextUserName = (EditText) dialog
                        .findViewById(R.id.editTextUserNameToLogin);

                final EditText editTextPassword = (EditText) dialog
                        .findViewById(R.id.editTextPasswordToLogin);

                String emailID = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();


                String storedPassword = DBMgr.getPassword(emailID);
                userCursor = DBMgr.getSinlgeUser(emailID);

//                Log.d("Log in Home", storedPassword);
//                Log.d("Log in Home", password);

                if (userCursor.getCount()==0)
                {
                    Toast.makeText(LogInHome.this,
                            "User does Not Exist",
                            Toast.LENGTH_LONG).show();
                    return;

                }

                if (password.equals(storedPassword)){
                    Intent volunteerIntent = new Intent(LogInHome.this, VolunteerHome.class);
                    startActivity(volunteerIntent);
                }
                else{
                    Toast.makeText(LogInHome.this,
                            "Password does not match",
                            Toast.LENGTH_LONG).show();
                    return;
                }


            }
        });

        dialog.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

