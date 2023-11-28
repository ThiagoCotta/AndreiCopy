package dwbe.lojatenis.Controller;

import dwbe.lojatenis.DAO.EstoqueDAO;
import dwbe.lojatenis.Model.Estoque;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    private EstoqueDAO estoqueDAO;

    public EstoqueController() {
        this.estoqueDAO = new EstoqueDAO();
    }

    public void cadastrarEstoque(Estoque estoque) {
        estoqueDAO.cadastrarEstoque(estoque);
    }

    @PostMapping("/alterarEstoque")
    public void alterarEstoque(Estoque estoque) {
        estoqueDAO.alterarEstoque(estoque);
    }

    @RequestMapping("/listarEstoque")
    public List<Estoque> listarEstoque() {
        return estoqueDAO.listarEstoque();
    }

    @PostMapping("/buscarEstoque")
    public Estoque buscarEstoque(int id) {
        return estoqueDAO.buscarEstoque(id);
    }

    @PostMapping("/excluirEstoque")
    public void excluirEstoque(int id) {
        estoqueDAO.excluirEstoque(id);
    }
}
