package com.zhh.image.compressor.jetpackdemo.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Created by zhanghai on 2020/5/14.
 * function：操作数据方法接口
 */
@Dao
public interface WordDao {

    @Query("SELECT * FROM word ORDER BY _id ASC")
    LiveData<List<Word>> getAllWordsLive();

    @Insert(entity = Word.class)
    void insertWord(Word... words);

    @Delete(entity = Word.class)
    void deleteWord(Word... words);

    @Update
    void updateWord(Word... word);
}
