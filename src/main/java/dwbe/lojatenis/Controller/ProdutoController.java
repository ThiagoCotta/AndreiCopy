package dwbe.lojatenis.Controller;

import dwbe.lojatenis.DAO.ProdutoDAO;
import dwbe.lojatenis.Model.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private ProdutoDAO produtoDAO;

    public ProdutoController() {
        this.produtoDAO = new ProdutoDAO();
    }

    public void cadastrarProduto(Produto produto) {
        produtoDAO.cadastrarProduto(produto);
    }

    @RequestMapping("/listarProdutos")
    public List<Produto> listarProdutos() {
        return produtoDAO.listarProdutos();
    }

    @PostMapping("/listarProdutosPorFornecedor")
    public List<Produto> listarProdutosPorFornecedor(int idFornecedor) {
        return produtoDAO.listarProdutosPorFornecedor(idFornecedor);
    }

    @PostMapping("/buscarProduto")
    public Produto buscarProduto(int id) {
        return produtoDAO.buscarProduto(id);
    }

    @PostMapping("/excluirProduto")
    public void excluirProduto(int id) {
        produtoDAO.excluirProduto(id);
    }
}