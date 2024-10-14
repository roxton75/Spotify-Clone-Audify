package com.example.spotifyclone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class LoginActivity extends AppCompatActivity {

    EditText et_user, et_pass;
    Button btn_login;
    CheckBox cb_show;
    TextView tv_signup;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        editor = preferences.edit();

        if (preferences.getBoolean("login",false))
        {
            Intent i = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(i);
            finish();
        }

    et_user = findViewById(R.id.et_login_user);
    et_pass= findViewById(R.id.et_login_pass);
    btn_login = findViewById(R.id.btn_login);
    cb_show = findViewById(R.id.checkbox_login_pg);
    tv_signup = findViewById(R.id.tv_register);

    cb_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked)
            {
                et_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else
            {
                et_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    });

    tv_signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent s = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(s);
            finish();
        }
    });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if  (et_user.getText().toString().length()<4 && et_user.getText().toString().length()==4)
                {
                    et_user.setError("Username must be greater than 4 letters");
                }
                else if (et_pass.getText().toString().isEmpty())
                {
                    et_pass.setError("Please enter your Password");
                }
                else if (et_pass.getText().toString().length()<8)
                {
                    et_pass.setError("Password must be greater than 8 letters");
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Login Successfully done",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this,HomeActivity.class);
//                    editor.putBoolean("login",true).commit();
//                    editor.putString("username",et_user.getText().toString()).commit();
                    i.putExtra("username",et_user.getText().toString());
                    i.putExtra("password",et_pass.getText().toString());
                    startActivity(i);

//                    This block of code displays the greeting to the user in HomeActivity
                    String greetsUser = et_user.getText().toString().trim();
                    Intent gi = new Intent(LoginActivity.this,HomeActivity.class);
                    gi.putExtra("User",greetsUser);
                    startActivity(gi);

                }
            }
        });
    }
}