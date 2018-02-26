package com.example.gocode.care.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gocode.care.R;
import com.example.gocode.care.models.Usuario;
import com.example.gocode.care.singleton.UsersList;


public class CadastroActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etIdade;
    private EditText etCpf;
    private EditText etCidade;
    private EditText etEstado;
    private EditText etTelefone;
    private EditText etDescricao;
    private EditText etExperiencia;
    private EditText etEmail;
    private EditText etSenha;
    private EditText etRepetirSenha;
    private TextView tvErroCadastro;
    private Button btConfirmar;

    private String nome;
    private String idade;
    private String cpf;
    private String cidade;
    private String estado;
    private String telefone;
    private String descricao;
    private String experiencia;
    private String email;
    private String senha;
    private String repetirSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        setupViews();
        ActionBar bar = getSupportActionBar();
        bar.hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageCadastro);
        imageView.setImageResource(R.drawable.onlinelogomaker_081716_2253);

        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nome = etNome.getText().toString();
                idade = etIdade.getText().toString();
                cpf = etCpf.getText().toString();
                cidade = etCidade.getText().toString();
                estado = etEstado.getText().toString();
                telefone = etTelefone.getText().toString();
                descricao = etDescricao.getText().toString();
                experiencia = etExperiencia.getText().toString();
                email = etEmail.getText().toString();
                senha = etSenha.getText().toString();
                repetirSenha = etRepetirSenha.getText().toString();

                if (senha.equals(repetirSenha)) {
                    AlertDialog alertDialog = null;
                    AlertDialog.Builder alert = new AlertDialog.Builder(CadastroActivity.this, R.style.styleProgressDialog);
                    alert.setCancelable(false);
                    alert.setTitle("Termos de Uso");
                    alert.setMessage("Termos de uso...");
                    alert.setPositiveButton("Concordo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Usuario usuario = new Usuario(
                                    nome,
                                    idade,
                                    cpf,
                                    cidade,
                                    estado,
                                    telefone,
                                    descricao,
                                    experiencia,
                                    email,
                                    senha,
                                    repetirSenha);

                            UsersList.getInstance().salvarUsuarios(CadastroActivity.this, usuario);
                            Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso.", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(CadastroActivity.this, FeedActivity.class);
                            startActivity(i);

                        }
                    });
                    alert.setNegativeButton("Não concordo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CadastroActivity.this, "Usuário não cadastrado.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                    alertDialog = alert.create();
                    alertDialog.show();
                } else {
                    tvErroCadastro.setText("*As senhas não são iguais");
                }
            }
        });
    }

    public void setupViews() {
        etNome = (EditText) findViewById(R.id.etNome);
        etIdade = (EditText) findViewById(R.id.etIdade);
        etCpf = (EditText) findViewById(R.id.etCpf);
        etCidade = (EditText) findViewById(R.id.etCidade);
        etEstado = (EditText) findViewById(R.id.etEstado);
        etTelefone = (EditText) findViewById(R.id.etTelefone);
        etDescricao = (EditText) findViewById(R.id.etDescricao);
        etExperiencia = (EditText) findViewById(R.id.etExperiencia);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);
        etRepetirSenha = (EditText) findViewById(R.id.etRepetirSenha);
        tvErroCadastro = (TextView) findViewById(R.id.tvErroCadastro);
        btConfirmar = (Button) findViewById(R.id.btConfirmarCadastro);
    }

}
