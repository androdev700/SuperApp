package com.oldmen.superapp.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.oldmen.superapp.db.model.Channel;

import java.util.List;

@Dao
public interface ChannelDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Channel channel);

    @Query("SELECT * FROM Channel")
    LiveData<List<Channel>> getAllChannels();

    @Query("SELECT * FROM Channel")
    List<Channel> getAllChannelsSync();
}
