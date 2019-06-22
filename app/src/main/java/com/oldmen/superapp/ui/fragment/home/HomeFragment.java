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

import com.oldmen.superapp.Data;
import com.oldmen.superapp.R;
import com.oldmen.superapp.db.dao.ChannelDao;
import com.oldmen.superapp.db.handler.SuperDatabase;
import com.oldmen.superapp.db.model.Channel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
                if (channels.size() == 0) {
                    channels = cookData();
                }

                mAdapter = new HomeAdapter(mContext, channels);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

            }
        });
    }

    private List<Channel> cookData() {
        try {
            JSONArray array = new JSONArray(Data.HOME_SCREEN_DEMO_DATA);
            Log.e("HOMEFRAGMENT", "cookData: Array size : " + array.length());
            int index = 0;
            List<Channel> channels = new ArrayList<>(array.length());
            while(index < array.length()) {
                Log.e("HOMEFRAGMENT", "cookData: channel size : " + channels.size());
                JSONObject object = array.getJSONObject(index);
                Channel channel = new Channel(
                        object.getString("id"),
                        object.getString("title"),
                        "public",
                        object.getString("description"),
                        object.getString("image"),
                        null,
                        null,
                        object.getString("secondaryText"),
                        System.currentTimeMillis()
                );

                channels.add(channel);
                ChannelDao channelDao = SuperDatabase.getInstance(mContext).channelDao();
                channelDao.insert(channel);
                index++;
            }
            return channels;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("HOMEFRAGMENT", "cookData: unable to cook");
            return null;
        }
    }
}
