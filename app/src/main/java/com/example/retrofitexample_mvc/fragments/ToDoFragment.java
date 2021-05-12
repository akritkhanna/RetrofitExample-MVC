package com.example.retrofitexample_mvc.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.retrofitexample_mvc.R;
import com.example.retrofitexample_mvc.adapters.recycler_adapter.TodoRecyclerAdapter;
import com.example.retrofitexample_mvc.databinding.ActivityTodoBinding;
import com.example.retrofitexample_mvc.databinding.FragmentToDoBinding;
import com.example.retrofitexample_mvc.models.Todo;
import com.example.retrofitexample_mvc.retrofit.ApiInterface;
import com.example.retrofitexample_mvc.retrofit.RestClient;
import com.example.retrofitexample_mvc.screens.activities.TodoActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToDoFragment#} factory method to
 * create an instance of this fragment.
 */
public class ToDoFragment extends Fragment {
    FragmentToDoBinding binding;

    private ApiInterface apiInterface;
    private List<Todo> todos;
    private TodoRecyclerAdapter todoRecyclerAdapter;
    private static final String TAG ="to do";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding=FragmentToDoBinding.inflate(inflater,container,false);

        Log.d(TAG, "onCreateView: in to do");
        todos = new ArrayList<>(); //initialize an empty list
        Log.d(TAG, "onCreateView: empty to do");
        todoRecyclerAdapter = new TodoRecyclerAdapter(todos);// initialize your adapter with empty list
        Log.d(TAG, "onCreateView: recycler");
        binding.todoRV.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d(TAG, "onCreateView: layout manager set");
        binding.todoRV.setAdapter(todoRecyclerAdapter); //set your adapter
        Log.d(TAG, "onCreateView: adapter set");
        
        apiInterface = new RestClient().getClient().create(ApiInterface.class); //map your rest client with your api calls
        Log.d(TAG, "onCreateView: api interface");
        //Call the api
        getTodo();
        Log.d(TAG, "onCreateView: call api");
        return binding.getRoot();
    }

    private void getTodo(){
        //binding.progressBar.setVisibility(View.VISIBLE); //show progress bar
        Log.d(TAG, "getTodo: ");
        apiInterface.getTodo().enqueue(new Callback<ArrayList<Todo>>() {
            @Override
            public void onResponse(Call<ArrayList<Todo>> call, Response<ArrayList<Todo>> response) {
                Log.d(TAG, "onResponse: firstz");
               // binding.progressBar.setVisibility(View.GONE); //remove progress bar when the api has response
                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: ");
                    todos.addAll(response.body());//if the api call is successful then populate your empty list.
                    Log.d(TAG, "onResponse: "+todos.get(0));
                    todoRecyclerAdapter.notifyDataSetChanged();   //now notify your adapter that list has items so load them.
                    Log.d(TAG, "onResponse: data changed");
                }else {
                    Log.d(TAG, "onResponse: fail");
                    Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Todo>> call, Throwable t) {

            }
        });
    }

    }
