package nl.novi.backendeindopdracht.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="designer_profiles")
public class DesignerProfile {

    @Id
    private String username;
    private String company;
    private String lastname;
    private String firstname;
    private String address;
    private String bankAccount;
    private Long amountOfDownloads;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Long getAmountOfDownloads() {
        return amountOfDownloads;
    }

    public void setAmountOfDownloads(Long amountOfDownloads) {
        this.amountOfDownloads = amountOfDownloads;
    }
}
