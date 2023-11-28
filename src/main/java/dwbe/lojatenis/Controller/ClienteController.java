package dwbe.lojatenis.Controller;

import dwbe.lojatenis.DAO.ClienteDAO;
import dwbe.lojatenis.Model.Cliente;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    @PostMapping("/cadastrarCliente")
    public void cadastrarCliente(Cliente cliente) {
        clienteDAO.cadastrarCliente(cliente);
    }

    @RequestMapping("/listarClientes")
    public List<Cliente> listarClientes() {
        return clienteDAO.listarClientes();
    }

    @PostMapping("/buscarCliente")
    public Cliente buscarCliente(int id) {
        return clienteDAO.buscarCliente(id);
    }

    @PostMapping("/excluirCliente")
    public void excluirCliente(int id) {
        clienteDAO.excluirCliente(id);
    }
}
