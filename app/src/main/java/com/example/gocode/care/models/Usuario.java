package com.example.gocode.care.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {

    private String nome;
    private String idade;
    private String cpf;
    private String cidade;
    private String estado;
    private String telefone;
    private String descricao;
    private String experincia;
    private String email;
    private String senha;
    private String repetirSenha;
    private int foto;

    public Usuario() {
    }

    //CONSTRUTOR LOGIN
    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    //CONSTRUTOR MENU
    public Usuario(String nome, int foto) {
        this.nome = nome;
        this.foto = foto;
    }

    //CONSTRUTOR FEED
    public Usuario(String nome, String cidade, String estado, int foto, String descricao, String email, String telefone, String experincia) {
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.foto = foto;
        this.descricao = descricao;
        this.email = email;
        this.telefone = telefone;
        this.experincia = experincia;
    }

    //CONSTRUTOR CADASTRO
    public Usuario(String nome, String idade, String cpf, String cidade, String estado, String telefone, String descricao, String experincia, String email, String senha, String repetirSenha) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.descricao = descricao;
        this.experincia = experincia;
        this.email = email;
        this.senha = senha;
        this.repetirSenha = repetirSenha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRepetirSenha() {
        return repetirSenha;
    }

    public void setRepetirSenha(String repetirSenha) {
        this.repetirSenha = repetirSenha;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getExperincia() {
        return experincia;
    }

    public void setExperincia(String experincia) {
        this.experincia = experincia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (foto != usuario.foto) return false;
        if (nome != null ? !nome.equals(usuario.nome) : usuario.nome != null) return false;
        if (idade != null ? !idade.equals(usuario.idade) : usuario.idade != null) return false;
        if (cpf != null ? !cpf.equals(usuario.cpf) : usuario.cpf != null) return false;
        if (cidade != null ? !cidade.equals(usuario.cidade) : usuario.cidade != null) return false;
        if (estado != null ? !estado.equals(usuario.estado) : usuario.estado != null) return false;
        if (telefone != null ? !telefone.equals(usuario.telefone) : usuario.telefone != null)
            return false;
        if (descricao != null ? !descricao.equals(usuario.descricao) : usuario.descricao != null)
            return false;
        if (experincia != null ? !experincia.equals(usuario.experincia) : usuario.experincia != null)
            return false;
        if (email != null ? !email.equals(usuario.email) : usuario.email != null) return false;
        if (senha != null ? !senha.equals(usuario.senha) : usuario.senha != null) return false;
        return repetirSenha != null ? repetirSenha.equals(usuario.repetirSenha) : usuario.repetirSenha == null;

    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (idade != null ? idade.hashCode() : 0);
        result = 31 * result + (cpf != null ? cpf.hashCode() : 0);
        result = 31 * result + (cidade != null ? cidade.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (senha != null ? senha.hashCode() : 0);
        result = 31 * result + (repetirSenha != null ? repetirSenha.hashCode() : 0);
        return result;
    }

    // PARCELABLE
    public Usuario(Parcel parcel) {
        setNome(parcel.readString());
        setIdade(parcel.readString());
        setCpf(parcel.readString());
        setCidade(parcel.readString());
        setEstado(parcel.readString());
        setEmail(parcel.readString());
        setSenha(parcel.readString());
        setRepetirSenha(parcel.readString());
        setFoto(parcel.readInt());
        setTelefone(parcel.readString());
        setDescricao(parcel.readString());
        setExperincia(parcel.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getNome());
        dest.writeString(getIdade());
        dest.writeString(getCpf());
        dest.writeString(getCidade());
        dest.writeString(getEstado());
        dest.writeString(getEmail());
        dest.writeString(getSenha());
        dest.writeString(getRepetirSenha());
        dest.writeInt(getFoto());
        dest.writeString(getTelefone());
        dest.writeString(getDescricao());
        dest.writeString(getExperincia());
    }

    public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel source) {
            return new Usuario(source);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
