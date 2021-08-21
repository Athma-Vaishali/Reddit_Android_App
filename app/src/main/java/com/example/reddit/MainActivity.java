package com.example.reddit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.reddit.adapter.postAdapter;
import com.example.reddit.model.post;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;


public class MainActivity extends AppCompatActivity {

    public static final String TAG="Main";
    String URL="https://www.reddit.com/r/all/top.json";
    List<post> items;
    EndlessRecyclerViewScrollListener scrollListener;
    postAdapter postAdapter;
    String after="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvListing=findViewById(R.id.rv_listing);
        items=new ArrayList<>();

        postAdapter = new postAdapter(this, items);
        rvListing.setAdapter(postAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvListing.setLayoutManager(layoutManager);

        scrollListener=new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadData();
            }
        };
        rvListing.addOnScrollListener(scrollListener);
        loadData();
    }

    private void loadData() {
        Log.d(TAG,"LOADING DATA !!!");
        if(!after.equals("")){
            URL=URL+"?after="+after;
        }

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG,"onSuccess"+json);
                try {
                    after=json.jsonObject.getJSONObject("data").getString("after");
                    JSONArray childrenArray= json.jsonObject.getJSONObject("data").getJSONArray("children");
                    items.addAll(post.fromJsonArray(childrenArray));
                    postAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG,"onFailure"+response);
            }
        });
    }
}