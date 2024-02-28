package com.senacead.PI.entity;

public class UserSession {

    // Declaração da variável global do tipo de usuário
    private static UserSession instance;
    private Usuario.TipoUsuario tipoUsuario;

    // Construtor privado para evitar criação de instâncias fora desta classe
    private UserSession() {
        tipoUsuario = Usuario.TipoUsuario.OPERADOR;
    }

    // Método para obter a instância única
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    // Método para definir o tipo de usuário após o login
    public void setTipoUsuario(Usuario.TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    // Método para obter o tipo de usuário
    public Usuario.TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
}
