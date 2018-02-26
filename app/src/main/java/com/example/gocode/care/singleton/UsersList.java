package com.example.gocode.care.singleton;

import android.content.Context;

import com.example.gocode.care.models.Usuario;

import java.util.ArrayList;

public class UsersList {

    private static UsersList instancia;
    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public static UsersList getInstance() {
        if (instancia == null) {
            instancia = new UsersList();
        }
        return instancia;
    }

    private UsersList() {
    }

    public void salvarUsuarios(Context context, Usuario usuario) {
        if (!listaUsuarios.contains(usuario)) {
            listaUsuarios.add(usuario);
        }
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    private void limparLista(ArrayList<Usuario> lista) {
        lista.clear();
    }
}
