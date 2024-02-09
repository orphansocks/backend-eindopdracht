package nl.novi.backendeindopdracht;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//allrelatives
//singlerelative?
//relative/:id
//newrelative
//login
//register


// VOOR ALLE REQUESTS / ENDPOINTS
@RestController
public class HelloController {
    private String name;


    @GetMapping("/hello")
    public String sayHello(@RequestParam(required = false) String name) {
        if (name == null || name.isEmpty()) {
            return "Hello Stranger";
        } else {
            return "Hello FrontEnd " + name;
        }
    }

    @PostMapping("/newrelative")
    public void saveRelative(@RequestParam String name) {
        this.name = name;
    }

    @GetMapping("/singlerelative")
    public String retrieveRelative() {
        if (this.name == null || this.name.isEmpty()) {
            return "no relative found";
        }
        else {
            return this.name;
        }

    }
}
