package com.example.gocode.care.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gocode.care.R;
import com.example.gocode.care.activities.CadastroActivity;
import com.example.gocode.care.activities.LoginActivity;

public class PerfilUserFragmentMain extends Fragment {

    private ImageView ivProfileMain;
    private TextView btEntrarMain;
    private TextView btCadastrar;

    public PerfilUserFragmentMain() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil_user_main, container, false);
        setupViews(v);

        btEntrarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(i);
            }
        });

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CadastroActivity.class);
                getActivity().startActivity(i);
            }
        });

        ivProfileMain.setImageResource(R.drawable.profile_grey);

        return v;
    }

    private void setupViews(View view) {
        btEntrarMain = (TextView) view.findViewById(R.id.btEntrarMain);
        btCadastrar = (TextView) view.findViewById(R.id.btCadastrarMain);
        ivProfileMain = (ImageView) view.findViewById(R.id.ivProfileMain);
    }
}
