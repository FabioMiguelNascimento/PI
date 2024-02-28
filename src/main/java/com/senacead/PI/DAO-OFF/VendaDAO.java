//package com.senacead.PI.DAO;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Date;
//
//import com.senacead.PI.conexao.Conexao;
//import com.senacead.PI.entity.ItemVenda;
//import com.senacead.PI.entity.Venda;
//
//public class VendaDAO {
//
//    public List<Venda> recuperarVendas() {
//        List<Venda> vendas = new ArrayList<>();
//
//        String query = "SELECT id, nome_cliente, nome_venda, valor_total, data FROM vendas";
//
//        try (Connection conexao = Conexao.getConexao(); PreparedStatement statement = conexao.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
//
//            while (resultSet.next()) {
//                Venda venda = new Venda();
//                venda.setId(resultSet.getInt("id"));
//                venda.setNomeCliente(resultSet.getString("nome_cliente"));
//                venda.setNomeVenda(resultSet.getString("nome_venda"));
//                venda.setValorTotal(resultSet.getFloat("valor_total"));
//                venda.setData(resultSet.getDate("data"));
//
//                vendas.add(venda);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return vendas;
//    }
//
//    public static boolean inserirVenda(Venda venda, List<ItemVenda> produtos, float valorTotal) {
//        Connection conexao = Conexao.getConexao();
//        if (conexao == null) {
//            System.err.println("Não foi possível conectar ao banco de dados.");
//            return false;
//        }
//
//        PreparedStatement stmtVenda = null;
//        PreparedStatement stmtProduto = null;
//        ResultSet generatedKeys = null;
//
//        try {
//            // Inicie uma transação
//            conexao.setAutoCommit(false);
//
//            // Insira os dados da venda na tabela de vendas
//            String sqlInserirVenda = "INSERT INTO vendas (nome_cliente, nome_venda, data, valor_total) VALUES (?, ?, ?, ?)";
//            stmtVenda = conexao.prepareStatement(sqlInserirVenda, Statement.RETURN_GENERATED_KEYS);
//            stmtVenda.setString(1, venda.getNomeCliente());
//            stmtVenda.setString(2, venda.getNomeVenda());
//            stmtVenda.setDate(3, new java.sql.Date(venda.getData().getTime()));
//            stmtVenda.setFloat(4, venda.getValorTotal());
//            stmtVenda.executeUpdate();
//
//            // Obtenha o ID da venda recém-inserida
//            generatedKeys = stmtVenda.getGeneratedKeys();
//            int idVenda = -1;
//            if (generatedKeys.next()) {
//                idVenda = generatedKeys.getInt(1);
//            } else {
//                throw new SQLException("Falha ao obter o ID da venda.");
//            }
//
//            // Insira os produtos da venda na tabela de produtos da venda
//            String sqlInserirProduto = "INSERT INTO item_venda (id_venda, nome_produto, quantidade, valor_unitario) VALUES (?, ?, ?, ?)";
//            stmtProduto = conexao.prepareStatement(sqlInserirProduto);
//            for (ItemVenda item : produtos) {
//                stmtProduto.setInt(1, idVenda);
//                stmtProduto.setString(2, item.getNomeProduto());
//                stmtProduto.setInt(3, item.getQuantidade());
//                stmtProduto.setFloat(4, item.getValorUnitario());
//                stmtProduto.executeUpdate();
//            }
//
//            // Confirme a transação
//            conexao.commit();
//            return true;
//
//        } catch (SQLException e) {
//            try {
//                conexao.rollback();
//                System.err.println("Transação revertida devido a um erro.");
//            } catch (SQLException rollbackError) {
//                System.err.println("Erro ao reverter a transação.");
//                rollbackError.printStackTrace();
//            }
//            e.printStackTrace();
//            return false;
//        } finally {
//            try {
//                if (stmtVenda != null) {
//                    stmtVenda.close();
//                }
//                if (stmtProduto != null) {
//                    stmtProduto.close();
//                }
//                if (generatedKeys != null) {
//                    generatedKeys.close();
//                }
//                conexao.close();
//            } catch (SQLException closeError) {
//                System.err.println("Erro ao fechar recursos.");
//                closeError.printStackTrace();
//            }
//        }
//    }
//
//    public int getUltimoIdInserido() {
//
//        String sql = "SELECT LAST_INSERT_ID() as id";
//
//        try (Connection conexao = Conexao.getConexao(); PreparedStatement statement = conexao.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
//
//            if (resultSet.next()) {
//                return resultSet.getInt("id") + 1; // Retorna o último ID inserido
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return -1; // Retorna -1 se não conseguir obter o ID
//    }
//
//    public List<ItemVenda> recuperarItensDaVenda(int idVenda) {
//        List<ItemVenda> itensDaVenda = new ArrayList<>();
//
//        String query = "SELECT id_venda, nome_produto, quantidade, valor_unitario FROM item_venda WHERE id_venda = ?";
//
//        try (Connection conexao = Conexao.getConexao(); PreparedStatement statement = conexao.prepareStatement(query)) {
//
//            statement.setInt(1, idVenda);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    ItemVenda item = new ItemVenda();
//                    item.setIdVenda(resultSet.getInt("id_venda"));
//                    item.setNomeProduto(resultSet.getString("nome_produto"));
//                    item.setQuantidade(resultSet.getInt("quantidade"));
//                    item.setValorUnitario(resultSet.getFloat("valor_unitario"));
//
//                    itensDaVenda.add(item);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return itensDaVenda;
//    }
//
//    public boolean editarItemVenda(ItemVenda itemVenda) {
//        String sql = "UPDATE item_venda SET nome_produto = ?, quantidade = ?, valor_unitario = ? WHERE idv = ?";
//
//        try (Connection conexao = Conexao.getConexao(); PreparedStatement statement = conexao.prepareStatement(sql)) {
//
//            statement.setString(1, itemVenda.getNomeProduto());
//            statement.setInt(2, itemVenda.getQuantidade());
//            statement.setFloat(3, itemVenda.getValorUnitario());
//            statement.setInt(4, itemVenda.getIdVenda());
//
//            int linhasAfetadas = statement.executeUpdate();
//
//            if (linhasAfetadas > 0) {
//                return true; // Atualização bem-sucedida
//            } else {
//                return false; // Item de venda não encontrado ou não atualizado
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false; // Erro durante a atualização
//        }
//    }
//
//    public Venda recuperarVendaPorId(int id) {
//        String query = "SELECT id, nome_cliente, nome_venda, valor_total, data FROM vendas WHERE id = ?";
//
//        try (Connection conexao = Conexao.getConexao(); PreparedStatement statement = conexao.prepareStatement(query)) {
//
//            statement.setInt(1, id);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    Venda venda = new Venda();
//                    venda.setId(resultSet.getInt("id"));
//                    venda.setNomeCliente(resultSet.getString("nome_cliente"));
//                    venda.setNomeVenda(resultSet.getString("nome_venda"));
//                    venda.setValorTotal(resultSet.getFloat("valor_total"));
//                    venda.setData(resultSet.getDate("data"));
//
//                    return venda;
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null; // Retorna null se a venda não for encontrada
//    }
//
//    public int getIdItemVendaDaVenda(int vendaId) {
//        int itemId = -1; // Valor padrão, caso não seja encontrado
//
//        try (Connection conexao = Conexao.getConexao(); PreparedStatement statement = conexao
//                .prepareStatement("SELECT id_item_venda FROM item_venda WHERE id_venda = ?")) {
//
//            statement.setInt(1, vendaId);
//
//            try (ResultSet rs = statement.executeQuery()) {
//                if (rs.next()) {
//                    itemId = rs.getInt("id_item_venda"); // Recupere o ID do item de venda
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return itemId;
//    }
//
//    public ItemVenda recuperarItemVendaPorId(int itemId) {
//
//        Connection conexao = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        ItemVenda itemVenda = null;
//
//        try {
//            conexao = Conexao.getConexao();
//            String query = "SELECT * FROM item_venda WHERE id = ?";
//            statement = conexao.prepareStatement(query);
//            statement.setInt(1, itemId);
//            resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                // Recupere os valores do item de venda do ResultSet
//                int id = resultSet.getInt("id");
//                String nomeProduto = resultSet.getString("nome_produto");
//                int quantidade = resultSet.getInt("quantidade");
//                float valorUnitario = resultSet.getFloat("valor_unitario");
//
//                // Crie uma instância de ItemVenda com os valores recuperados
//                itemVenda = new ItemVenda();
//                itemVenda.setId(id);
//                itemVenda.setNomeProduto(nomeProduto);
//                itemVenda.setQuantidade(quantidade);
//                itemVenda.setValorUnitario(valorUnitario);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(); // Lide com exceções de banco de dados de acordo com as necessidades do seu
//            // aplicativo.
//        } finally {
//            // Feche as conexões e recursos
//            if (resultSet != null) {
//                try {
//                    resultSet.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (conexao != null) {
//                try {
//                    conexao.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return itemVenda;
//    }
//
//    public boolean excluirVenda(int idVenda) {
//        Connection conexao = Conexao.getConexao();
//        if (conexao == null) {
//            System.err.println("Não foi possível conectar ao banco de dados.");
//            return false;
//        }
//
//        PreparedStatement stmtVenda = null;
//        PreparedStatement stmtProdutos = null;
//
//        try {
//            // Inicie uma transação
//            conexao.setAutoCommit(false);
//
//            // Exclua os produtos da venda
//            String sqlExcluirProdutos = "DELETE FROM item_venda WHERE id_venda = ?";
//            stmtProdutos = conexao.prepareStatement(sqlExcluirProdutos);
//            stmtProdutos.setInt(1, idVenda);
//            stmtProdutos.executeUpdate();
//
//            // Exclua a venda
//            String sqlExcluirVenda = "DELETE FROM vendas WHERE id = ?";
//            stmtVenda = conexao.prepareStatement(sqlExcluirVenda);
//            stmtVenda.setInt(1, idVenda);
//            int linhasAfetadas = stmtVenda.executeUpdate();
//
//            // Confirme a transação
//            conexao.commit();
//            return linhasAfetadas > 0; // Retorna true se pelo menos uma linha foi afetada (exclusão bem-sucedida)
//
//        } catch (SQLException e) {
//            try {
//                conexao.rollback();
//                System.err.println("Transação revertida devido a um erro.");
//            } catch (SQLException rollbackError) {
//                System.err.println("Erro ao reverter a transação.");
//                rollbackError.printStackTrace();
//            }
//            e.printStackTrace();
//            return false; // Falha na exclusão
//        } finally {
//            try {
//                if (stmtVenda != null) {
//                    stmtVenda.close();
//                }
//                if (stmtProdutos != null) {
//                    stmtProdutos.close();
//                }
//                conexao.close();
//            } catch (SQLException closeError) {
//                System.err.println("Erro ao fechar recursos.");
//                closeError.printStackTrace();
//            }
//        }
//    }
//
//    public List<Venda> recuperarVendasPorCliente(String nomeCliente) {
//        List<Venda> vendasDoCliente = new ArrayList<>();
//        String query = "SELECT id, nome_cliente, nome_venda, valor_total, data FROM vendas WHERE nome_cliente = ?";
//
//        try (Connection conexao = Conexao.getConexao(); PreparedStatement statement = conexao.prepareStatement(query)) {
//
//            statement.setString(1, nomeCliente);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    Venda venda = new Venda();
//                    venda.setId(resultSet.getInt("id"));
//                    venda.setNomeCliente(resultSet.getString("nome_cliente"));
//                    venda.setNomeVenda(resultSet.getString("nome_venda"));
//                    venda.setValorTotal(resultSet.getFloat("valor_total"));
//                    venda.setData(resultSet.getDate("data"));
//
//                    vendasDoCliente.add(venda);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return vendasDoCliente;
//    }
//
//    public List<Venda> recuperarVendasPorIntervaloDeDatas(Date dataInicial, Date dataFinal) {
//        List<Venda> vendasNoIntervalo = new ArrayList<>();
//        String query = "SELECT id, nome_cliente, nome_venda, valor_total, data FROM vendas WHERE data BETWEEN ? AND ?";
//
//        try (Connection conexao = Conexao.getConexao(); PreparedStatement statement = conexao.prepareStatement(query)) {
//
//            statement.setDate(1, new java.sql.Date(dataInicial.getTime()));
//            statement.setDate(2, new java.sql.Date(dataFinal.getTime()));
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    Venda venda = new Venda();
//                    venda.setId(resultSet.getInt("id"));
//                    venda.setNomeCliente(resultSet.getString("nome_cliente"));
//                    venda.setNomeVenda(resultSet.getString("nome_venda"));
//                    venda.setValorTotal(resultSet.getFloat("valor_total"));
//                    venda.setData(resultSet.getDate("data"));
//
//                    vendasNoIntervalo.add(venda);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return vendasNoIntervalo;
//    }
//
//}
