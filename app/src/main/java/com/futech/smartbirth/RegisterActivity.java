package com.futech.smartbirth;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    //private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText editTextNik, editTextNama, editTextTtl, editTextAlamat, editTextRtRw, editTextDusun, editTextTelp, editTextPassword;
    private View mProgressView;
    private View mLoginFormView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set up the login form.
        editTextNik = (EditText) findViewById(R.id.edittext_nik);
        editTextNama = (EditText) findViewById(R.id.edittext_nama);
        editTextTtl = (EditText) findViewById(R.id.edittext_ttl);
        editTextAlamat = (EditText) findViewById(R.id.edittext_alamat);
        editTextRtRw = (EditText) findViewById(R.id.edittext_rtrw);
        editTextDusun = (EditText) findViewById(R.id.edittext_dusun);
        editTextTelp = (EditText) findViewById(R.id.edittext_telp);
        editTextPassword = (EditText) findViewById(R.id.edittext_password);

        Button mSignupButton = (Button) findViewById(R.id.signup_button);
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signup();
            }
        });


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    private void signup() {
        //if (mAuthTask != null) {
        //    return;
        //}

        // Reset errors.
        //mPassView.setError(null);

        // Store values at the time of the login attempt.
        String nik = editTextTelp.getText().toString();
        String nama = editTextNama.getText().toString();
        String ttl = editTextTtl.getText().toString();
        String alamat = editTextAlamat.getText().toString();
        String rtrw = editTextRtRw.getText().toString();
        String dusun = editTextDusun.getText().toString();
        String telp = editTextTelp.getText().toString();
        String password = editTextPassword.getText().toString();


        boolean cancel = false;
        View focusView = null;

        // Check form
        if (TextUtils.isEmpty(nik)) {
            editTextNik.setError("NIK tidak boleh kosong");
            focusView = editTextNik;
            cancel = true;
        } else if (TextUtils.isEmpty(nama)) {
            editTextNama.setError("Masukkan Nama Anda");
            focusView = editTextNama;
            cancel = true;
        } else if (TextUtils.isEmpty(ttl)) {
            editTextTtl.setError("Mohon isi tempat lahir Anda.");
            focusView = editTextTtl;
            cancel = true;
        } else if (TextUtils.isEmpty(alamat)) {
            editTextAlamat.setError("Alamat tidak boleh kosong");
            focusView = editTextAlamat;
            cancel = true;
        } else if (TextUtils.isEmpty(rtrw)) {
            editTextRtRw.setError("Isi nomor RT/RW");
            focusView = editTextRtRw;
            cancel = true;
        } else if (TextUtils.isEmpty(dusun)) {
            editTextDusun.setError("NIK tidak boleh kosong");
            focusView = editTextDusun;
            cancel = true;
        } else if (TextUtils.isEmpty(telp)) {
            editTextTelp.setError("No Telp tidak boleh kosong");
            focusView = editTextTelp;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Isi password a");
            focusView = editTextPassword;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            //mAuthTask = new UserLoginTask(phone, password);
            //mAuthTask.execute((Void) null);
            userSignup();
        }
    }

    private boolean isPhoneValid(String phone) {
        //TODO: Replace this with your own logic
        return phone.length() > 9;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }






    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */

    /*
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        private final String mPhone;
        private final String mPassword;
        UserLoginTask(String phone, String password) {
            mPhone = phone;
            mPassword = password;
        }
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }
            // TODO: register the new account here.
            //return false;
        }
        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);
            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }
        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
        */

    private void userSignup(){

        final String nik = editTextNik.getText().toString();
        final String nama = editTextNama.getText().toString();
        final String ttl = editTextTtl.getText().toString();
        final String alamat = editTextAlamat.getText().toString();
        final String rtrw = editTextRtRw.getText().toString();
        final String dusun = editTextDusun.getText().toString();
        final String telp = editTextTelp.getText().toString();
        final String password = editTextPassword.getText().toString();

        String url = "https://www.tokosms.com/api/smartbirth/signup.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {

                        Log.d("echo",response.toString());
                        showProgress(false);

                        if(response.equalsIgnoreCase("success")){

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("nik",nik);
                params.put("nama",nama);
                params.put("ttl",ttl);
                params.put("alamat",alamat);
                params.put("rtrw",rtrw);
                params.put("dusun",dusun);
                params.put("telp",telp);
                params.put("password",password);

                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,1,1));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

}