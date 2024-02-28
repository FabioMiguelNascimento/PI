//package com.senacead.PI.DAO;
//
//import com.senacead.PI.conexao.Conexao;
//import com.senacead.PI.entity.ItemVenda;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class ItemVendaDAO {
//
//    public boolean inserirItemVenda(ItemVenda itemVenda) {
//        try (Connection conexao = Conexao.getConexao(); PreparedStatement pstmt = conexao.prepareStatement(
//                "INSERT INTO ItensVenda (NomeProduto, Quantidade, Valor, IDVenda) VALUES (?, ?, ?, ?)")) {
//
//            pstmt.setString(1, itemVenda.getNomeProduto());
//            pstmt.setInt(2, itemVenda.getQuantidade());
//            pstmt.setFloat(3, itemVenda.getValorUnitario());
//            pstmt.setInt(4, itemVenda.getIdVenda());
//
//            int linhasAfetadas = pstmt.executeUpdate();
//
//            return linhasAfetadas > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return false; // Erro na inserção
//    }
//}
