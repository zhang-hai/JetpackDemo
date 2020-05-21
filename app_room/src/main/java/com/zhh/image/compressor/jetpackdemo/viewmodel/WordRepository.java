package com.zhh.image.compressor.jetpackdemo.viewmodel;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.zhh.image.compressor.jetpackdemo.room.Word;
import com.zhh.image.compressor.jetpackdemo.room.WordDao;
import com.zhh.image.compressor.jetpackdemo.room.WordDatabase;

import java.util.List;

/**
 * Created by zhanghai on 2020/5/14.
 * function：
 */
public class WordRepository {
    private WordDatabase mWordDB;
    private WordDao mWordDao;

    private LiveData<List<Word>> allWordsLive;

    public WordRepository(Context context){
        mWordDB = WordDatabase.getInstance(context);
        mWordDao = mWordDB.getWordDao();
        allWordsLive = mWordDao.getAllWordsLive();
    }


    public LiveData<List<Word>> getAllWordsLive(){
        return allWordsLive;
    }

    public void insertWord(Word... words){
        new InsertAsyncTask().execute(words);
    }

    public void updateWord(Word... word){
        new UpdateAsyncTask().execute(word);
    }

    public void deleteWord(Word... words){
        new DeleteAsyncTask().execute(words);
    }

    class InsertAsyncTask extends AsyncTask<Word,Void,Void>{
        @Override
        protected Void doInBackground(Word... words) {
            mWordDao.insertWord(words);
            return null;
        }
    }

    class UpdateAsyncTask extends AsyncTask<Word,Void,Void>{
        @Override
        protected Void doInBackground(Word... words) {
            mWordDao.updateWord(words);
            return null;
        }
    }

    class DeleteAsyncTask extends AsyncTask<Word,Void,Void>{
        @Override
        protected Void doInBackground(Word... words) {
            mWordDao.deleteWord(words);
            return null;
        }
    }
}
