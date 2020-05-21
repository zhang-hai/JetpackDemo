package com.zhh.image.compressor.jetpackdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zhh.image.compressor.jetpackdemo.adapter.WordAdapter;
import com.zhh.image.compressor.jetpackdemo.databinding.ActivityMainBinding;
import com.zhh.image.compressor.jetpackdemo.room.Word;
import com.zhh.image.compressor.jetpackdemo.room.WordDao;
import com.zhh.image.compressor.jetpackdemo.room.WordDatabase;
import com.zhh.image.compressor.jetpackdemo.viewmodel.WordViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        //创建返回键 左上角
        NavController controller= Navigation.findNavController(this,R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this,controller);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController controller=Navigation.findNavController(this,R.id.fragment);
        return controller.navigateUp();
    }
}
