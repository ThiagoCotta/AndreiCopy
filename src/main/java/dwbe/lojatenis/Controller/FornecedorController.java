package dwbe.lojatenis.Controller;

import dwbe.lojatenis.DAO.FornecedorDAO;
import dwbe.lojatenis.Model.Fornecedor;
import org.springframework.stereotype.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {
    private FornecedorDAO fornecedorDAO;

    public FornecedorController() {
        this.fornecedorDAO = new FornecedorDAO();
    }

    @PostMapping("/cadastrarFornecedor")
    public void cadastrarFornecedor(String nome, String cpf, String endereco, String sexo, String telefone, String email, String cnpj, int numeroInscricao, String nomeFantasia, String dataDeAbertura, String porte, String atividadeEconomicaPrincipal, String  situcaoCadastral) {
        var fornecedor = new Fornecedor(nome, cpf, endereco, sexo, telefone, email, cnpj, numeroInscricao, nomeFantasia, dataDeAbertura, porte, atividadeEconomicaPrincipal, situcaoCadastral);
        fornecedorDAO.cadastrarFornecedor(fornecedor);
    }

    @RequestMapping("/listarFornecedores")
    public List<Fornecedor> listarFornecedores() {
        return fornecedorDAO.listarFornecedores();
    }


    @PostMapping("/buscarFornecedor")
    public Fornecedor buscarFornecedor(int fornecedorId) {
        return fornecedorDAO.buscarFornecedor(fornecedorId);
    }


    @PostMapping("/excluirFornecedor")
    public void excluirFornecedor(int id) {
        fornecedorDAO.excluirFornecedor(id);
    }
}
