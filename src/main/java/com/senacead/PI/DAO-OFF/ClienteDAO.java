//package com.senacead.PI.DAO;
//
//import com.senacead.PI.entity.Cliente;
//import com.senacead.PI.conexao.Conexao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ClienteDAO {
//
//    public int cadastrarCliente(Cliente cliente) {
//        String sql = "INSERT INTO CLIENTE (NOME, NUMERO, EMAIL, CPF) VALUES (?, ?, ?, ?)";
//        try (Connection conexao = Conexao.getConexao(); PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//            ps.setString(1, cliente.getNome());
//            ps.setString(2, cliente.getNumero());
//            ps.setString(3, cliente.getEmail());
//            ps.setString(4, cliente.getCpf());
//
//            int linhasAfetadas = ps.executeUpdate();
//            if (linhasAfetadas > 0) {
//                // Obtém o ID gerado após o cadastro
//                ResultSet generatedKeys = ps.getGeneratedKeys();
//                if (generatedKeys.next()) {
//                    return generatedKeys.getInt(1);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return -1; // Retorna -1 em caso de erro
//    }
//
//    public List<Cliente> obterTodosClientes() {
//        List<Cliente> clientes = new ArrayList<>();
//        Connection conexao = Conexao.getConexao();
//        if (conexao != null) {
//            String sql = "SELECT * FROM CLIENTE";
//            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
//                ResultSet rs = ps.executeQuery();
//                while (rs.next()) {
//                    Cliente cliente = new Cliente();
//                    cliente.setId(rs.getInt("id"));
//                    cliente.setNome(rs.getString("nome"));
//                    cliente.setNumero(rs.getString("numero"));
//                    cliente.setEmail(rs.getString("email"));
//                    cliente.setCpf(rs.getString("cpf"));
//                    clientes.add(cliente);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return clientes;
//    }
//
//    public boolean atualizarCliente(Cliente cliente) {
//        String sql = "UPDATE CLIENTE SET NOME = ?, NUMERO = ?, EMAIL = ?, CPF = ? WHERE ID = ?";
//        try (Connection conexao = Conexao.getConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {
//            ps.setString(1, cliente.getNome());
//            ps.setString(2, cliente.getNumero());
//            ps.setString(3, cliente.getEmail());
//            ps.setString(4, cliente.getCpf());
//            ps.setInt(5, cliente.getId());
//
//            int linhasAfetadas = ps.executeUpdate();
//            return linhasAfetadas > 0; // Retorna true se uma ou mais linhas foram afetadas
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false; // Em caso de exceção, retorna false.
//        }
//    }
//
//    public boolean excluirCliente(int clienteID) {
//        String sql = "DELETE FROM CLIENTE WHERE ID = ?";
//        try (Connection conexao = Conexao.getConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {
//            ps.setInt(1, clienteID);
//
//            int linhasAfetadas = ps.executeUpdate();
//            return linhasAfetadas > 0; // Retorna true se uma ou mais linhas foram afetadas
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false; // Em caso de exceção, retorna false.
//        }
//    }
//
//    public Cliente obterClientePorID(int clienteID) {
//        String sql = "SELECT * FROM CLIENTE WHERE id = ?";
//
//        try (Connection conexao = Conexao.getConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {
//
//            ps.setInt(1, clienteID);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                Cliente cliente = new Cliente();
//                cliente.setId(rs.getInt("id"));
//                cliente.setNome(rs.getString("nome"));
//                cliente.setNumero(rs.getString("numero"));
//                cliente.setEmail(rs.getString("email"));
//                cliente.setCpf(rs.getString("cpf"));
//
//                return cliente;
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null; // Retorna null se o cliente não for encontrado
//    }
//
//    public int obterUltimoID() {
//        Connection conexao = Conexao.getConexao();
//        if (conexao != null) {
//            String sql = "SELECT LAST_INSERT_ID() AS id";
//            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
//                ResultSet rs = ps.executeQuery();
//                if (rs.next()) {
//                    return rs.getInt("id");
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return -1; // Retorna -1 em caso de erro
//    }
//}
