package com.example.retrofitexample_mvc.screens.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.retrofitexample_mvc.R;
import com.example.retrofitexample_mvc.adapters.recycler_adapter.TodoRecyclerAdapter;
import com.example.retrofitexample_mvc.databinding.ActivityTodoBinding;
import com.example.retrofitexample_mvc.models.Todo;
import com.example.retrofitexample_mvc.retrofit.ApiInterface;
import com.example.retrofitexample_mvc.retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoActivity extends AppCompatActivity {

    private ActivityTodoBinding binding;
    private ApiInterface apiInterface;
    private List<Todo> todos;
    private TodoRecyclerAdapter todoRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTodoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        todos = new ArrayList<>(); //initialize an empty list
        todoRecyclerAdapter = new TodoRecyclerAdapter(todos); // initialize your adapter with empty list
        binding.todoRV.setAdapter(todoRecyclerAdapter); //set your adapter


        apiInterface = new RestClient().getClient().create(ApiInterface.class); //map your rest client with your api calls

        //Call the api
        getTodo();

    }

    private void getTodo(){
        binding.progressBar.setVisibility(View.VISIBLE); //show progress bar
        apiInterface.getTodo().enqueue(new Callback<ArrayList<Todo>>() {
            @Override
            public void onResponse(Call<ArrayList<Todo>> call, Response<ArrayList<Todo>> response) {
                binding.progressBar.setVisibility(View.GONE); //remove progress bar when the api has response
                if (response.isSuccessful()){
                    todos.addAll(response.body());//if the api call is successful then populate your empty list.
                    todoRecyclerAdapter.notifyDataSetChanged();   //now notify your adapter that list has items so load them.
                }else {
                    Toast.makeText(TodoActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Todo>> call, Throwable t) {

            }
        });
    }
}