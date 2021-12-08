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
     * Get all clients from the repository
     *
     * @return list of Clients
     */
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clients.findAll();
    }

    /** Get a Client by his account id */
    @GetMapping("/clients/{id}")
    public Client getClientById(@PathVariable("id") long id) throws ResourceNotFoundException{
        // TODO logging : LOG.info("Reading client with id " + id + " from database.");
        return clients.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "The client with the id " + id + " couldn't be found in the database."));
    }

    /** Get a Client by his email*/
    @GetMapping("/client/{email}")
    public Client getClientByEmail( @PathVariable("email") String email) {
        return clients.findByEmail(email);
    }
    /** Get a Client by his tel*/
    @GetMapping("/client/{tel}")
    public Client getClientByTel(@PathVariable("tel") String tel) {
        return clients.findByTel(tel);
    }

    /** Add a client to the database with all parameter lastName, firstName, email, tel, balance, overdraft and cap*/
    @ResponseBody
    @RequestMapping(path = "/secured/create/{lastName}/{firstName}/{email}/{tel}/{balance}/{overdraft}/{cap}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public long addNewClient(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName,
                             @PathVariable("email") String email,@PathVariable("tel") String tel,
                             @PathVariable("balance") double balance,@PathVariable("overdraft") double overdraft,
                             @PathVariable("cap") double cap) {
        Client savedClient = clients.save(new Client(firstName, lastName, email, tel, balance, overdraft, cap));

        // TODO logging : LOG.info(savedClient.toString() + " successfully saved into
        // DB");

        return savedClient.getId();
    }

    /** Add a client to the database with email and tel number */
    @ResponseBody
    @RequestMapping(path = "/secured/create/{email}/{tel}/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public long addNewClient(@PathVariable("email") String email, @PathVariable("tel") String tel) {
        Client savedClient = clients.save(new Client(email,tel));

        // TODO logging : LOG.info(savedClient.toString() + " successfully saved into
        // DB");

        return savedClient.getId();
    }

    /** Withdraw */
    @RequestMapping(path = "/Withdraw/{id}/{amount}")
    public Client withdraw(@PathVariable("id") Long id, @PathVariable("amount") Double amount) throws ResourceNotFoundException, FondInsuffisantException, SeuilDuPlafondAttendException {

        Client c = clients.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "The client with the id " + id + " couldn't be found in the database."));

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

    /** Deposit */
    @ResponseBody
    @RequestMapping(path = "/Deposit/{id}/{amount}")
    public Client deposit(@PathVariable("id") Long id, @PathVariable("amount") Double amount) throws ResourceNotFoundException {
        Client c = clients.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "The client with the id " + id + " couldn't be found in the database."));
        c.setBalance(c.getBalance()+amount);
        clients.save(c);
        return c;
    }
}
