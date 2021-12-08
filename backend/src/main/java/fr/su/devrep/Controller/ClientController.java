package fr.su.devrep.Controller;

import fr.su.devrep.models.Client;
import fr.su.devrep.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository clients;
    // c'est quoi autowire
    public ClientController(ClientRepository clients) {
        this.clients = clients;
    }

    // get envoie qqch
    // front envoir requete post au controleur et lui renvoie qqch
    // post -> set


}
