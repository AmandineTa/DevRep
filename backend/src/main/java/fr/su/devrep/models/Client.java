package fr.su.devrep.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "clients", uniqueConstraints = { @UniqueConstraint( columnNames = { "email", "tel"})})
public class Client implements Serializable {

    private @Id @GeneratedValue Long id;

    private String firstname;
    private String lastname;
    private String email;
    private String tel;
    // passeword ???

    //Ce qu’il y a dans le compte
    private Double balance;
    // Découvert autorisée
    private Double overdraft;
    // Maximum de transfert en un coups
    private Double cap;

    // default constructor
    public Client(){}

    // constructor
    public Client(String firstname, String lastname, String email, String tel, Double balance, Double overdraft, Double cap){
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.tel = tel;
        this.balance = balance;
        this.overdraft = overdraft;
        this.cap = cap;
    }

    public Client(String email, String tel){
        super();
        this.firstname = "";
        this.lastname = "";
        this.email = email;
        this.tel = tel;
        this.balance = 0.0;
        this.overdraft = 0.0;
        this.cap = 0.0;
    }

    // getters

    public Long getId() { return id; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getEmail() { return email; }
    public String getTel() { return tel; }
    public Double getBalance() { return balance; }
    public Double getOverdraft() { return overdraft; }
    public Double getCap() { return cap; }

    // setters

    public void setId(Long id) { this.id = id; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setEmail(String email) { this.email = email; }
    public void setTel(String tel) { this.tel = tel; }
    public void setBalance(Double balance) { this.balance = balance; }
    public void setOverdraft(Double overdraft) { this.overdraft = overdraft; }
    public void setCap(Double cap) { this.cap = cap; }

    // methods

    // id, email and tel is not null for equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!id.equals(client.id)) return false;
        if (!Objects.equals(firstname, client.firstname)) return false;
        if (!Objects.equals(lastname, client.lastname)) return false;
        if (!email.equals(client.email)) return false;
        if (!tel.equals(client.tel)) return false;
        if (!Objects.equals(balance, client.balance)) return false;
        if (!Objects.equals(overdraft, client.overdraft)) return false;
        return Objects.equals(cap, client.cap);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", balance=" + balance +
                ", overdraft=" + overdraft +
                ", cap=" + cap +
                '}';
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + email.hashCode();
        result = 31 * result + tel.hashCode();
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (overdraft != null ? overdraft.hashCode() : 0);
        result = 31 * result + (cap != null ? cap.hashCode() : 0);
        return result;


    }
}
