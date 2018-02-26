package com.example.gocode.care.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.gocode.care.R;
import com.example.gocode.care.models.Usuario;

public class PerfilActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Usuario usuarioPerfil;
    private ImageView ivUser;
    private TextView tvNome;
    private TextView tvDescricao;
    private TextView tvCidade;
    private TextView tvEstado;
    private RatingBar ratingBar;
    private ViewGroup mRoot;
    private TextView tvExperiencia;
    private TextView tvEmail;
    private TextView tvTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        setupViews();

        usuarioPerfil = new Usuario("Eduarda Souza", "Santa Maria", "RS", R.drawable.cuidador2,
                "Cuidadora de idosos", "eduarda.souza@gmail.com", "81244450", "Recém-formada em psicologia, já atuei algumas vezes como cuidadora de idosos. Ligue para mais informações.");


        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarPerfil);
        mCollapsingToolbarLayout.setTitle(usuarioPerfil.getNome());

        mToolbar = (Toolbar) findViewById(R.id.tbPerfil);
        mToolbar.setTitle(usuarioPerfil.getNome());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivUser.setImageResource(usuarioPerfil.getFoto());
        tvNome.setText(usuarioPerfil.getNome());
        tvDescricao.setText(usuarioPerfil.getDescricao());
        tvCidade.setText(usuarioPerfil.getCidade());
        tvEstado.setText(usuarioPerfil.getEstado());
        ratingBar.setIsIndicator(true);
        tvExperiencia.setText(usuarioPerfil.getExperincia());
        tvEmail.setText(usuarioPerfil.getEmail());
        tvTelefone.setText(usuarioPerfil.getTelefone());
    }

    private void setupViews() {
        ivUser = (ImageView) findViewById(R.id.ivUserPerfil);
        tvNome = (TextView) findViewById(R.id.tvNomePerfil);
        tvDescricao = (TextView) findViewById(R.id.tvDescrciaoPerfil);
        tvCidade = (TextView) findViewById(R.id.tvCidadePerfil);
        tvEstado = (TextView) findViewById(R.id.tvEstadoPerfil);
        ratingBar = (RatingBar) findViewById(R.id.ratingBarPerfil);
        mRoot = (ViewGroup) findViewById(R.id.llTvDescricaoPerfil);
        tvExperiencia = (TextView) findViewById(R.id.tvExperienciaPerfil);
        tvEmail = (TextView) findViewById(R.id.tvEmailPerfil);
        tvTelefone = (TextView) findViewById(R.id.tvTelefonePerfil);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_perfil_activity, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        MenuItem item = menu.findItem(R.id.action_perfil_activity);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB) {
            searchView = (SearchView) item.getActionView();
        } else {
            searchView = (SearchView) MenuItemCompat.getActionView(item);
        }

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.search_hint));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("user", usuarioPerfil);
    }
}
