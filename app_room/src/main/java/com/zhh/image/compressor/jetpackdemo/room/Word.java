package com.zhh.image.compressor.jetpackdemo.room;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by zhanghai on 2020/5/14.
 * function：数据库的表类
 */
@Entity(tableName = "word")
public class Word implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    public int id;

    @ColumnInfo
    public String english_word;

    @ColumnInfo
    public String chinese_word;

    public Word(String english_word, String chinese_word) {
        this.english_word = english_word;
        this.chinese_word = chinese_word;
    }

    protected Word(Parcel in) {
        id = in.readInt();
        english_word = in.readString();
        chinese_word = in.readString();
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    public String getEnglish_word() {
        return english_word;
    }

    public void setEnglish_word(String english_word) {
        this.english_word = english_word;
    }

    public String getChinese_word() {
        return chinese_word;
    }

    public void setChinese_word(String chinese_word) {
        this.chinese_word = chinese_word;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(english_word);
        parcel.writeString(chinese_word);
    }


}
