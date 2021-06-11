package com.example.retrofitexample_mvc.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitexample_mvc.models.Todo;
import com.example.retrofitexample_mvc.retrofit.ApiInterface;
import com.example.retrofitexample_mvc.retrofit.RestClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToDoViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Todo>> todoData;
    private ApiInterface apiInterface;
    private Call<ArrayList<Todo>> callTodo;
    private static final String TAG = "ToDoViewModel";

    public ToDoViewModel() {
        todoData = new MutableLiveData<>();
        apiInterface = new RestClient().getClient().create(ApiInterface.class);
        getTodoList();
        Log.d(TAG, "ToDoViewModel: i will be called only once");
    }


    public void getTodoList() {
       callTodo = apiInterface.getTodo();
       callTodo.enqueue(new Callback<ArrayList<Todo>>() {
            @Override
            public void onResponse(Call<ArrayList<Todo>> call, Response<ArrayList<Todo>> response) {
                if (response.isSuccessful()){
                    todoData.postValue(response.body());
                }else {

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Todo>> call, Throwable t) {

            }
        });
    }


    public void cancelRequest(){
        if (callTodo != null){
            callTodo.cancel();
        }
    }
}
