package com.senacead.PI.service;

import com.senacead.PI.entity.Cliente;
import com.senacead.PI.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

  public Cliente cadastrarCliente(String nome, String numero, String email, String cpf) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setNumero(numero);
        cliente.setEmail(email);
        cliente.setCpf(cpf);
        return clienteRepository.save(cliente);
    }
  
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(int id) {
        return clienteRepository.findById(id);
    }

    public boolean update(int id, Cliente clienteAtualizado) {
        if (clienteRepository.existsById(id)) {
            clienteAtualizado.setId(id); 
            clienteRepository.save(clienteAtualizado);
            return true;
        }
        return false;
    }

    public boolean deleteById(int id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
  
}