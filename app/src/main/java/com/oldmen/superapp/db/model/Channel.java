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

    @ColumnInfo(name = "description")
    private String mDescription;

    @ColumnInfo(name = "channel_image")
    private String mChannelImage;

    @ColumnInfo(name = "destination_type")
    private String mDestinationType;

    @ColumnInfo(name = "url")
    private String mUrl;

    @ColumnInfo(name = "time")
    private String mTime;

    @ColumnInfo(name = "created_at")
    private Long created_at;



    public Channel(@NonNull String id, String name, String type, String description,
                   String channelImage, String destinationType, String url, String time, Long created_at) {
        this.mId = id;
        this.mName = name;
        this.mType = type;
        this.mDescription = description;
        this.mChannelImage = channelImage;
        this.mDestinationType = destinationType;
        this.mUrl = url;
        this.mTime = time;
        this.created_at = created_at;
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

    public String getDescription() {
        return mDescription;
    }

    public String getChannelImage() {
        return mChannelImage;
    }

    public String getDestinationType() {
        return mDestinationType;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getTime() {
        return mTime;
    }

    public Long getCreated_at() {
        return created_at;
    }
}
