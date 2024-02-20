package nl.novi.backendeindopdracht.models;

import jakarta.persistence.*;

import java.util.List;

class Designer extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "designer")
    private List<Card> cards;


}
