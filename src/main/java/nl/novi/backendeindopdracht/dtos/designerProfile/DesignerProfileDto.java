package nl.novi.backendeindopdracht.dtos.designerProfile;

import nl.novi.backendeindopdracht.dtos.card.CardDto;
import nl.novi.backendeindopdracht.models.Card;

import java.util.Set;

public class DesignerProfileDto {


    public Long id;
    public String company;
    public String lastname;
    public String firstname;
    public String address;
    public String url;
    public String phone;
    public String bankAccount;
    public Set<CardDto> cardDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Set<CardDto> getCardDto() {
        return cardDto;
    }

    public void setCardDto(Set<CardDto> cardDto) {
        this.cardDto = cardDto;
    }
}
