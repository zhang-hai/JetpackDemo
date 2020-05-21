package com.zhh.image.compressor.jetpackdemo.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by zhanghai on 2020/5/14.
 * function：创建数据库
 */
@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase instance;

    public synchronized static WordDatabase getInstance(Context context){
        if(instance == null){
            instance = Room
                    .databaseBuilder(context.getApplicationContext(),WordDatabase.class,"word_database")
                    .build();
        }
        return instance;
    }

    public abstract WordDao getWordDao();
}
