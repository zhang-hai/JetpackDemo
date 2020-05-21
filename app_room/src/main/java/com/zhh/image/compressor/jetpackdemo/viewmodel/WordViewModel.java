package com.zhh.image.compressor.jetpackdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.zhh.image.compressor.jetpackdemo.room.Word;

import java.util.List;

/**
 * Created by zhanghai on 2020/5/14.
 * function：
 */
public class WordViewModel extends AndroidViewModel {
    private WordRepository mRepository;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
    }

    public LiveData<List<Word>> getWordLiveData(){
        return mRepository.getAllWordsLive();
    }

    public void insertWord(Word... words){
        mRepository.insertWord(words);
    }

    public void updateWord(Word... words){mRepository.updateWord(words);}

    public void deleteWord(Word... words){
        mRepository.deleteWord(words);
    }
}
