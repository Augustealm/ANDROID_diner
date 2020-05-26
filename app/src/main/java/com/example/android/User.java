package com.example.android;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
    private String Login_username;
    private String Login_password;
    private String email_address;

    private SharedPreferences sharedPreferences;
    private static final String PREFERENCES_PACKAGE_NAME="com.example.android";
    private static final String USERNAME_KEY="Login_username";
    private static final String PASSWORD_KEY="Login_password";
    private static final String REMEMBERME_KEY="rememberMe";

    public User(String Login_username, String Login_password, String email_address) {
    this.Login_username=Login_username;
    this.Login_password=Login_password;
    this.email_address=email_address;
}

public User(Context context) {
    this.sharedPreferences=context.getSharedPreferences(User.PREFERENCES_PACKAGE_NAME, Context.MODE_PRIVATE);
}
public String getLogin_usernameRegistration() {
    return Login_username;
}
public String getEmail_address() {
    return email_address;
}
public void setLogin_usernameForRegistration(String Login_username){
    this.Login_username=Login_username;
}
public void setLogin_passwordForRegistration(String Login_password){
    this.Login_password=Login_password;
}
public void setEmail_addressForRegistration(String email) {
    this.email_address=email_address;
}
public String getLogin_usernameForLogin(){
    return this.sharedPreferences.getString(USERNAME_KEY, "");
}
public void setLogin_usernameForLogin(String Login_username){
    this.sharedPreferences.edit().putString(USERNAME_KEY, Login_username).commit();
}
public String getLogin_passwordForLogin(){
    return this.sharedPreferences.getString(PASSWORD_KEY, "");
}
public void setLogin_passwordForLogin(String Login_password){
    this.sharedPreferences.edit().putString(PASSWORD_KEY, Login_password).commit();
}
public boolean isRememberedForLogin(){
    return this.sharedPreferences.getBoolean(REMEMBERME_KEY, false);
}
public void setRemembermeKeyForLogin(boolean remembermeKey){
    this.sharedPreferences.edit().putBoolean(REMEMBERME_KEY, remembermeKey).commit();
}
}