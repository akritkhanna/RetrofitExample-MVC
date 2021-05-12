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
import com.example.retrofitexample_mvc.adapters.recycler_adapter.CommentsRecyclerAdapter;
import com.example.retrofitexample_mvc.databinding.FragmentCommentsBinding;
import com.example.retrofitexample_mvc.models.Comments;
import com.example.retrofitexample_mvc.retrofit.ApiInterface;
import com.example.retrofitexample_mvc.retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommentsFragment#} factory method to
 * create an instance of this fragment.
 */
public class CommentsFragment extends Fragment {
    private static final String TAG ="comments" ;
    FragmentCommentsBinding binding;
    List<Comments> comments;
    ApiInterface apiInterface;
    CommentsRecyclerAdapter commentsRecyclerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentCommentsBinding.inflate(inflater,container,false);
        comments= new ArrayList<>();
        Log.d(TAG, "onCreate: "+comments);
        commentsRecyclerAdapter=new CommentsRecyclerAdapter(comments);
        binding.commentsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.commentsRV.setAdapter(commentsRecyclerAdapter);

        Log.d(TAG, "onCreate: api interface");
        apiInterface= new RestClient().getClient().create(ApiInterface.class);

        Log.d(TAG, "onCreate: call api");
        getComments();
        Log.d(TAG, "onCreate: "+comments);
        return binding.getRoot();
    }

    private void getComments()
    {
        apiInterface.getComments().enqueue(new Callback<ArrayList<Comments>>() {
            @Override
            public void onResponse(Call<ArrayList<Comments>> call, Response<ArrayList<Comments>> response) {
                if(response.isSuccessful())
                {
                    Log.d(TAG, "onCreate: get response");
                    comments.addAll(response.body());
                    Log.d(TAG, "onResponse: "+comments.get(0).getName());
                    commentsRecyclerAdapter.notifyDataSetChanged();
                    Log.d(TAG, "onCreate: data set changed");
                }
                else {
                    //Toast.makeText(CommentsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Comments>> call, Throwable t) {

            }
        });
    }

    }
