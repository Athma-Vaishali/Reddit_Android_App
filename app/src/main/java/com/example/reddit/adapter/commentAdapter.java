package com.example.reddit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reddit.R;
import com.example.reddit.model.comment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class commentAdapter extends RecyclerView.Adapter<commentAdapter.ViewHolder> {
    Context context;
    List<comment> comments;

    public commentAdapter(Context context, List<comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @NonNull
    @NotNull
    @Override
    public commentAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.comment, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull commentAdapter.ViewHolder holder, int position) {
        comment comment=comments.get(position);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvAuthor;
        TextView tvBody;
        public ViewHolder(@NotNull View itemView){
            super(itemView);
            tvAuthor=itemView.findViewById(R.id.tv_cAuthor);
            tvBody=itemView.findViewById(R.id.tv_body);
        }

        public void bind(comment comment) {
            tvAuthor.setText(comment.getAuthor());
            tvBody.setText(comment.getBody());
        }
    }
}