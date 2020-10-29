package com.jiwon.gradlesamples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView packageNameTextView = (TextView)findViewById(R.id.package_name);
        final TextView versionNameTextView = (TextView)findViewById(R.id.version_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        packageNameTextView.setText(BuildConfig.APPLICATION_ID);
        versionNameTextView.setText(BuildConfig.VERSION_NAME);
        setSupportActionBar(toolbar);
        Toast.makeText(MainActivity.this,getString(R.string.api_uri)+"에 액세스",Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new GreatFeature().doIt(MainActivity.this);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
}
