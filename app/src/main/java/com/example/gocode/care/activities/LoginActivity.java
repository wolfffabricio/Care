package com.example.gocode.care.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gocode.care.R;
import com.example.gocode.care.extras.UtilCare;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String EMAIL = "eduarda.souza@gmail.com";
    private static final String SENHA = "123456";
    private static final int SIGN_IN_CODE = 9000;

    public static GoogleApiClient googleApiClient;
    private ConnectionResult connectionResult;
    private boolean isConsentScreenOpened;
    private boolean isSignInButtonClicked;

    private SignInButton btGPlus;
    private ProgressDialog progressDialog;
    private EditText etEmail;
    private EditText etSenha;
    private Button btEntrar;
    private Button btEsqueceuSenha;
    private Button btRegistrar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar bar = getSupportActionBar();
        bar.hide();
        setupViews();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EMAIL.equals(etEmail.getText().toString()) && SENHA.equals(etSenha.getText().toString())) {
                    Intent i = new Intent(LoginActivity.this, FeedActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "Usuário não encontrado. Tente novamente", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, EsqueceuSuaSenhaActivity.class);
                startActivity(i);
            }
        });

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(i);
            }
        });

        //PLUS
        btGPlus.setOnClickListener(LoginActivity.this);
        googleApiClient = new GoogleApiClient.Builder(LoginActivity.this)
                .addConnectionCallbacks(LoginActivity.this)
                .addOnConnectionFailedListener(LoginActivity.this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btSignInCustom) {
            if (!googleApiClient.isConnected()) {
                isSignInButtonClicked = true;
                resolveSignIn();
            }
        }
    }

    public void resolveSignIn() {
        if (connectionResult != null && connectionResult.hasResolution()) {
            if (UtilCare.verifyConnection(LoginActivity.this)) {
                try {
                    isConsentScreenOpened = true;
                    connectionResult.startResolutionForResult(LoginActivity.this, SIGN_IN_CODE);
                    getProgressDiaLog(LoginActivity.this);
                } catch (IntentSender.SendIntentException e) {
                    isConsentScreenOpened = false;
                    googleApiClient.connect();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Sem conexão com a rede. Por favor, verifique seu WIFI ou Dados Móveis.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SIGN_IN_CODE) {
            isConsentScreenOpened = false;

            if (resultCode != RESULT_OK) {
                isSignInButtonClicked = false;
            }

            if (!googleApiClient.isConnecting()) {
                googleApiClient.connect();
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        isConsentScreenOpened = false;
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        Intent i = new Intent(LoginActivity.this, FeedActivity.class);
        startActivity(i);
    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), LoginActivity.this, 0).show();
            return;
        }

        if (!isConsentScreenOpened) {
            connectionResult = result;

            if (isSignInButtonClicked) {
                resolveSignIn();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    private void getProgressDiaLog(Context context) {
        progressDialog = new ProgressDialog(context, R.style.styleProgressDialog);
        progressDialog.setTitle("Entrar");
        progressDialog.setMessage("Conectando...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    public void setupViews() {
        btGPlus = (SignInButton) findViewById(R.id.btSignInCustom);
        btGPlus.setSize(SignInButton.SIZE_STANDARD);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);
        btEntrar = (Button) findViewById(R.id.btEntrar);
        btEsqueceuSenha = (Button) findViewById(R.id.btEsqueceuSenha);
        btRegistrar = (Button) findViewById(R.id.btRegistrar);
    }
}