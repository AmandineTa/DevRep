package fr.su.devrep.Controller;

import fr.su.devrep.Exception.ResourceNotFoundException;
import fr.su.devrep.Exception.SeuilDuPlafondAttendException;
import fr.su.devrep.Exception.FondInsuffisantException;
import fr.su.devrep.models.Client;
import fr.su.devrep.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    Logger l = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientRepository clients;

    /*
     -  <S extends T> S save(S entity); Saves the given entity.
     -  Optional<T> findById(ID primaryKey); Returns the entity identified by the given ID.
     -  Iterable<T> findAll(); Returns all entities.
     -  long count(); Returns the number of entities.
     -  void delete(T entity); Deletes the given entity.
     -  boolean existsById(ID primaryKey); 	Indicates whether an entity with the given ID exists.
    */

    /**
            CLIENTS : OK
     */

    // Obtenir la liste de tous les clients : OK
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        l.info("print all clients.");
        return clients.findAll();
    }

    // Obtenir un clients selon son id : OK
    @GetMapping("/clients/{id}")
    public Client getClientById(@PathVariable("id") long id) throws ResourceNotFoundException{
        l.info("Print client " + id + ".");
        return clients.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Client " + id + " is not in the database."));
    }

    // Obtenir un client selon son email : OK
    @GetMapping("/clients/mail/{email}")
    public Client getClientByEmail( @PathVariable("email") String email) {
        l.info("Get client with his/her email : " + email + ", id : " + clients.findByEmail(email) + " .");
        return clients.findByEmail(email);
    }

    // Obtenir un client selon son tel : OK
    @GetMapping("/clients/tel/{tel}")
    public Client getClientByTel(@PathVariable("tel") String tel) {
        l.info("Get client with his/her tel : " + tel + ", id : " + clients.findByTel(tel) + " .");
        return clients.findByTel(tel);
    }

    /**
        ACTIONS CLIENTS : OK
     */

    // Dépot
    @PostMapping("/clients/{id}/depot/{amount}")
    public Client deposit(@PathVariable("id") Long id, @PathVariable("amount") Double amount) throws ResourceNotFoundException {
        Client c = clients.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Client " + id + " is not in the database."));
        c.setBalance(c.getBalance()+amount);
        clients.save(c);
        l.info("Client "+ id + "got "+ amount + "in his account.");
        return c;
    }
    // Retrait
    @PostMapping("/clients/{id}/retrait/{amount}")
    public Client withdraw(@PathVariable("id") Long id, @PathVariable("amount") Double amount) throws ResourceNotFoundException, FondInsuffisantException, SeuilDuPlafondAttendException {

        Client c = clients.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "The client " + id + " is not in the database."));
        if (amount <= c.getCap()) {
            if(amount < c.getBalance()+c.getOverdraft()){
                c.setBalance(c.getBalance()-amount);
                clients.save(c);
                l.info("Client "+ id + "retire "+ amount + "in his account.");
                return c;
            }else{
                throw new FondInsuffisantException("The amount that you're trying to withdraw is above your overdraft.");
            }
        }
        else {
            throw new SeuilDuPlafondAttendException("Is over for the gap");
        }
    }

    /**
            AJOUTS CLIENTS : OK
     */


    @PostMapping("/admin/add/{lastName}/{firstName}/{email}/{tel}/{mdp}/{balance}/{overdraft}/{cap}")
    public long addNewClient(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName,
                             @PathVariable("email") String email,@PathVariable("tel") String tel,
                             @PathVariable("mdp") String mdp,
                             @PathVariable("balance") double balance,@PathVariable("overdraft") double overdraft,
                             @PathVariable("cap") double cap) {

        Client c = new Client(firstName, lastName, email, tel, mdp, balance, overdraft, cap);
        l.info("Client create an account : "+ c.toString());
        return clients.save(c).getId();

    }


    @PostMapping("/admin/add/{email}/{tel}/{mdp}")

    public long addNewClient(@PathVariable("email") String email, @PathVariable("tel") String tel,  @PathVariable("mdp") String mdp) {
        Client c = new Client(email,tel,mdp);
        l.info("Client create an account : " + c.toString());
        return clients.save(c).getId();
    }

}
