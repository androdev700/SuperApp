package com.oldmen.superapp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface ChannelDao {
    @Insert
    void insert();


}
