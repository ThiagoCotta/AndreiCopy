package dwbe.lojatenis.Controller;

import dwbe.lojatenis.DAO.PessoaDAO;
import dwbe.lojatenis.Model.Pessoa;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    private PessoaDAO pessoaDAO;

    public PessoaController() {
        this.pessoaDAO = new PessoaDAO();
    }

    @PostMapping("/cadastrarPessoa")
    public void cadastrarPessoa(Pessoa pessoa) {
        pessoaDAO.cadastrarPessoa(pessoa);
    }

    @RequestMapping("/listarPessoa")
    public List<Pessoa> listarPessoa() {
        return pessoaDAO.listarPessoa();
    }

    @PostMapping("/excluirPessoa")
    public void excluirPessoa(int id) {
        pessoaDAO.excluirPessoa(id);
    }
}