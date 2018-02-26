package com.example.gocode.care.activities;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gocode.care.R;
import com.example.gocode.care.models.Usuario;

import java.security.Permission;

import me.drakeet.materialdialog.MaterialDialog;

public class UsuarioActivity extends ActionBarActivity {

    private static final int REQUEST_PERMISSIONS_CODE = 128;

    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Usuario user;
    private ImageView ivUser;
    private TextView tvNome;
    private TextView tvDescricao;
    private TextView tvCidade;
    private TextView tvEstado;
    private RatingBar ratingBar;
    private ViewGroup mRoot;
    private TextView tvExperiencia;
    private TextView tvEmail;
    private Button btTelefone;
    private MaterialDialog mMaterialDiaLog;
    private MaterialDialog mCallDialog;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        setupViews();

        if (savedInstanceState != null) {
            user = savedInstanceState.getParcelable("user");
        } else {
            if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getParcelable("user") != null) {
                user = getIntent().getExtras().getParcelable("user");
            } else {
                Toast.makeText(this, "Falha", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        mCollapsingToolbarLayout.setTitle(user.getNome());

        mToolbar = (Toolbar) findViewById(R.id.tbUsuario);
        mToolbar.setTitle(user.getNome());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivUser.setImageResource(user.getFoto());
        tvNome.setText(user.getNome());
        tvDescricao.setText(user.getDescricao());
        tvCidade.setText(user.getCidade());
        tvEstado.setText(user.getEstado());
        ratingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingBar.setIsIndicator(true);
            }
        });
        tvExperiencia.setText(user.getExperincia());
        tvEmail.setText(user.getEmail());
        btTelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMaterialDiaLog = new MaterialDialog(new ContextThemeWrapper(UsuarioActivity.this, R.style.MyAlertDiaLog))
                        .setTitle("Telefone")
                        .setMessage(user.getTelefone())
                        .setPositiveButton("Ligar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                i = new Intent(Intent.ACTION_CALL);
                                i.setData(Uri.parse("tel:" + user.getTelefone().trim()));
                                if (ContextCompat.checkSelfPermission(UsuarioActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                    if (ActivityCompat.shouldShowRequestPermissionRationale(UsuarioActivity.this, Manifest.permission.CALL_PHONE)) {
                                        callDialog("É preciso a permissão CALL_PHONE para apresentação dos eventos locais", new String[]{Manifest.permission.CALL_PHONE});

                                    } else {
                                        ActivityCompat.requestPermissions(UsuarioActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PERMISSIONS_CODE);
                                    }

                                } else {
                                    startActivity(i);
                                }
                            }
                        })
                        .setNegativeButton("Voltar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDiaLog.dismiss();
                            }
                        });
                mMaterialDiaLog.show();
            }
        });
    }

    private void setupViews() {
        ivUser = (ImageView) findViewById(R.id.ivUserClick);
        tvNome = (TextView) findViewById(R.id.tvNomeClick);
        tvDescricao = (TextView) findViewById(R.id.tvDescrciaoClick);
        tvCidade = (TextView) findViewById(R.id.tvCidadeClick);
        tvEstado = (TextView) findViewById(R.id.tvEstadoClick);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        mRoot = (ViewGroup) findViewById(R.id.llTvDescricao);
        tvExperiencia = (TextView) findViewById(R.id.tvExperienciaClick);
        tvEmail = (TextView) findViewById(R.id.tvEmailClick);
        btTelefone = (Button) findViewById(R.id.btTelefoneClick);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_usuario_activity, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        MenuItem item = menu.findItem(R.id.action_user_activity);
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
        outState.putParcelable("user", user);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS_CODE:

                for (int j = 0; j < permissions.length; j++) {
                    if (permissions[j].equalsIgnoreCase(Manifest.permission.CALL_PHONE)
                            && grantResults[j] == PackageManager.PERMISSION_GRANTED) {
                        startActivity(i);
                    }
                }
        }
    }

    private void callDialog(String message, final String[] permissions) {

        mCallDialog = new MaterialDialog(UsuarioActivity.this)
                .setTitle("Permissão")
                .setMessage(message)
                .setPositiveButton("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ActivityCompat.requestPermissions(UsuarioActivity.this, permissions, REQUEST_PERMISSIONS_CODE);
                        mCallDialog.dismiss();

                    }
                })
                .setNegativeButton("Cancelar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCallDialog.dismiss();
                    }
                });
        mCallDialog.show();

    }
}
