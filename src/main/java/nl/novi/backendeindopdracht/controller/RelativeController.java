package nl.novi.backendeindopdracht.controller;

import nl.novi.backendeindopdracht.model.Relative;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/relatives")
public class RelativeController {

    private ArrayList<Relative> relatives = new ArrayList<>();

    @GetMapping
    public ResponseEntity<Object> getAllRelatives() {
        return new ResponseEntity<>(this.relatives, HttpStatus.OK);

    }




}
