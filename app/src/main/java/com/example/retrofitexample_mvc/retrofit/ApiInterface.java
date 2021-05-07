package com.example.retrofitexample_mvc.retrofit;

import com.example.retrofitexample_mvc.models.Todo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET("todos/")
    Call<ArrayList<Todo>> getTodo();
}
