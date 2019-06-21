package com.oldmen.superapp.ui.fragment.home;


import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oldmen.superapp.R;
import com.oldmen.superapp.db.handler.SuperDatabase;
import com.oldmen.superapp.db.model.Channel;

import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private HomeViewModel homeViewModel;
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getContext();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = view.findViewById(R.id.home_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        return view;
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
//                for (Channel channel : channels) {
//                    Log.e("TAG", channel.getName());
//
//                }
                mAdapter = new HomeAdapter(mContext, channels);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
