package com.example.jingziqi.mvvm.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.jingziqi.R;
import com.example.jingziqi.databinding.ActivityMainMvvmBinding;
import com.example.jingziqi.mvvm.viewmodel.JingziqiViewModel;
/**
 三、MVVM -> 双向数据绑定：android  google databinding-》使用的模块和APP模块同时打开databinding
 M:  Model
 V:   xml  + activity
 VM: ViewModel
 优点：角色清晰
 缺点：databinding -》xml包含了少了的代码，xml和activity进行了绑定， xml式activity的拖油瓶
 应用场景：首页/复杂需求多变的页面
 */
public class JingZiQiMVVMActivity extends AppCompatActivity {

    private static  String TAG = JingZiQiMVVMActivity.class.getName();
    JingziqiViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainMvvmBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main_mvvm);
        viewModel = new ViewModelProvider(this,new  ViewModelProvider.AndroidViewModelFactory(getApplication()))
                      .get(JingziqiViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_jingziqi, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                viewModel.onResetSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}