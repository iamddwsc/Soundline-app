package com.saber.Soundline.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;
import com.saber.Soundline.Activities.HomeActivity;
import com.saber.Soundline.Adapters.SingleItemAdapter;
import com.saber.Soundline.Adapters.SubjectItemAdapter;
import com.saber.Soundline.Constant;
import com.saber.Soundline.Models.SingleItem;
import com.saber.Soundline.Models.SubjectItem;
import com.saber.Soundline.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HomeFragment extends Fragment{

    private View view;
    private RecyclerView recyclerHome;
    private ArrayList<SingleItem> singleItemArrayList;
    private SwipeRefreshLayout refreshLayout;
    private MaterialToolbar toolbar;
    private SingleItemAdapter homeAdapter;

    private SubjectItemAdapter subjectItemAdapter;
    private ArrayList<SubjectItem> subjectItemArrayList;

    public HomeFragment() {}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_home, container, false);
        init();
        return view;
    }

    private void init() {
        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        recyclerHome = view.findViewById(R.id.recyclerHome);
        recyclerHome.setHasFixedSize(true);
        recyclerHome.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout = view.findViewById(R.id.swipe_home);
        toolbar = view.findViewById(R.id.toolbar_home);
        ((HomeActivity) Objects.requireNonNull(getContext())).setSupportActionBar(toolbar);

        getRecently();
        getRecently2();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRecently();
                Log.i("xyz", "kho hieu");
            }
        });
    }

    private void getRecently() {
        singleItemArrayList = new ArrayList<SingleItem>();
        subjectItemArrayList = new ArrayList<SubjectItem>();
        refreshLayout.setRefreshing(true);


        StringRequest request = new StringRequest(Request.Method.GET, Constant.RECENTLY, response -> {
            try {
                JSONObject object = new JSONObject(response);
                if (object.getBoolean("success")) {
                    JSONArray jsonArray = new JSONArray(object.getString("trackName"));
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject trackObject = jsonArray.getJSONObject(i);

                        //SubjectItem recentlyPlayed = new SubjectItem();


                        SingleItem singleItem = new SingleItem();
                        singleItem.setTrackName(trackObject.getString("name"));
                        singleItem.setArtist(trackObject.getString("artistName"));
                        singleItem.setPhoto(trackObject.getString("url_64"));

//                        JSONArray img_urls =  new JSONArray(trackObject.getString("urls"));
//                        for (int j=0;j<img_urls.length();j++) {

                        //itemRecycleHome.setPhoto(trackObject.getString("url"));
                            //Log.i("abczz",img_urls.getString(3));
                        //}
//                        Log.i("artist",trackObject.getString("artistName"));
//                        Log.i("abcd", "OK");
                        singleItemArrayList.add(singleItem);
                    }
                    SubjectItem subjectItem = new SubjectItem();
                    subjectItem.setHeaderTitle("Mới phát gần đây");
                    subjectItem.setItems(singleItemArrayList);

                    subjectItemArrayList.add(subjectItem);
                    subjectItemAdapter = new SubjectItemAdapter(subjectItemArrayList, getContext());
                    recyclerHome.setAdapter(subjectItemAdapter);
//                    subjectItemAdapter = new SubjectItemAdapter(subjectItemArrayList, getContext());
//                    recyclerHome.setAdapter(subjectItemAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            refreshLayout.setRefreshing(false);
        }, error -> {
            error.printStackTrace();
            refreshLayout.setRefreshing(false);
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                String token = preferences.getString("token","");
                //String token2 = sharedPreferences.getString("token2","");
                Log.i("abcd", "Tokenzz here: " + token);
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                String token = preferences.getString("token", "");
                Log.i("abcd", "Tokenz here: " + token);
                map.put("token", token);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        queue.add(request);
    }
    private void getRecently2() {
        singleItemArrayList = new ArrayList<SingleItem>();
        subjectItemArrayList = new ArrayList<SubjectItem>();
        refreshLayout.setRefreshing(true);


        StringRequest request = new StringRequest(Request.Method.POST, Constant.RECENTLY, response -> {
            try {
                JSONObject object = new JSONObject(response);
                if (object.getBoolean("success")) {
                    JSONArray jsonArray = new JSONArray(object.getString("trackName"));
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject trackObject = jsonArray.getJSONObject(i);

                        //SubjectItem recentlyPlayed = new SubjectItem();


                        SingleItem singleItem = new SingleItem();
                        singleItem.setTrackName(trackObject.getString("name"));
                        singleItem.setArtist(trackObject.getString("artistName"));
                        singleItem.setPhoto(trackObject.getString("url_64"));



//                        JSONArray img_urls =  new JSONArray(trackObject.getString("urls"));
//                        for (int j=0;j<img_urls.length();j++) {

                        //itemRecycleHome.setPhoto(trackObject.getString("url"));
                        //Log.i("abczz",img_urls.getString(3));
                        //}
//                        Log.i("artist",trackObject.getString("artistName"));
//                        Log.i("abcd", "OK");
                        singleItemArrayList.add(singleItem);
                    }
                    SubjectItem subjectItem = new SubjectItem();
                    subjectItem.setHeaderTitle("Mới phát gần đây");
                    subjectItem.setItems(singleItemArrayList);

                    subjectItemArrayList.add(subjectItem);
                    subjectItemAdapter = new SubjectItemAdapter(subjectItemArrayList, getContext());
                    recyclerHome.setAdapter(subjectItemAdapter);
//                    subjectItemAdapter = new SubjectItemAdapter(subjectItemArrayList, getContext());
//                    recyclerHome.setAdapter(subjectItemAdapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            refreshLayout.setRefreshing(false);
        }, error -> {
            error.printStackTrace();
            refreshLayout.setRefreshing(false);
        }){
            //            @Override
//            public Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<>();
//                String token = sharedPreferences.getString("token","");
//                String token2 = sharedPreferences.getString("token2","");
//                Log.i("abcd", "Tokenz here: " + token2);
//                headers.put("headers", token);
//                return headers;
//            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                String token = preferences.getString("token", "");
                Log.i("abcd", "Tokenz here: " + token);
                map.put("token", token);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        queue.add(request);
    }
}