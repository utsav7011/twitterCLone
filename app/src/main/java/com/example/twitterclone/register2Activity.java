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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class register2Activity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    ImageButton btnBack;
    EditText uName;
    EditText password;

    Button finish;

    String name, email, dob;

    //    firebase Auth:
    private FirebaseAuth mAuth;


//    @Override
//    public void onStart() {
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
////            / automatically login user
//
//        }
//        super.onStart();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        FirebaseApp.initializeApp(this);

        btnBack = findViewById(R.id.btn_back);
        uName = findViewById(R.id.edt_username);
        password = findViewById(R.id.edt_password);

        finish = findViewById(R.id.btnFinish);

        name = getIntent().getStringExtra("name").toString().trim();
        email = getIntent().getStringExtra("email").toString().trim();
        dob = getIntent().getStringExtra("dob").toString().trim();
        mAuth = FirebaseAuth.getInstance();
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate(uName.getText().toString().trim(),
                        password.getText().toString().trim())) {
                    register(email.trim(), password.getText().toString().trim());

                } else {
                    Toast.makeText(register2Activity.this, "Fill in correct details", Toast.LENGTH_SHORT).show();
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

    void register(String email, String pass) {
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Create user with email and passwod success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(register2Activity.this, "user successfully created ", Toast.LENGTH_LONG).show();
                            nextPage(user);
                        } else {
                            try {
                                throw Objects.requireNonNull(task.getException());
                            } catch (FirebaseAuthUserCollisionException e) {
                                Toast.makeText(register2Activity.this, "Alread registered and please login ", Toast.LENGTH_SHORT).show();;
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.w(TAG, "CreateUserwithEmailAndAsswordFailure" + task.getException());
                            }
                        }
                    }
                });
    }



    void nextPage(FirebaseUser user) {
        // go to dashboard after signin sccessful: :
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("users");
        userRef.child(user.getUid()).child("name").setValue(name);
        userRef.child(user.getUid()).child("dob").setValue(dob.toString());
        userRef.child(user.getUid()).child("email").setValue(email.toString());
        userRef.child(user.getUid()).child("username").setValue(uName.getText().toString());

        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("isUserLogged", true).commit();


        Intent intent =new Intent(register2Activity.this, homeActivity.class);
        startActivity(intent);
    }
}