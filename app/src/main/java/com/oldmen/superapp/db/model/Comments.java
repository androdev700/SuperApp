package com.oldmen.superapp.db.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "comments")
public class Comments {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "item_id")
    private String mId;

    @ColumnInfo(name = "parent_id")
    private String parentId;

    @ColumnInfo(name = "likes_count")
    private String mComment;

    public Comments(@NonNull String id, String parentId, String comment) {
        this.mId = id;
        this.parentId = parentId;
        this.mComment = comment;
    }

    public String getId() {
        return mId;
    }

    public String getParentId() {
        return parentId;
    }

    public String getComment() {
        return mComment;
    }
}
