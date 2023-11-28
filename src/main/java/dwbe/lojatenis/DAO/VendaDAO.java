package dwbe.lojatenis.DAO;

import dwbe.lojatenis.Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class VendaDAO {
    private Connection connection;

    public VendaDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void cadastrarVenda(Venda venda) {
        var entradaSaida = new EntradaSaida(venda.getQtd(), venda.getValor(), venda.getData(), venda.getProdutoId());
        var daoEntradaSaida = new EntradaSaidaDAO();
        daoEntradaSaida.cadastrarEntradaSaida(entradaSaida);

        String sql = "INSERT INTO Venda(id, clienteId) VALUES(?,?)";

        try (PreparedStatement pstmtVenda = connection.prepareStatement(sql)) {
            pstmtVenda.setString(1, entradaSaida.getId());
            pstmtVenda.setInt(2, venda.getClienteId());
            pstmtVenda.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
