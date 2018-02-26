package com.example.gocode.care.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;;
import android.widget.TextView;

import com.example.gocode.care.R;
import com.example.gocode.care.activities.PerfilActivity;
import com.example.gocode.care.models.Usuario;

public class PerfilUserFragment extends Fragment {

    private Usuario usuarioPerfil;
    private ImageView ivUser;
    private TextView tvNome;
    private TextView tvVerPerfil;

    public PerfilUserFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil_user, container, false);
        setupViews(v);

        usuarioPerfil = new Usuario("Eduarda Souza", R.drawable.cuidador2);
        ivUser.setImageResource(usuarioPerfil.getFoto());
        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PerfilActivity.class);
                i.putExtra("user", usuarioPerfil);
                getActivity().startActivity(i);
            }
        });
        tvNome.setText(usuarioPerfil.getNome());
        tvVerPerfil.setText("Ver seu perfil");

        return v;
    }

    private void setupViews(View view) {
        ivUser = (ImageView) view.findViewById(R.id.ivUserMenu);
        tvNome = (TextView) view.findViewById(R.id.tvNomeMenu);
        tvVerPerfil = (TextView) view.findViewById(R.id.tvVerPerfilMenu);
    }
}
