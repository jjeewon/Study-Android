package com.jiwon.leakproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LeakActivity extends AppCompatActivity {
    static View.OnClickListener sListener = new View.OnClickListener() {
        private Button preClickedView;
        @Override
        public void onClick(View view) {
            String text = "버튼이 눌렸습니다. 지난 번엔 ("+(preClickedView == null ? "없음" : preClickedView.getText())+"을 눌렀습니다.";
            Toast.makeText(view.getContext(),text,Toast.LENGTH_SHORT).show();
            preClickedView = (Button) view;

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);

        findViewById(R.id.button1).setOnClickListener(sListener);
        findViewById(R.id.button2).setOnClickListener(sListener);
    }
}
