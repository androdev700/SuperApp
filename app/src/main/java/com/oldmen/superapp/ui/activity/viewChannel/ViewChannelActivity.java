package com.oldmen.superapp.ui.activity.viewChannel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

import com.oldmen.superapp.R;
import com.oldmen.superapp.ui.fragment.discovery.DiscoveryAdapter;

public class ViewChannelActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ViewChannelAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_channel);
        mRecyclerView = findViewById(R.id.view_channel_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ViewChannelAdapter();
        mRecyclerView.setAdapter(mAdapter);

    }
}
