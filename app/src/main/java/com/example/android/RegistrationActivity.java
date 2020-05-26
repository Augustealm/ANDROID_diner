package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class RegistrationActivity extends AppCompatActivity {
    private static final String INSERT_URL = "https://atsiskaitomojiandroid.000webhostapp.com/connect.php";

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


                if (Validation.isCredentialsValid(username)) {
                    usernameValid = true;

                } else {
                    Login_username.setError(getResources().getString(R.string.login_invalid_login_username));
                    Login_username.requestFocus();
                }

                if (Validation.isValidCredentials(password)) {
                    passwordValid = true;
                } else {
                    Login_password.setError(getResources().getString(R.string.login_invalid_login_password));
                    Login_password.requestFocus();
                }
                if (Validation.isValidEmail(email)) {
                    emailValid = true;
                } else {
                    email_address.setError(getResources().getString(R.string.login_invalid_email_address));
                    email_address.requestFocus();
                }

                if (emailValid && usernameValid && passwordValid) {
                    User userRegistration = new User(Login_username.getText().toString(), Login_password.getText().toString(), email_address.getText().toString());

                    registerToDB(userRegistration);

                    Intent gotNewEntryActivity = new Intent(RegistrationActivity.this, NewEntryActivity.class);
                    startActivity(gotNewEntryActivity);
                }
            }
        });
    }
        public void registerToDB(final User userRegistration){
            class Registration extends AsyncTask<String, Void, String> {
                ProgressDialog loading;
                DB db = new DB();

                @Override
                protected String doInBackground(String... strings) {
                    HashMap<String, String> data = new HashMap<String, String>();
                    data.put("action", "register");
                    data.put("username", strings[0]);
                    data.put("email", strings[1]);
                    data.put("password", strings[2]);

                    String result = db.sendPostRequest(INSERT_URL, data);

                    return result;
                }

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(RegistrationActivity.this, getResources().getString(R.string.new_entry_database_info), null, true, true);
                }

            @Override
            protected void onPostExecute (String s){
                super.onPostExecute(s);

                if (s.equals(Integer.toString(HttpsURLConnection.HTTP_OK))) {
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(RegistrationActivity.this, getString(R.string.register_success), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegistrationActivity.this, getString(R.string.register_failure), Toast.LENGTH_LONG).show();
                }
                loading.dismiss();
            }

        }
        Registration registration = new Registration();
        registration.execute();

    }};