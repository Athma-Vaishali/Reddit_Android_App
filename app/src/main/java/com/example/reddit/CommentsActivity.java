package com.example.reddit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.reddit.adapter.commentAdapter;
import com.example.reddit.model.comment;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class CommentsActivity extends AppCompatActivity {

    public static final String TAG="Comments";
    List<comment> comments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        comments=new ArrayList<>();
        RecyclerView rvListing=findViewById(R.id.rv_comments);

        commentAdapter commentAdapter = new commentAdapter(this, comments);
        rvListing.setAdapter(commentAdapter);
        rvListing.setLayoutManager(new LinearLayoutManager(this));

        String id= getIntent().getStringExtra("id");
        final String URL="https://www.reddit.com/r/all/comments/"+id+".json";

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG,"onSuccess"+json);
                try {
                    JSONArray childrenArray= json.jsonArray.getJSONObject(1).getJSONObject("data").getJSONArray("children");
                    Log.d(TAG,"The OBJECT "+childrenArray);
                    comments.addAll(comment.fromJsonArray(childrenArray));
                    commentAdapter.notifyDataSetChanged();
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