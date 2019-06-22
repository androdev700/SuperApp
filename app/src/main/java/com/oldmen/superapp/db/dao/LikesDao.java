package com.oldmen.superapp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.oldmen.superapp.db.model.Likes;

import java.util.List;

@Dao
public interface LikesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Likes likes);

    @Query("SELECT * FROM Likes")
    List<Likes> getAllLikes();
}
