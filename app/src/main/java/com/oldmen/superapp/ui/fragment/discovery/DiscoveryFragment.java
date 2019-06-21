package com.oldmen.superapp.ui.fragment.discovery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oldmen.superapp.R;
import com.oldmen.superapp.db.handler.SuperDatabase;
import com.oldmen.superapp.db.model.Channel;
import com.oldmen.superapp.ui.fragment.home.HomeViewModel;
import com.oldmen.superapp.ui.fragment.home.HomeViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class DiscoveryFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private HomeViewModel homeViewModel;

    public DiscoveryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);
        mRecyclerView = view.findViewById(R.id.discovery_recyclerview);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeViewModel = ViewModelProviders.of(
                this,
                new HomeViewModelFactory(getContext(), SuperDatabase.getInstance(getContext()))
        ).get(HomeViewModel.class);

        List<Channel> channels = homeViewModel.getChannels();
        List<String> channelIds = new ArrayList<>();
        for (Channel channel : channels) {
            channelIds.add(channel.getId());
        }

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new DiscoveryAdapter(getContext(), channelIds);
        mRecyclerView.setAdapter(mAdapter);
    }
}
