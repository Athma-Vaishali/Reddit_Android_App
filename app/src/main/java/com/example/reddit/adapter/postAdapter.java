package com.example.reddit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reddit.CommentsActivity;
import com.example.reddit.GlideApp;
import com.example.reddit.R;
import com.example.reddit.model.post;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class postAdapter extends RecyclerView.Adapter<postAdapter.ViewHolder> {
    Context context;
    List<post> items;

    public postAdapter(Context context,List<post> items){
        this.context=context;
        this.items=items;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.post,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        post item=items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout container;
        TextView tvAuthor;
        TextView tvTitle;
        ImageView ivThumbnail;
        public ViewHolder(@NotNull View itemView){
            super(itemView);
            container=itemView.findViewById(R.id.container);
            tvAuthor=itemView.findViewById(R.id.tv_author);
            tvTitle=itemView.findViewById(R.id.tv_title);
            ivThumbnail=itemView.findViewById(R.id.iv_thumbnail);
        }

        public void bind(post item) {
            tvAuthor.setText(item.getAuthor());
            tvTitle.setText(item.getTitle());
            GlideApp.with(context).load(item.getThumbnail_path()).into(ivThumbnail);
            container.setOnClickListener(view -> {
                Intent i = new Intent(context, CommentsActivity.class);
                i.putExtra("id",item.getId());
                context.startActivity(i);
            });
        }
    }
}

