package com.futech.smartbirth;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


public class PerkembanganFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    public static final String EXTRA_REPLY = "com.futech.smartbirth.datalistsql.REPLY";


    private List<DataModel> dataModelList;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private RequestQueue queue;
    private SwipeRefreshLayout swipeRefreshLayout;




    public PerkembanganFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        dataModelList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_perkembangan, container, false);
        //new liveview model
        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);
        final PerkembanganListAdapter adapter = new PerkembanganListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        swipeRefreshLayout = v.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);

        //loadRiwayatData();

        return v;

    }

    public void loadRiwayatData(){

        swipeRefreshLayout.setRefreshing(true);
        RequestQueue queue = VolleySingleton.getInstance(getActivity().getApplicationContext()).getRequestQueue();
        String url = "https://www.tokosms.com/api/smartbirth/getdata.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if(dataModelList.size()!=0) {
                    dataModelList.clear();
                }

                try {
                    // Parsing json array response
                    // loop through each json object
                    String jsonResponse = "";
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject kontak = (JSONObject) response
                                .get(i);

                        String nama = kontak.getString("tanggal");
                        String nomor = kontak.getString("berat");
                        //contacts.add(new Contact(nama, nomor));

                        dataModelList.add(new DataModel(nama,nomor,"1000"));
                        Log.d("Msg",nama + nomor);

                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                swipeRefreshLayout.setRefreshing(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                swipeRefreshLayout.setRefreshing(false);
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //params.put("token","qwertyuiop");

                return params;
            }

        };
        queue.add(jsonArrayRequest);
    }

    public void loadData(){
        Intent replyIntent = new Intent();
        getActivity().setResult(RESULT_OK, replyIntent);
        getActivity().finish();
    }


    @Override
    public void onRefresh() {
        //loadRiwayatData();
        loadData();

    }
}