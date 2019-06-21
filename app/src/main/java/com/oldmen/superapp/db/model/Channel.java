package com.oldmen.superapp.db.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "channel")
public class Channel {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "channel_id")
    private String mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "channel_type")
    private String mType;

    public Channel(@NonNull String id, String name, String type) {
        this.mId = id;
        this.mName = name;
        this.mType = type;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getType() {
        return mType;
    }
}
