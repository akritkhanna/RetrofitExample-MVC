package com.example.retrofitexample_mvc.screens.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.retrofitexample_mvc.R;
import com.example.retrofitexample_mvc.adapters.recycler_adapter.TodoRecyclerAdapter;
import com.example.retrofitexample_mvc.databinding.ActivityTodoBinding;
import com.example.retrofitexample_mvc.models.Todo;
import com.example.retrofitexample_mvc.retrofit.ApiInterface;
import com.example.retrofitexample_mvc.retrofit.RestClient;
import com.example.retrofitexample_mvc.viewModel.ToDoViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoActivity extends AppCompatActivity {

    private ActivityTodoBinding binding;
    private static final String TAG = "TodoActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTodoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<Todo> todos = new ArrayList<>();

        ToDoViewModel viewModel = new ViewModelProvider(this).get(ToDoViewModel.class);
        TodoRecyclerAdapter todoRecyclerAdapter = new TodoRecyclerAdapter(todos);
        binding.todoRV.setAdapter(todoRecyclerAdapter);

        viewModel.todoData.observe(this, todosData -> {
            if (todosData != null){
                Log.d(TAG, "onCreate: called");
                todos.addAll(todosData);
                todoRecyclerAdapter.notifyDataSetChanged();
            }
        });

    }
}