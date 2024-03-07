package com.senacead.PI.controller;

import com.senacead.PI.entity.Cliente;
import com.senacead.PI.service.ClienteService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        return "redirect:/clientes"; 
    }

    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.obterTodosClientes(); 
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

    @GetMapping("/editarCliente")
    public String exibirFormularioEditarCliente(@RequestParam("id") Long id, Model model) {
        Optional<Cliente> clienteOptional = clienteService.obterClientePorId(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            model.addAttribute("cliente", cliente);
            return "editarCliente"; 
        } else {
            return "clienteNaoEncontrado"; 
        }
    }

    @PostMapping("/salvarEdicaoCliente")
    public String salvarClienteEditado(@ModelAttribute("cliente") Cliente cliente) {
        boolean sucesso = clienteService.atualizarCliente(cliente.getId(), cliente);
        if (sucesso) {
            return "redirect:/clientes"; 
        } else {
            return "redirect:/erro";
        }
    }

    @PostMapping("/excluirCliente")
    public String excluirCliente(@RequestParam("id") Long id) {
        boolean sucesso = clienteService.excluirCliente(id);
        if (sucesso) {
            return "redirect:/clientes";
        } else {
            return "redirect:/erro";
        }
    }

}
