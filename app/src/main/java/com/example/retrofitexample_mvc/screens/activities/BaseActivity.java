package com.example.retrofitexample_mvc.screens.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.retrofitexample_mvc.R;
import com.example.retrofitexample_mvc.databinding.ActivityBaseBinding;
import com.example.retrofitexample_mvc.fragments.MainListFragment;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG ="base";
    ActivityBaseBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        Log.d(TAG, "onCreate: "+"fragment trans...");
        fragmentTransaction.replace(R.id.baseFL,new MainListFragment()).commit();
        Log.d(TAG, "onCreate: "+"fragment replace...");
    }
}