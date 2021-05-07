package com.example.retrofitexample_mvc.adapters.recycler_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitexample_mvc.databinding.TodoListLayoutBinding;
import com.example.retrofitexample_mvc.models.Todo;

import java.util.List;

public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.TodoViewHolder> {
    private Context context;
    List<Todo> todos;

    public TodoRecyclerAdapter(List<Todo> todos) {
        this.todos = todos;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new TodoViewHolder(TodoListLayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {

       holder.binding.todoTitleTV.setText(todos.get(position).getTitle()); //set the title from the model

       if (todos.get(position).isCompleted()){ //check if the to do is completed or not
           holder.binding.completeIV.setVisibility(View.VISIBLE);
       }else {
           holder.binding.completeIV.setVisibility(View.GONE);
       }
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {
        private TodoListLayoutBinding binding;

        public TodoViewHolder(@NonNull TodoListLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
