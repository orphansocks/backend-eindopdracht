package nl.novi.backendeindopdracht.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "relatives")
public class Relative {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String nickName;
    private LocalDate dob;
    private String socialStatus;
    private Boolean hasKids;
    private Integer amountOfKids;
    private String namesOfKids;
    private String misc;
    private String relation;

    @ManyToMany(mappedBy = "groupRelatives")
    private Set<Group> groups;

// DE SETTER VAN DE ID ER UIT HALEN
    // GETTERS AND SETTERS VIA LOMBOK??
    // ISBIRTHDAY
    // RELATION ALS ENUM?
    // SOCIALSTATUS ALSO AS ENUM?
    // GEEN GENDER WANT HEE 2024 :)
    // NAMES OF KIDS OP DEZE MANIER KOMMAGESCHEIDEN??
    // AGE STAAT ER NIET TUSSEN WANT DIE WORDT BEREKENT IN DE FRONTEND
    // WIL IK OOK LEEFTIJDEN VAN KIDS >> JA
    // SETAGE VAN DE KIDS WANNEER & HOE IN HET PROCES??


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


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


    public Boolean getHasKids() {
        return hasKids;
    }

    public void setHasKids(Boolean hasKids) {
        this.hasKids = hasKids;
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

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
