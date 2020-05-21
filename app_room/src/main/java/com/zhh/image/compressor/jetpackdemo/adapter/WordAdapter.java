package com.zhh.image.compressor.jetpackdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.zhh.image.compressor.jetpackdemo.R;
import com.zhh.image.compressor.jetpackdemo.room.Word;

import java.util.List;

/**
 * Created by zhanghai on 2020/5/14.
 * function：适配器
 */
public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private Context mContext;
    private List<Word> mItems;
    private LayoutInflater inflater;

    public WordAdapter(Context context,List<Word> mItems) {
        this.mItems = mItems;
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_data_layout,parent,false);
        final WordViewHolder holder = new WordViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                Word word = mItems.get(pos);
                Intent intent = new Intent();
                Uri uri = Uri.parse("https://fanyi.baidu.com/#en/zh/"+word.getEnglish_word());
                intent.setData(uri);
                intent.setAction(Intent.ACTION_VIEW);
                mContext.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int pos = holder.getAdapterPosition();
                Word word = mItems.get(pos);
                Bundle bundle = new Bundle();
                bundle.putParcelable("word",word);
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.inputFragment,bundle);
                return true;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word word = mItems.get(position);
        holder.tv_sort.setText(String.valueOf(position));
        holder.tv_english.setText(word.getEnglish_word());
        holder.tv_chinese.setText(word.getChinese_word());
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }


    static class WordViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_sort;
        public TextView tv_english;
        public TextView tv_chinese;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_sort = itemView.findViewById(R.id.tv_sort);
            tv_english = itemView.findViewById(R.id.tv_english);
            tv_chinese = itemView.findViewById(R.id.tv_chinese);
        }
    }
}
