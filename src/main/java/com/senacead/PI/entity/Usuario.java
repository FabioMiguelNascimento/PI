package com.senacead.PI.entity;

public class Usuario {


    private String nomeUsuario;
    private String senha;
    private TipoUsuario tipoUsuario;

    public enum TipoUsuario {
        GERENTE, OPERADOR, VENDEDOR
    }

    public Usuario(String nomeUsuario, String senha, TipoUsuario tipoUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
