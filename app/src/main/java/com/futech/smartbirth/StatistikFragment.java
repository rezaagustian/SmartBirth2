package com.futech.smartbirth;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
import com.android.volley.toolbox.StringRequest;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatistikFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<DataModel> dataModelList;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private GraphView graph;

    public StatistikFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_statistik, container, false);

        graph = (GraphView) v.findViewById(R.id.graph);


        dataModelList = new ArrayList<>();
        recyclerView = v.findViewById (R.id.recycler_view);

        linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new ListAdapter(dataModelList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = v.findViewById(R.id.swipe_container);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                //loadData();
                loadGraph();
            }
        });;

        return v;
    }

/*
    private void loadData(){

        String url = "https://www.tokosms.com/api/smartbirth/getlist.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {

                        if(dataModelList.size()!= 0){
                            dataModelList.clear();
                        }

                        Log.d("echo",response);

                        try{

                            JSONArray jsonArray = new JSONArray(response);

                            for (int i=0; i < jsonArray.length(); i++){
                                JSONObject data = (JSONObject) jsonArray.get(i);
                                DataModel dataModel = new DataModel(
                                        data.getString("tanggal"),
                                        data.getString("berat")
                                );

                                dataModelList.add(dataModel);
                            }
                            adapter.notifyDataSetChanged();


                        } catch (JSONException e){
                            e.printStackTrace();
                        }

                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }*/


    private void loadGraph(){

        String url = "https://www.tokosms.com/api/smartbirth/getdata.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {
                        Log.d("echo 1",response);

                        try{


                            JSONArray jsonArray = new JSONArray(response);
                            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
                            double y;
                            int x = 0;
                            int max = jsonArray.length();


                            for(int i =0; i<max ; i++){
                                JSONObject data = (JSONObject) jsonArray.get(i);
                                y = data.getDouble("berat");
                                series.appendData(new DataPoint(x ,y),true,max);
                                x++;
                            }

                            graph.addSeries(series);

                            if(dataModelList.size()!= 0){
                                dataModelList.clear();
                            }

                            for (int i=0; i < jsonArray.length(); i++){
                                JSONObject data = (JSONObject) jsonArray.get(i);
                                DataModel dataModel = new DataModel(
                                        data.getString("tanggal"),
                                        data.getString("berat")
                                );

                                dataModelList.add(dataModel);

                            }
                            adapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);

                        } catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
                swipeRefreshLayout.setRefreshing(false);
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



    @Override
    public void onRefresh() {
        loadGraph();
    }
}