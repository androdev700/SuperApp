package com.oldmen.superapp.ui.activity.viewChannel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.oldmen.superapp.R;
import com.oldmen.superapp.db.handler.SuperDatabase;
import com.oldmen.superapp.db.model.Channel;
import com.oldmen.superapp.db.model.Comments;
import com.oldmen.superapp.db.model.Likes;
import com.oldmen.superapp.ui.fragment.home.HomeViewModel;
import com.oldmen.superapp.ui.fragment.home.HomeViewModelFactory;

import java.util.HashMap;
import java.util.List;

public class ViewChannel2Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ViewChannelAdapter2 mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_channel);
        mRecyclerView = findViewById(R.id.view_channel_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);

        HomeViewModel homeViewModel = ViewModelProviders.of(
                this,
                new HomeViewModelFactory(this, SuperDatabase.getInstance(this))
        ).get(HomeViewModel.class);

        List<Likes> likesList = homeViewModel.getLikes();
        HashMap<String, Integer> map = new HashMap<>();
        for (Likes like : likesList) {
            map.put(like.getId(), like.getCount());
        }

        List<Comments> commentList = homeViewModel.getComments();
        HashMap<String, Integer> commentCounts = new HashMap<>();
        for (Comments comments : commentList) {
            int currentCount = commentCounts.get(comments.getParentId()) == null ? 0 : commentCounts.get(comments.getParentId());
            commentCounts.put(comments.getParentId(), currentCount + 1);
        }

        mAdapter = new ViewChannelAdapter2(this, map, commentCounts, commentList);
        mRecyclerView.setAdapter(mAdapter);

    }
}
