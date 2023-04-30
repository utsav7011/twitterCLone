package com.example.twitterclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    ImageButton btn_back;
    Button btnNext;
    TextView edt_name;
    TextView edt_email;
    TextView edt_dob;

    Calendar calendar;

    String dob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_back = findViewById(R.id.btn_back);
//        code for login Page:
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        code for authentication through firebase ;

        btnNext = findViewById(R.id.btn_next);
        edt_name = findViewById(R.id.edt_name);
        edt_email = findViewById(R.id.edt_email);
        edt_dob = findViewById(R.id.edt_dob);

//        calendar = Calendar.getInstance();
//        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                calendar.set(Calendar.YEAR ,year);
//                calendar.set(Calendar.MONTH ,month);
//                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                updateDobEdt();
//            }
//        };
//        edt_dob.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new DatePickerDialog(RegisterActivity.this, date,
//                        calendar.get(Calendar.YEAR),
//                        calendar.get(Calendar.MONTH),
//                        calendar.get(Calendar.DAY_OF_MONTH));
//            }
//        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate(edt_name.getText().toString(),
                        edt_email.getText().toString(),
                        edt_dob.getText().toString())){
                    Intent intent = new Intent(RegisterActivity.this, register2Activity.class);
                    intent.putExtra("name", edt_name.getText().toString());
                    intent.putExtra("email", edt_email.getText().toString());
                    intent.putExtra("dob", edt_dob.getText().toString());
                    startActivity(intent);
                }else{
                    Toast.makeText(RegisterActivity.this, "Fill All the details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean validate(String name, String email, String dob){
        if (name.equals("") || email.equals("") || dob.equals("")){
            Toast.makeText(this, "Fill all the feilds", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }

    void updateDobEdt(){
        String dateFormat  = "mm/dd/yyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
        edt_dob.setText(simpleDateFormat.format(calendar.getTime()));
        dob = simpleDateFormat.format(calendar.getTime());
    }

}