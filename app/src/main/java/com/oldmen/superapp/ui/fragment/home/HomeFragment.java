package com.oldmen.superapp.ui.fragment.home;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.oldmen.superapp.R;
import com.oldmen.superapp.db.handler.SuperDatabase;
import com.oldmen.superapp.db.model.Channel;

import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeViewModel = ViewModelProviders.of(
                this,
                new HomeViewModelFactory(getContext(), SuperDatabase.getInstance(getContext()))
        ).get(HomeViewModel.class);

        homeViewModel.getChannelLiveData().observe(this, new Observer<List<Channel>>() {
            @Override
            public void onChanged(List<Channel> channels) {
                for (Channel channel : channels) {
                    Log.e("TAG", channel.getName());
                }
            }
        });
    }
}
