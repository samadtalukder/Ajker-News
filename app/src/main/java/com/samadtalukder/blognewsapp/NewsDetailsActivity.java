package com.samadtalukder.blognewsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Vector;

public class NewsDetailsActivity extends AppCompatActivity {
    TextView headLine,body;
    ImageView imageTeaser;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        headLine = findViewById(R.id.headLineTV);
        body = findViewById(R.id.bodyTV);
        imageTeaser = findViewById(R.id.newsImage);
        scrollView = findViewById(R.id.scrollView);

        headLine.setText(getIntent().getStringExtra("headline"));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            body.setText(Html.fromHtml(getIntent().getStringExtra("body"),Html.FROM_HTML_MODE_LEGACY));
        } else {
            body.setText(getIntent().getStringExtra("body"));
        }
        //body.setText(getIntent().getStringExtra("body"));
        setImage(getIntent().getStringExtra("image"),imageTeaser);


        scrollView.fullScroll(ScrollView.FOCUS_UP);
    }

    private void setImage(String url, ImageView imageID) {
        Glide.with(NewsDetailsActivity.this)
                .load(url)
                .thumbnail(Glide.with(NewsDetailsActivity.this)
                .load(R.drawable.image_holder))
                .into(imageID);

    }
}
