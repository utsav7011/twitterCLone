package com.example.twitterclone;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    FirebaseAuth mAuth;
    ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(this);

        btn_back = findViewById(R.id.btn_back);

        mAuth = FirebaseAuth.getInstance();
        EditText edt_email = findViewById(R.id.login_email);
        EditText edt_password = findViewById(R.id.login_password);
//        code for back button :
        Button btn_next = findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate(edt_email.getText().toString().trim(),
                        edt_password.getText().toString().trim())) {
                    PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit().putBoolean("isUserLogged", true).commit();
                    login(edt_email.getText().toString().trim(),
                            edt_password.getText().toString().trim());

                } else {
                    Toast.makeText(LoginActivity.this, "Fill in correct details", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signin with Email and password Success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Success login", Toast.LENGTH_LONG).show();
                            Intent intent =new Intent(LoginActivity.this, homeActivity.class);
                            startActivity(intent);
                        } else {
                            Log.v(TAG, "sigin with email and pasword failure", task.getException());
                            Toast.makeText(LoginActivity.this, "login using email and passoword error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    boolean validate(String uname, String password) {
        if (uname.equals("") || password.equals("")) {
            Toast.makeText(this, "Fill all the feilds", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}