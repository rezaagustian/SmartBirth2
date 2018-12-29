package com.futech.smartbirth;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String nik;
    double v, v1;
    MarkerOptions markerOptions;
    View view;
    //TextView alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLatLong();
            }
        });

       // v = -7.807740;
       // v1 = 110.010017;
        //alamat = findViewById(R.id.);
        //view = findViewById(R.id.view);
        //view.setVisibility(View.INVISIBLE);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        User user = SharedPrefManager.getInstance(getApplicationContext()).getLatLong();
        nik = user.getNik();

        getLatLong();
        mMap = googleMap;

        //User user = SharedPrefManager.getInstance(getApplicationContext()).getLatLong();
        //v = Double.valueOf(user.getLatitude());
        //v1 = Double.valueOf(user.getLongitude());
        //LatLng lokasiAwal = new LatLng(v, v1);

        mMap.setMinZoomPreference(15);
        mMap.getUiSettings().setZoomGesturesEnabled(true);

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(yogya));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
        }


        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                //markerOptions.position(mMap.getCameraPosition().target);
                //mMap.addMarker(markerOptions);
                Log.d("camera", "idle");
                //alamat.setText(mMap.getCameraPosition().target.toString());
                // view.setVisibility(View.VISIBLE);
                /*
                try {

                    Geocoder geo = new Geocoder(MapsActivity.this.getApplicationContext(), Locale.getDefault());
                    List<Address> addresses = geo.getFromLocation(mMap.getCameraPosition().target.latitude, mMap.getCameraPosition().target.longitude,1);
                    if (addresses.isEmpty()) {
                        alamat.setText("Waiting for Location");
                    }
                    else {
                        if (addresses.size() > 0) {
                            alamat.setText(addresses.get(0).toString());
                            //Toast.makeText(getApplicationContext(), "Address:- " + addresses.get(0).getFeatureName() + addresses.get(0).getAdminArea() + addresses.get(0).getLocality(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace(); // getFromLocation() may sometimes fail
                }
                */
            }
        });

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {

                Log.d("camera",  "" + mMap.getCameraPosition().zoom);
                // view.setVisibility(View.INVISIBLE);

            }
        });




    }

    private void getLatLong(){
        String url = "https://www.tokosms.com/api/smartbirth/getLatLong.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {

                        Log.d("echo",response);


                        try{
                            JSONObject jsonObject = new JSONObject(response);

                            double l1 = Double.valueOf(jsonObject.getString("lat"));
                            double l2 = Double.valueOf(jsonObject.getString("long"));
                            float z = Float.valueOf(jsonObject.getString("zoom"));

                            //SharedPrefManager.getInstance(getApplicationContext()).setLatLong(l1, l2);

                            LatLng mLocation = new LatLng(l1, l2);
                            mMap.setMinZoomPreference(z);
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(mLocation));


                        } catch (JSONException e){
                            e.printStackTrace();
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
                params.put("user_id",nik);

                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,1,1));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }




    private void updateLatLong(){

        final String l1 = String.valueOf(mMap.getCameraPosition().target.latitude);
        final String l2 = String.valueOf(mMap.getCameraPosition().target.longitude);
        final String z = String.valueOf(mMap.getCameraPosition().zoom);

        String url = "https://www.tokosms.com/api/smartbirth/setLatLong.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {

                        Toast.makeText(getApplicationContext(),"Berhasil Disimpan", Toast.LENGTH_SHORT).show();


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
                params.put("lat",l1);
                params.put("long",l2);
                params.put("zoom",z);
                params.put("user_id",nik);

                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,1,1));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
