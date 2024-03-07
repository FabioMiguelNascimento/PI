package com.senacead.PI.controller;

import com.senacead.PI.entity.Cliente;
import com.senacead.PI.service.ClienteService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clicad")
    public String exibirFormCadastrarClientes() {
        return "cadastroCliente";
    }
    
    @PostMapping("/clicad")
    public String cadastrarCliente(@RequestParam("nome") String nome, @RequestParam("numero") String numero, @RequestParam("email") String email, @RequestParam("cpf") String cpf) {
        clienteService.cadastrarCliente(nome, numero, email, cpf);
        return "redirect:/clientes"; // Redireciona para a página de clientes após o cadastro bem-sucedido
    }
    
    @GetMapping("/edicli")
    public String editarCliente(@RequestParam("id") Long id, Model model) {
        Optional<Cliente> clienteOptional = clienteService.obterClientePorId(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            model.addAttribute("cliente", cliente);
            return "edicli";
        } else {
            return "clienteNaoEncontrado";
        }
    }
}
