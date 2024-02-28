package com.senacead.PI.entity;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Autenticacao {

    private List<Usuario> usuariosCadastrados;

    public Autenticacao() {
        usuariosCadastrados = new ArrayList<>();
        usuariosCadastrados.add(new Usuario("gerente", "123", Usuario.TipoUsuario.GERENTE));
        usuariosCadastrados.add(new Usuario("operador", "123", Usuario.TipoUsuario.OPERADOR));
        usuariosCadastrados.add(new Usuario("vendedor", "123", Usuario.TipoUsuario.VENDEDOR));
    }

    public Usuario autenticar(String nomeUsuario, String senha) {
        for (Usuario usuario : usuariosCadastrados) {
            if (usuario.getNomeUsuario().equals(nomeUsuario) && usuario.getSenha().equals(senha)) {
                return usuario; // Autenticação bem-sucedida
            }
        }
        return null; // Autenticação falhou
    }
}
