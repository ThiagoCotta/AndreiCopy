package dwbe.lojatenis.DAO;

import dwbe.lojatenis.Model.Compra;
import dwbe.lojatenis.Model.DatabaseConnection;

import java.sql.*;
import java.util.List;

public class CompraDAO {
    private Connection connection;

    public CompraDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void cadastrarCompra(Compra compra) {
        String sql = "INSERT INTO EntradaSaida(qtd, valor, data, produtoId) VALUES(?,?,?,?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, compra.getQtd());
            pstmt.setDouble(2, compra.getValor());
            pstmt.setDate(3, new java.sql.Date(compra.getData().getTime()));
            pstmt.setInt(4, compra.getProdutoId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating purchase failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    compra.setId(generatedKeys.getString(1));

                    sql = "INSERT INTO Compra(id, fornecedorId) VALUES(?,?)";
                    try (PreparedStatement pstmtCompra = connection.prepareStatement(sql)) {
                        pstmtCompra.setString(1, compra.getId());
                        pstmtCompra.setInt(2, compra.getFornecedorId());
                        pstmtCompra.executeUpdate();
                    }
                } else {
                    throw new SQLException("Creating purchase failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
