package com.samadtalukder.blognewsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

import com.bumptech.glide.Glide;
import com.samadtalukder.blognewsapp.NewsDetailsActivity;
import com.samadtalukder.blognewsapp.R;
import com.samadtalukder.blognewsapp.model.NewsModel;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private Context context;
    private Vector vector;
    private boolean item = true;

    public NewsAdapter(Context context, Vector vector) {
        this.context = context;
        this.vector = vector;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.news_list_item, null);

        return new NewsAdapter.NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        NewsModel newsModel = (NewsModel) vector.elementAt(position);
        if (item){
            setImage(newsModel.getImage_mobile(),holder.newsImage_1,context);
            holder.newsTitile_1.setText(newsModel.getHeadLine());
            holder.newsDescription_1.setText(newsModel.getBody());
            holder.news_1.setVisibility(View.VISIBLE);
            holder.news_2.setVisibility(View.GONE);
            item = false;
        }else {
            setImage(newsModel.getImage_mobile(),holder.newsImage_2,context);
            holder.newsTitile_2.setText(newsModel.getHeadLine());
            holder.newsDescription_2.setText(newsModel.getBody());
            holder.news_1.setVisibility(View.GONE);
            holder.news_2.setVisibility(View.VISIBLE);
            item = true;
        }
        holder.news_1.setOnClickListener(v -> {
            // Toast.makeText(context, position+" news1", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, NewsDetailsActivity.class);
            intent.putExtra("headline", newsModel.getHeadLine());
            intent.putExtra("news_type", newsModel.getNews_type());
            intent.putExtra("image", newsModel.getImage_mobile());
            intent.putExtra("body", newsModel.getBody());
            context.startActivity(intent);
        });

        holder.news_2.setOnClickListener(v -> {
            // Toast.makeText(context, position+" news2", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, NewsDetailsActivity.class);
            intent.putExtra("headline", newsModel.getHeadLine());
            intent.putExtra("news_type", newsModel.getNews_type());
            intent.putExtra("image", newsModel.getImage_mobile());
            intent.putExtra("body", newsModel.getBody());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return vector.size();
    }

    private void setImage(String url, ImageView imageID, Context context) {
        Glide.with(context)
                .load(url)
                .thumbnail(Glide.with(context)
                .load(R.drawable.image_holder))
                .into(imageID);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
         CardView news_1,news_2;
        private TextView newsTitile_1,newsTitile_2;
        private TextView newsDescription_1,newsDescription_2;
        private ImageView newsImage_1,newsImage_2;

        public NewsViewHolder(View itemView) {
            super(itemView);
            news_1 = itemView.findViewById(R.id.newsCardView);
            news_2 = itemView.findViewById(R.id.newsCardView_2);

            newsTitile_1 = itemView.findViewById(R.id.newsTitle_1);
            newsTitile_2 = itemView.findViewById(R.id.newsTitle_2);

            newsImage_1 = itemView.findViewById(R.id.newsImage_1);
            newsImage_2 = itemView.findViewById(R.id.newsImage_2);

            newsDescription_1 = itemView.findViewById(R.id.newsDescription_1);
            newsDescription_2 = itemView.findViewById(R.id.newsDescription_2);
        }
    }
}
