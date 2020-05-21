package com.zhh.image.compressor.jetpackdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhh.image.compressor.jetpackdemo.adapter.WordAdapter;
import com.zhh.image.compressor.jetpackdemo.room.Word;
import com.zhh.image.compressor.jetpackdemo.viewmodel.WordViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghai on 2020/5/15.
 * function：
 */
public class HomeFragment extends Fragment {

    private List<Word> allWords = new ArrayList<>();

    private WordAdapter mAdapter;

    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_home,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final WordViewModel viewModel = ViewModelProviders.of(requireActivity()).get(WordViewModel.class);
        viewModel.getWordLiveData().observe(getViewLifecycleOwner(), new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                allWords.clear();
                allWords.addAll(words);
                mAdapter.notifyDataSetChanged();
            }
        });
        recyclerView = view.findViewById(R.id.rv_data);
        mAdapter = new WordAdapter(requireActivity(),allWords);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(mAdapter);

        view.findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Word word = new Word("Hello","你好");
//                viewModel.insertWord(word);
                //找到button 所归属的Controller
                NavController controller= Navigation.findNavController(view);
                //todo:注意这里的id要用action_开头的，否则添加的转场动画不会生效
                controller.navigate(R.id.action_homeFragment_to_inputFragment);
            }
        });

        view.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allWords == null || allWords.size() == 0){
                    Toast.makeText(requireActivity(),"当前无可删除的数据",Toast.LENGTH_SHORT).show();
                    return;
                }
                viewModel.deleteWord(allWords.get(0));
            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        view.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return true;
            }
        });
    }
}
