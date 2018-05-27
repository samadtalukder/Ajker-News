package com.samadtalukder.blognewsapp;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.samadtalukder.blognewsapp.adapter.NewsAdapter;
import com.samadtalukder.blognewsapp.model.NewsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private Vector newsData;
    private RecyclerView newsRecyclerView;
    private String URL = "https://next.json-generator.com/api/json/get/NyOC-E7kS";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsRecyclerView = findViewById(R.id.newsRecylcerView);
        newsRecyclerView.setFocusable(false);
        newsRecyclerView.setNestedScrollingEnabled(false);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsRecyclerView.setItemViewCacheSize(20);
        newsRecyclerView.setDrawingCacheEnabled(true);
        //
        loadNewsData();
        //
    }
    private void loadNewsData() {
        newsData = new Vector<>();
        NewsModel newsModel = new NewsModel();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject jsonObject = new JSONObject();
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET,URL,jsonObject,
                response ->{
                    //Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show();
                    Log.e("News API:",response.toString());
            try {

                JSONObject homeSegmentJsonObject = new JSONObject(String.valueOf(response));
                if (!homeSegmentJsonObject.isNull("success")){
                    JSONArray newsJsonArray = homeSegmentJsonObject.getJSONArray("news_data");
                    for (int i = 0; i<newsJsonArray.length(); i++){
                        JSONObject newsJsonObject = newsJsonArray.getJSONObject(i);
                        if (i==0)
                        {
                            newsModel.setArticle_id(newsJsonObject.getString("article_id"));
                            newsModel.setPublishedTime(newsJsonObject.getString("publishedTime"));
                            newsModel.setHeadLine(newsJsonObject.getString("headline"));
                            newsModel.setBody(newsJsonObject.getString("body"));
                            newsModel.setNews_type(newsJsonObject.getString("news_type"));
                            newsModel.setImage_mobile(newsJsonObject.getString("image"));
                        }

                        newsData.add(new NewsModel(
                                newsJsonObject.getString("article_id"),
                                newsJsonObject.getString("publishedTime"),
                                newsJsonObject.getString("headline"),
                                newsJsonObject.getString("body"),
                                newsJsonObject.getString("news_type"),
                                newsJsonObject.getString("image")
                                )
                        );
                    }

                    NewsAdapter adapter = new NewsAdapter(this, newsData);
                    newsRecyclerView.setAdapter(adapter);
                    //nextPage_url = homeSegmentJsonObject.getString("nextPageUrl");
                }
                else {
                    Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show();

                }
            }catch (JSONException e) {
                System.out.print(e);
                //e.printStackTrace();
            }
        },error -> {
            VolleyLog.e("Error: ", error.getMessage());
        } );
        requestQueue.add(objectRequest);
    }

}
