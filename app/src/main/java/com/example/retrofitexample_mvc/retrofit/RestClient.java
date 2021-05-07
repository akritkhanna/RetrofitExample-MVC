package com.example.retrofitexample_mvc.retrofit;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static final String TAG = "RestClient";

    static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public Retrofit getClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create())) //allow null object to be serialized.
                .build();
    }

}
