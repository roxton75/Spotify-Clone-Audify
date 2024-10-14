package com.example.spotifyclone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText name, num, email, user, pass, re_pass;
    Button btn_register;
    TextView sign_in, TnC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.et_res_name);
        num = findViewById(R.id.et_res_number);
        email = findViewById(R.id.et_res_mail);
        user = findViewById(R.id.et_res_username);
        pass = findViewById(R.id.et_res_pass);
        re_pass = findViewById(R.id.et_res_renter_pass);

        sign_in = findViewById(R.id.tv_sign_in);
        TnC = findViewById(R.id.TnC);

        btn_register = findViewById(R.id.btn_register);

        sign_in.setOnClickListener(v -> {
            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        });

        btn_register.setOnClickListener(v -> {
            if (user.getText().toString().isEmpty()) {
                user.setError("This field can't be empty");
            } else if (user.getText().toString().length() < 6) {
                user.setError("Username must be greater than 6 letters");
            } else if (!user.getText().toString().matches(".*[a-z].*")) {
                user.setError("Username must contain atleast one lowercase letter");
            } else if (!user.getText().toString().matches(".*[A-Z].*")) {
                user.setError("Username must contain atleast one uppercase letter");
            }
//                else if (!user.getText().toString().matches(".*[0-9].*"))
//                {
//                    user.setError("Username must contain one number from 0-9");
//                }
            else if (!user.getText().toString().matches(".*@.*")) {
                user.setError("Username must contain '@' ");
            } else if (name.getText().toString().isEmpty()) {
                name.setError("This field can't be empty");
            } else if (num.getText().toString().isEmpty()) {
                num.setError("This field can't be empty");
            } else if (num.getText().toString().length() != 10) {
                num.setError("The number should be 10 digits");
            } else if (!email.getText().toString().contains("@") || !email.getText().toString().contains("gmail.com")) {
                email.setError("Invalid email id");
            } else if (pass.getText().toString().isEmpty()) {
                pass.setError("Please enter your Password");
            } else if (!pass.getText().toString().matches(".*[0-9].*")) {
                pass.setError("Password must contain one number from 0-9");
            } else if (!pass.getText().toString().matches(".*@.*")) {
                pass.setError("Password must contain '@' ");
            } else if (!pass.getText().toString().equals(re_pass.getText().toString())) {
                pass.setError("Password does not match");
            } else {
                Toast.makeText(RegisterActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                Handler h = new Handler();
                h.postDelayed(() -> {
                    Intent i = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }, 800);
            }
        });

        TnC.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://policies.google.com"));
            startActivity(i);
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}

