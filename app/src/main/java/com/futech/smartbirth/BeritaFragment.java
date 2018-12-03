package com.futech.smartbirth;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class BeritaFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private List<BeritaDataModel> beritaDataModelList;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;


    public BeritaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_berita, container, false);

        beritaDataModelList = new ArrayList<>();
        recyclerView = v.findViewById (R.id.recycler_view);;

        linearLayoutManager = new LinearLayoutManager(getContext());

        adapter = new BeritaAdapter(beritaDataModelList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = v.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);


        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                loadBerita();
            }
        });;

        return v;

    }

    private void loadBerita(){

        String url = "https://www.tokosms.com/api/smartbirth/getnews.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {

                        if(beritaDataModelList.size()!= 0){
                            beritaDataModelList.clear();
                        }

                        Log.d("echo",response);

                        try{

                            JSONArray jsonArray = new JSONArray(response);

                            for (int i=0; i < jsonArray.length(); i++){
                                JSONObject berita = (JSONObject) jsonArray.get(i);
                                BeritaDataModel beritaDataModel = new BeritaDataModel(
                                        berita.getString("judul_berita"),
                                        berita.getString("isi_berita"),
                                        berita.getString("tanggal_berita"),
                                        berita.getString("tanggal_berita2")
                                );

                                beritaDataModelList.add(beritaDataModel);
                            }
                            adapter.notifyDataSetChanged();


                        } catch (JSONException e){
                            e.printStackTrace();
                        }

                        swipeRefreshLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
                swipeRefreshLayout.setRefreshing(false);

            }
        });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }


    @Override
    public void onRefresh() {
        loadBerita();
    }

}

