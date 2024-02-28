//package com.senacead.PI.DAO;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import com.senacead.PI.conexao.Conexao;
//import com.senacead.PI.entity.Produto;
//
//public class ProdutoDAO {
//
//    public void cadastrarProduto(Produto produto) {
//
//        String sql = "INSERT INTO PRODUTO (NOME, QUANTIDADE, VALOR) VALUES (?, ?, ?)";
//
//        try (Connection conexao = Conexao.getConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {
//
//            ps.setString(1, produto.getNome());
//            ps.setInt(2, produto.getQuantidade());
//            ps.setFloat(3, produto.getValor());
//
//            int linhasAfetadas = ps.executeUpdate();
//            if (linhasAfetadas > 0) {
//                System.out.println("Produto cadastrado com sucesso!");
//            } else {
//                System.out.println("Erro ao cadastrar o produto.");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Erro ao cadastrar o produto.");
//        }
//    }
//
//    public List<Produto> obterTodosProdutos() {
//
//        List<Produto> produtos = new ArrayList<>();
//        Connection conexao = Conexao.getConexao();
//        if (conexao != null) {
//            String sql = "SELECT * FROM PRODUTO";
//            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
//                ResultSet rs = ps.executeQuery();
//                while (rs.next()) {
//                    Produto produto = new Produto();
//                    produto.setId(rs.getInt("id"));
//                    produto.setNome(rs.getString("nome"));
//                    produto.setQuantidade(rs.getInt("quantidade"));
//                    produto.setValor(rs.getFloat("valor"));
//                    produtos.add(produto);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return produtos;
//    }
//
//    public boolean editarProduto(Produto produto) {
//
//        String sql = "UPDATE PRODUTO SET NOME = ?, QUANTIDADE = ?, VALOR = ? WHERE id = ?";
//
//        try (Connection conexao = Conexao.getConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {
//
//            ps.setString(1, produto.getNome());
//            ps.setInt(2, produto.getQuantidade());
//            ps.setFloat(3, produto.getValor());
//            ps.setLong(4, produto.getId());
//
//            int linhasAfetadas = ps.executeUpdate();
//            return linhasAfetadas > 0;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean excluirProduto(int produtoID) {
//        String sql = "DELETE FROM PRODUTO WHERE id = ?";
//
//        try (Connection conexao = Conexao.getConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {
//
//            ps.setInt(1, produtoID);
//
//            int linhasAfetadas = ps.executeUpdate();
//            return linhasAfetadas > 0;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public Produto obterProdutoPorID(int produtoID) {
//        Connection conexao = Conexao.getConexao();
//        if (conexao != null) {
//            String sql = "SELECT * FROM PRODUTO WHERE id = ?";
//            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
//                ps.setInt(1, produtoID);
//                ResultSet rs = ps.executeQuery();
//                if (rs.next()) {
//                    Produto produto = new Produto();
//                    produto.setId(rs.getInt("id"));
//                    produto.setNome(rs.getString("nome"));
//                    produto.setQuantidade(rs.getInt("quantidade"));
//                    produto.setValor(rs.getFloat("valor"));
//                    return produto;
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return null; // Retorna null se o produto não for encontrado
//    }
//
//}
