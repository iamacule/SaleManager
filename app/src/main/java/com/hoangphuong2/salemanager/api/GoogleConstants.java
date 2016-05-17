package com.hoangphuong2.salemanager.api;

public class GoogleConstants {
    public static String CLIENT_ID = "40284376940-1spe3moldefq8s82dh18v7o1bvv536uj.apps.googleusercontent.com";
//    public static String CLIENT_ID = "";
    // Use your own client id

    public static String CLIENT_SECRET = "CjDhTgRqZXf6jwEyNW9tqcQN";
    // Use your own client secret

    public static String REDIRECT_URI = "http://localhost";
    public static String GRANT_TYPE = "authorization_code";
    public static String TOKEN_URL = "https://accounts.google.com/o/oauth2/token";
    public static String OAUTH_URL = "https://accounts.google.com/o/oauth2/auth";
    public static String OAUTH_SCOPE = "https://www.googleapis.com/auth/contacts.readonly";

    public static final String CONTACTS_URL = "https://www.google.com/m8/feeds/contacts/default/full";
    public static final int MAX_NB_CONTACTS = 1000;
    public static final String APP = "";
}