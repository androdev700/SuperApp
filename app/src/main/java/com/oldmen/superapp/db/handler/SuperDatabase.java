package com.oldmen.superapp.db.handler;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.oldmen.superapp.db.dao.ChannelDao;
import com.oldmen.superapp.db.dao.CommentsDao;
import com.oldmen.superapp.db.dao.LikesDao;
import com.oldmen.superapp.db.model.Channel;
import com.oldmen.superapp.db.model.Comments;
import com.oldmen.superapp.db.model.Likes;

@Database(entities = {Channel.class, Likes.class, Comments.class}, version = 7)
public abstract class SuperDatabase extends RoomDatabase {

    private static volatile SuperDatabase INSTANCE;

    public abstract ChannelDao channelDao();

    public abstract LikesDao likesDao();

    public abstract CommentsDao commentsDao();

    public static SuperDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SuperDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SuperDatabase.class, "super.db")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}