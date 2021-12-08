package fr.su.devrep.Controller;

import fr.su.devrep.repository.ClientRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    private ClientRepository clients;

    public ClientController(ClientRepository clients) {
        this.clients = clients;
    }
    // test
    // get envoie qqch
    // front envoir requete post au controleur et lui renvoie qqch
    // post -> set


}
