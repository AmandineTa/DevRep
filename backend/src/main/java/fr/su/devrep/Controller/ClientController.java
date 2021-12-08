package fr.su.devrep.Controller;

import fr.su.devrep.Exception.ResourceNotFoundException;
import fr.su.devrep.Exception.SeuilDuPlafondAttendException;
import fr.su.devrep.Exception.FondInsuffisantException;
import fr.su.devrep.models.Client;
import fr.su.devrep.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
