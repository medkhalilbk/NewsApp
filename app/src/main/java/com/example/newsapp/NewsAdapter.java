package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Outline;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<News> newsList;
    private Context context;

    public NewsAdapter(Context context,List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        News currentItem = newsList.get(position);
        if (currentItem.getImage() != null) {
            int imageResource = holder.itemView.getContext().getResources().getIdentifier(currentItem.getImage(), "drawable", holder.itemView.getContext().getPackageName());
            holder.image.setImageResource(imageResource);
        }
        holder.titleTextView.setText(currentItem.getTitle());
        /*holder.descriptionTextView.setText(currentItem.getDescription());*/
        holder.typeTextView.setText(currentItem.getType());
        holder.dateTextView.setText(currentItem.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("image", currentItem.getImage());
                intent.putExtra("title", currentItem.getTitle());
                intent.putExtra("description", currentItem.getDescription());
                intent.putExtra("type", currentItem.getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView typeTextView;
        public TextView dateTextView;
        public NewsViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            titleTextView = itemView.findViewById(R.id.title);
            /*descriptionTextView = itemView.findViewById(R.id.description);*/
            typeTextView = itemView.findViewById(R.id.type);
            dateTextView = itemView.findViewById(R.id.date);

            image.setClipToOutline(true);
            image.setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    int cornerRadius = 40;
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), cornerRadius);
                }
            });
        }
    }

}
