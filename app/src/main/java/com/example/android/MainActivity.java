package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CheckBox rememberMe = findViewById(R.id.remember);
        final EditText Login_username = findViewById(R.id.Login_username);
        final EditText Login_password = findViewById(R.id.Login_password);
        Button btn_register = findViewById(R.id.btn_register);
        Button btn_login = findViewById(R.id.btn_login);
       // Button newEntryButton = findViewById(R.id.new_entry_button);

        final User user = new User(MainActivity.this);
        rememberMe.setChecked(user.isRememberedForLogin());

        if (rememberMe.isChecked()){
            Login_username.setText(user.getLogin_usernameForLogin(), TextView.BufferType.EDITABLE);
            Login_password.setText(user.getLogin_passwordForLogin(), TextView.BufferType.EDITABLE);
        } else {
            Login_username.setText("", TextView.BufferType.EDITABLE);
            Login_password.setText("", TextView.BufferType.EDITABLE);
        }

       // newEntryButton.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
               // Intent gotoNewEntryActivity = new Intent(MainActivity.this, NewEntryActivity.class);
               // startActivity(gotoNewEntryActivity);
          //  }
       // });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Registracija", Toast.LENGTH_LONG).show();
                Intent gotoRegistrationActivity = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(gotoRegistrationActivity);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username = Login_username.getText().toString();
            String password = Login_password.getText().toString();
            Toast.makeText(MainActivity.this, "Prisijungėte", Toast.LENGTH_LONG).show();

            boolean usernameValid = false;
            boolean passwordValid = false;

            Login_username.setError(null);
            Login_password.setError(null);

            if (Validation.isCredentialsValid(username)){
                usernameValid = true;
            } else {
                Login_username.setError(getResources().getString(R.string.login_invalid_login_username));
                Login_username.requestFocus();
            }

            if (Validation.isValidCredentials(password)){
                passwordValid = true;
            } else {
                Login_password.setError(getResources().getString(R.string.login_invalid_login_password));
                Login_password.requestFocus();
            }
            if(usernameValid && passwordValid) {
                Intent gotNewEntryActivity = new Intent(MainActivity.this, NewEntryActivity.class);
                startActivity(gotNewEntryActivity);
            }
            if (Validation.isCredentialsValid(username) && Validation.isValidCredentials(password)) {
                user.setLogin_usernameForLogin(username);
                user.setLogin_passwordForLogin(password);
                if(rememberMe.isChecked()) {
                    user.setRemembermeKeyForLogin(true);
                } else {
                    user.setRemembermeKeyForLogin(false);
                }
            }
        }
        });

    }}