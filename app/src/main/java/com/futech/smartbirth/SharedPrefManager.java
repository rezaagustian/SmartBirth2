package com.futech.smartbirth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPrefManager {

    //the constants
    private static final String SHARED_PREF_NAME = "smartbirthsharedpref";
    private static final String KEY_NIK = "keynik";
    private static final String KEY_NAMA = "keynama";
    private static final String KEY_DUSUN = "keydusun";
    private static final String KEY_TL = "keytl";
    private static final String KEY_LAT = "keylat";
    private static final String KEY_LONG = "keylong";
    private static final String KEY_ZOOM = "keyZoom";
    private static final String KEY_ALAMAT = "keyalamat";
    private static final String KEY_TELP = "keytelp";

    private static final String KEY_LANGKAH = "keylangkah";
    private static final String KEY_TANGGAL = "keytanggal";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NIK, user.getNik());
        editor.putString(KEY_NAMA, user.getNama());
        editor.putString(KEY_DUSUN, user.getDusun());
        editor.putString(KEY_TL, user.getTL());
        editor.putString(KEY_ALAMAT, user.getAlamat());
        editor.putString(KEY_TELP, user.getTelp());
        editor.apply();
    }

    public User getLatLong(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_NIK, null),
                sharedPreferences.getString(KEY_LAT, null),
                sharedPreferences.getString(KEY_LONG, null),
                sharedPreferences.getString(KEY_ZOOM, null)
        );
    }


    public void setLatLong(String l1, String l2) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_LAT, l1);
        editor.putString(KEY_LONG, l2);
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NIK, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_NIK, null),
                sharedPreferences.getString(KEY_NAMA, null)
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }


    public void setProfile(User user){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NIK, user.getNik());
        editor.putString(KEY_NAMA, user.getNama());
        editor.putString(KEY_ALAMAT, user.getAlamat());
        editor.putString(KEY_TELP, user.getTelp());
        editor.apply();
    }


    public User getProfile() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_NIK, null),
                sharedPreferences.getString(KEY_NAMA, null),
                sharedPreferences.getString(KEY_DUSUN, null),
                sharedPreferences.getString(KEY_ALAMAT, null),
                sharedPreferences.getString(KEY_TL, null),
                sharedPreferences.getString(KEY_TELP, null)
        );
    }

    public void setLangkah(Langkah langkah){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_LANGKAH, langkah.getLangkah());
        editor.putString(KEY_TANGGAL, langkah.getTanggal());
        editor.apply();
    }

    public Langkah getLangkah() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Langkah(
                sharedPreferences.getString(KEY_LANGKAH, null),
                sharedPreferences.getString(KEY_TANGGAL, null)
        );
    }

}