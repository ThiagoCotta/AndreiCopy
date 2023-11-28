package dwbe.lojatenis.Controller;

import dwbe.lojatenis.DAO.VendaDAO;
import dwbe.lojatenis.Model.Venda;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {
    private VendaDAO vendaDAO;

    public VendaController() {
        this.vendaDAO = new VendaDAO();
    }

    @PostMapping("/cadastrarVenda")
    public void cadastrarVenda(Venda venda) {
        vendaDAO.cadastrarVenda(venda);
    }
}