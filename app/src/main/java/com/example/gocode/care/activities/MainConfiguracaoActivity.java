package com.example.gocode.care.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.gocode.care.R;

public class MainConfiguracaoActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Button btQuemSomosNosMain;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_configuracao);

        //TABS
        mToolbar = (Toolbar) findViewById(R.id.tbSetting);
        mToolbar.setTitle("Configurações");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btQuemSomosNosMain = (Button) findViewById(R.id.btQuemSomosNosMain);
        btQuemSomosNosMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainConfiguracaoActivity.this, QuemSomosNosActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }
}
