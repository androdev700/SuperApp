package com.oldmen.superapp.db.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "likes")
public class Likes {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "item_id")
    private String mId;

    @ColumnInfo(name = "likes_count")
    private int mCount;

    public Likes(@NonNull String id, int count) {
        this.mId = id;
        this.mCount = count;
    }

    public String getId() {
        return mId;
    }

    public int getCount() {
        return mCount;
    }
}
