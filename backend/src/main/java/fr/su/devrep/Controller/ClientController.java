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
            CLIENTS
     */

    // Obtenir la liste de tous les clients
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clients.findAll();
    }

    // Obtenir un clients selon son id
    @GetMapping("/clients/{id}")
    public Client getClientById(@PathVariable("id") long id) throws ResourceNotFoundException{
        // TODO logging : LOG.info("Reading client with id " + id + " from database.");
        return clients.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "The client " + id + " is not in the database."));
    }

    // Obtenir un client selon son email
    @GetMapping("/clients/{email}")
    public Client getClientByEmail( @PathVariable("email") String email) {
        return clients.findByEmail(email);
    }

    // Obtenir un client selon son tel
    @GetMapping("/clients/{tel}")
    public Client getClientByTel(@PathVariable("tel") String tel) {
        return clients.findByTel(tel);
    }

    /**
        ACTIONS CLIENTS
     */

    // Dépot
    @PostMapping("/clients/{id}/depot/{amount}")
    public Client deposit(@PathVariable("id") Long id, @PathVariable("amount") Double amount) throws ResourceNotFoundException {
        Client c = clients.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "The client " + id + " is not in the database."));
        c.setBalance(c.getBalance()+amount);
        clients.save(c);
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
            AJOUTS CLIENTS
     */

    @PostMapping("/admin/add/{lastName}/{firstName}/{email}/{tel}/{balance}/{overdraft}/{cap}")
    @ResponseStatus(HttpStatus.CREATED)
    public long addNewClient(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName,
                             @PathVariable("email") String email,@PathVariable("tel") String tel,
                             @PathVariable("balance") double balance,@PathVariable("overdraft") double overdraft,
                             @PathVariable("cap") double cap) {
        return clients.save(new Client(firstName, lastName, email, tel, balance, overdraft, cap)).getId();

    }

    /** Add a client to the database with email and tel number */
    @PostMapping("/admin/add/{email}/{tel}/")
    @ResponseStatus(HttpStatus.CREATED)
    public long addNewClient(@PathVariable("email") String email, @PathVariable("tel") String tel) {
        return clients.save(new Client(email,tel)).getId();
    }
}
