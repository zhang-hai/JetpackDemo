package com.zhh.image.compressor.jetpackdemo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.zhh.image.compressor.jetpackdemo.room.Word;
import com.zhh.image.compressor.jetpackdemo.viewmodel.WordViewModel;

/**
 * Created by zhanghai on 2020/5/15.
 * function：
 */
public class InputFragment extends Fragment {

    private Word word = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_input_layotu,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final WordViewModel viewModel = ViewModelProviders.of(requireActivity()).get(WordViewModel.class);
        final EditText etEnglish = view.findViewById(R.id.et_english);
        final EditText etChinese = view.findViewById(R.id.et_chinese);
        final Button button = view.findViewById(R.id.button);
        button.setEnabled(false);
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                button.setEnabled(!TextUtils.isEmpty(etEnglish.getText().toString().trim()) && !TextUtils.isEmpty(etChinese.getText().toString().trim()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        etEnglish.addTextChangedListener(watcher);
        etChinese.addTextChangedListener(watcher);

        button.setText("add");
        if(getArguments() != null && getArguments().containsKey("word")){
            word = getArguments().getParcelable("word");
            if(word != null){
                etEnglish.setText(word.getEnglish_word());
                etChinese.setText(word.getChinese_word());
                button.setText("update");
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String english = etEnglish.getText().toString().trim();
                String chinese = etChinese.getText().toString().trim();
                if(word == null){//插入
                    viewModel.insertWord(new Word(english,chinese));
                }else {//更新
                    word.setEnglish_word(english);
                    word.setChinese_word(chinese);
                    viewModel.updateWord(word);
                }
                NavController controller = Navigation.findNavController(view);

                controller.navigate(R.id.action_inputFragment_to_homeFragment);
            }
        });
    }

}
