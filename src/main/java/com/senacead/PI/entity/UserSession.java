package com.senacead.PI.entity;

public class UserSession {

    private static UserSession instance;
    private Usuario.TipoUsuario tipoUsuario;

    private UserSession() {
        tipoUsuario = Usuario.TipoUsuario.OPERADOR;
    }
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setTipoUsuario(Usuario.TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario.TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
}
