package com.jiwon.databindingsample;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;

import com.jiwon.databindingsample.databinding.ActivityMainBinding;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Bind Object 얻기
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        // Binding 오브젝트를 User에 설정
        binding.setUser(new User("gomdol",26));

        String date = (String) DateFormat.format("yyyy/MM/dd/ kk:mm:ss", Calendar.getInstance());
        binding.textTime.setText(date);
        // 뷰에 id 지정되어 있으면, Binding 오브젝트로부터 뷰에 대한 참조 얻을 수 있음

    }
}
