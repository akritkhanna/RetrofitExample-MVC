package com.example.retrofitexample_mvc.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retrofitexample_mvc.R;
import com.example.retrofitexample_mvc.adapters.recycler_adapter.MainListRecyclerAdapter;
import com.example.retrofitexample_mvc.databinding.FragmentMainListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainListFragment#} factory method to
 * create an instance of this fragment.
 */
public class MainListFragment extends Fragment {
    FragmentMainListBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentMainListBinding.inflate(inflater,container,false);
        List<String> listElements=new ArrayList<>();
        listElements.add("Todo List");
        listElements.add("Comments List");
        binding.mainlistRV.setAdapter(new MainListRecyclerAdapter(listElements));
        return binding.getRoot();
    }
}