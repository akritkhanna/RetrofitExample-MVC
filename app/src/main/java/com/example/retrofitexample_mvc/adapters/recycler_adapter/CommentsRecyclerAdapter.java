package com.example.retrofitexample_mvc.adapters.recycler_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitexample_mvc.databinding.CommentsSingleRowBinding;
import com.example.retrofitexample_mvc.models.Comments;

import java.util.List;

public class CommentsRecyclerAdapter extends RecyclerView.Adapter<CommentsRecyclerAdapter.CommentsHolder> {
    List<Comments> comments;
    public CommentsRecyclerAdapter(List<Comments> comments) {
        this.comments=comments;
    }

    @NonNull
    @Override
    public CommentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentsHolder(CommentsSingleRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsHolder holder, int position) {
        holder.binding.postTV.setText(String.valueOf(comments.get(position).getPostId()));
        holder.binding.nameTV.setText(comments.get(position).getName());
        holder.binding.emailTV.setText(comments.get(position).getEmail());
        holder.binding.bodyTV.setText(comments.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class CommentsHolder extends RecyclerView.ViewHolder{
        CommentsSingleRowBinding binding;
        public CommentsHolder(CommentsSingleRowBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
