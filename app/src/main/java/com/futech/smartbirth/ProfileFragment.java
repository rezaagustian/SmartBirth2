package com.futech.smartbirth;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private ImageView imageView;
    private Button btnLogout;
    private TextView textViewNik, textViewNama, textViewAlamat, textViewKota, textViewTelp;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        textViewNik = v.findViewById(R.id.textViewProfileNik);
        textViewNama = v.findViewById(R.id.textViewProfileNama);
        textViewAlamat = v.findViewById(R.id.textViewProfileAlamat);
        textViewTelp = v.findViewById(R.id.textViewProfileTelp);

        imageView = v.findViewById(R.id.imageViewAddress);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), MapsActivity.class);
                getContext().startActivity(i);

            }
        });

        btnLogout = v.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefManager.getInstance(getContext()).logout();
            }
        });

        loadProfile();
        return v;
    }


    private void loadProfile(){

        String url = "https://www.tokosms.com/api/smartbirth/getprofile.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {

                        try{


                                JSONObject profile = new JSONObject(response);
                                String nama = profile.getString("nama");
                                String nik = profile.getString("nik");
                                String alamat = profile.getString("alamat");
                                String telp = profile.getString("telp");


                            textViewNik.setText(nik);
                            textViewNama.setText(nama);
                            textViewAlamat.setText(alamat);
                            textViewTelp.setText(telp);


                        } catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());

            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("phone", "08123456789");

                return params;
            }

        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);


        }

}
