package com.oldmen.superapp.ui.fragment.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.oldmen.superapp.db.handler.SuperDatabase;
import com.oldmen.superapp.db.model.Channel;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private Context context;
    private SuperDatabase superDatabase;

    HomeViewModel(Context context, SuperDatabase superDatabase) {
        this.context = context;
        this.superDatabase = superDatabase;
    }

    LiveData<List<Channel>> getChannelLiveData() {
        return superDatabase.channelDao().getAllChannels();
    }
}
