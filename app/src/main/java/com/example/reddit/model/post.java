package com.example.reddit.model;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class post {
    String title;
    String thumbnail_path;
    String author;
    String id;

    public post(JSONObject data) throws JSONException {
        title=data.getJSONObject("data").getString("title");
        thumbnail_path=data.getJSONObject("data").getString("thumbnail");
        switch (thumbnail_path) {
            case "default":
                thumbnail_path = "https://www.reddit.com/static/noimage.png";
                break;
            case "nsfw":
                thumbnail_path = "https://www.reddit.com/static/nsfw2.png";
                break;
            case "self":
                thumbnail_path = "https://www.reddit.com/static/self_default2.png";
                break;
        }
        author=data.getJSONObject("data").getString("author");
        id=data.getJSONObject("data").getString("id");
    }

    public static List<post> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<post> items=new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            items.add(new post(jsonArray.getJSONObject(i)));
        }
        return items;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail_path() {
        return thumbnail_path;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }
}
