package com.example.android;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validation {
    private static final String CREDENTIALS_PATTERN = "^[a-zA-Z]{3,20}$";
    private static final String VALID_CREDENTIALS_PATTERN = "^[a-zA-Z0-9(.!@_]{5,20}$";
    private static final String VALID_EMAIL_ADDRESS_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$";

    public static boolean isCredentialsValid(String credentials) {
        Pattern pattern=Pattern.compile(CREDENTIALS_PATTERN);
        Matcher matcher=pattern.matcher(credentials);
        return matcher.matches();
    }
    public static boolean isValidCredentials(String password) {
        Pattern credentialsPattern = Pattern.compile(VALID_CREDENTIALS_PATTERN);
        Matcher credentialsMatcher = credentialsPattern.matcher(password);
        return credentialsMatcher.find();
    }
    public static boolean isValidEmail(String email) {
        Pattern emailPattern = Pattern.compile(VALID_EMAIL_ADDRESS_REGEX);
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.find();
    }

}

