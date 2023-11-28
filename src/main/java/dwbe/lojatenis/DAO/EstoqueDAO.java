package dwbe.lojatenis.DAO;

import dwbe.lojatenis.Model.DatabaseConnection;
import dwbe.lojatenis.Model.Estoque;
import dwbe.lojatenis.Model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {
    private Connection connection;
    private ProdutoDAO produtoDAO;

    public EstoqueDAO() {
        connection = DatabaseConnection.getConnection();
        this.produtoDAO = new ProdutoDAO();
    }

    public void cadastrarEstoque(Estoque estoque) {
        String sql = "INSERT INTO Estoque(qtd, estoqueMinimo, estoqueMaximo, produtoId) VALUES(?,?,?,?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, estoque.getQtd());
            pstmt.setInt(2, estoque.getEstoqueMinimo());
            pstmt.setInt(3, estoque.getEstoqueMaximo());
            pstmt.setInt(4, estoque.getProdutoId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating stock failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    estoque.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating stock failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void alterarEstoque(Estoque estoque) {
        String sql = "UPDATE Estoque SET qtd = ?, estoqueMinimo = ?, estoqueMaximo = ?, produto = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, estoque.getQtd());
            pstmt.setInt(2, estoque.getEstoqueMinimo());
            pstmt.setInt(3, estoque.getEstoqueMaximo());
            pstmt.setInt(4, estoque.getProdutoId());
            pstmt.setInt(5, estoque.getId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating stock failed, no rows affected.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Estoque> listarEstoque() {
        List<Estoque> estoques = new ArrayList<>();

        String sql = "SELECT * FROM Estoque";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int qtd = rs.getInt("qtd");
                int estoqueMinimo = rs.getInt("estoqueMinimo");
                int estoqueMaximo = rs.getInt("estoqueMaximo");
                int produtoId = rs.getInt("produtoId");

                Produto produto = produtoDAO.buscarProduto(produtoId);

                Estoque estoque = new Estoque(qtd, estoqueMinimo, estoqueMaximo, produto.getId());
                estoque.setId(id);
                estoques.add(estoque);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return estoques;
    }

    public Estoque buscarEstoque(int id) {
        Estoque estoque = null;
        String sql = "SELECT * FROM Estoque WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int qtd = rs.getInt("qtd");
                int estoqueMinimo = rs.getInt("estoqueMinimo");
                int estoqueMaximo = rs.getInt("estoqueMaximo");
                int produtoId = rs.getInt("produtoId");

                estoque = new Estoque(qtd, estoqueMinimo, estoqueMaximo, produtoId);
                estoque.setId(id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return estoque;
    }

    public void excluirEstoque(int id) {
        String sql = "DELETE FROM Estoque WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting stock failed, no rows affected.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
