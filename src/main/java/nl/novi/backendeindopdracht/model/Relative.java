package nl.novi.backendeindopdracht.model;

import java.time.LocalDate;

public class Relative {
    private String firstName;
    private String lastName;
    private String nickName;
    private LocalDate dob;
    private String socialStatus;
    private Integer amountOfKids;
    private String namesOfKids;
    private String misc;
    private String relation;




    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getSocialStatus() {
        return socialStatus;
    }

    public void setSocialStatus(String socialStatus) {
        this.socialStatus = socialStatus;
    }

    public Integer getAmountOfKids() {
        return amountOfKids;
    }

    public void setAmountOfKids(Integer amountOfKids) {
        this.amountOfKids = amountOfKids;
    }

    public String getNamesOfKids() {
        return namesOfKids;
    }

    public void setNamesOfKids(String namesOfKids) {
        this.namesOfKids = namesOfKids;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }





}
