package com.example.reddit.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class comment {
    String author;
    String body;

    public comment(JSONObject data) throws JSONException {
        author=data.getJSONObject("data").getString("author");
        body = data.getJSONObject("data").getString("body");
    }

    public static List<comment> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<comment> comments=new ArrayList<>();
        for(int i=0;i<jsonArray.length()-1;i++){
            comments.add(new comment(jsonArray.getJSONObject(i)));
        }
        return comments;
    }

    public String getBody() {
        return body;
    }

    public String getAuthor() {
        return author;
    }
}
