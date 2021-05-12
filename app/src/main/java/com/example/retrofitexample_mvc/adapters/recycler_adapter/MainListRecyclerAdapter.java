package com.example.retrofitexample_mvc.adapters.recycler_adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitexample_mvc.R;
import com.example.retrofitexample_mvc.databinding.ListSingleRowBinding;
import com.example.retrofitexample_mvc.fragments.CommentsFragment;
import com.example.retrofitexample_mvc.fragments.ToDoFragment;

import java.util.List;

public class MainListRecyclerAdapter extends RecyclerView.Adapter<MainListRecyclerAdapter.MainListHolder> {
    private static final String TAG ="mainRVAdapter" ;
    List<String> listElements;
    AppCompatActivity activity;
    public MainListRecyclerAdapter(List<String> listElements) {
        this.listElements=listElements;
    }

    @NonNull
    @Override
    public MainListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainListHolder(ListSingleRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainListHolder holder, int position) {
        holder.binding.listItemTV.setText(listElements.get(position));
        holder.binding.listItemTV.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                switch (position){
                    case 0:
                        Log.d(TAG, "onClick: ");
                        activity= (AppCompatActivity) view.getContext();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.rec,new ToDoFragment()).addToBackStack(null).commit();
                        Log.d(TAG, "onClick: replaced");
                        break;
                    case 1:
                        activity= (AppCompatActivity) view.getContext();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.rec,new CommentsFragment()).addToBackStack(null).commit();
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listElements.size();
    }

    class MainListHolder extends RecyclerView.ViewHolder {
        ListSingleRowBinding binding;

        public MainListHolder(@NonNull ListSingleRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}