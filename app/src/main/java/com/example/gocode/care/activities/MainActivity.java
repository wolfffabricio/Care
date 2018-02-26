package com.example.gocode.care.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gocode.care.R;

public class MainActivity extends AppCompatActivity {

    private Button btCuidados;
    private Button btEmpregos;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }


        btCuidados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(MainActivity.this, FeedActivityMain.class);
                startActivity(i);
            }
        });

        btEmpregos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }

    private void setupViews() {
        btCuidados = (Button) findViewById(R.id.bt_encontrar_cuidados);
        btEmpregos = (Button) findViewById(R.id.bt_encontrar_empregos);
    }
}
