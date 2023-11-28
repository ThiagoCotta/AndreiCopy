package dwbe.lojatenis.Controller;

import dwbe.lojatenis.DAO.CompraDAO;
import dwbe.lojatenis.Model.Compra;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/compra")
public class CompraController {
    private CompraDAO compraDAO;

    public CompraController() {
        this.compraDAO = new CompraDAO();
    }

    @PostMapping("/cadastrarCompra")
    public void cadastrarCompra(Compra compra) {
        compraDAO.cadastrarCompra(compra);
    }
}