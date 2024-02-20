package nl.novi.backendeindopdracht.models;

import jakarta.persistence.*;

@Entity
@Table( name = "cards")
public class Card {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer amountOfDownloads;
    private String designer;

}
