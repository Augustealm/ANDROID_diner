package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        final EditText Login_username = findViewById(R.id.Login_username);
        final EditText Login_password = findViewById(R.id.Login_password);
        final EditText email_address = findViewById(R.id.email_address);
        Button btn_register1 = findViewById(R.id.btn_register);

        btn_register1.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            String username = Login_username.getText().toString();
            String password = Login_password.getText().toString();
            String email = email_address.getText().toString();
            Toast.makeText(RegistrationActivity.this, "Užsiregistravote", Toast.LENGTH_LONG).show();

            boolean emailValid = false;
            boolean usernameValid = false;
            boolean passwordValid = false;

            Login_username.setError(null);
            Login_password.setError(null);
            email_address.setError(null);

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
            if (Validation.isValidEmail(email)){
                emailValid = true;
            } else {
                email_address.setError(getResources().getString(R.string.login_invalid_email_address));
                email_address.requestFocus();
            }

            if(emailValid && usernameValid && passwordValid) {
                Intent gotLoginActivity = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(gotLoginActivity);
            }
        }
    });

}}


