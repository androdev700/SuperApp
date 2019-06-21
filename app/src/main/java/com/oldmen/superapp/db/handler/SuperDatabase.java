package com.oldmen.superapp.db.handler;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.oldmen.superapp.db.dao.ChannelDao;
import com.oldmen.superapp.db.model.Channel;

@Database(entities = {Channel.class}, version = 1)
public abstract class SuperDatabase extends RoomDatabase {

    private static volatile SuperDatabase INSTANCE;

    public abstract ChannelDao channelDao();

    public static SuperDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SuperDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SuperDatabase.class, "super.db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}