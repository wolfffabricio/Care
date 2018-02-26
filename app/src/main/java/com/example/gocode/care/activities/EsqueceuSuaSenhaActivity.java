package com.example.gocode.care.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.example.gocode.care.R;

public class EsqueceuSuaSenhaActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Button btSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_sua_senha);

        //TABS
        mToolbar = (Toolbar) findViewById(R.id.tbSettingEss);
        mToolbar.setTitle("Ajuda com login");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
