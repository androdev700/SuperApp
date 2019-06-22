package com.oldmen.superapp.ui.fragment.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.oldmen.superapp.db.handler.SuperDatabase;
import com.oldmen.superapp.db.model.Channel;
import com.oldmen.superapp.db.model.Comments;
import com.oldmen.superapp.db.model.Likes;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private Context context;
    private SuperDatabase superDatabase;

    HomeViewModel(Context context, SuperDatabase superDatabase) {
        this.context = context;
        this.superDatabase = superDatabase;
    }

    public LiveData<List<Channel>> getChannelLiveData() {
        return superDatabase.channelDao().getAllChannels();
    }

    public List<Channel> getChannels() {
        return superDatabase.channelDao().getAllChannelsSync();
    }

    public List<Likes> getLikes() {
        return superDatabase.likesDao().getAllLikes();
    }

    public List<Comments> getComments() {
        return superDatabase.commentsDao().getAllComments();
    }
}
