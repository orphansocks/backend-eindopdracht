package nl.novi.backendeindopdracht.controllers;

import nl.novi.backendeindopdracht.dtos.auth.AuthInputDto;
import nl.novi.backendeindopdracht.dtos.auth.AuthResponseDto;
import nl.novi.backendeindopdracht.services.CustomUserDetailsService;
import nl.novi.backendeindopdracht.utils.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    // dit is de GET voor het terug geven van de JWT TOKEN Wanneer de gebruiker de juiste AuthInput opgeeft
    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> login(@RequestBody AuthInputDto authInputDto) throws Exception {

        String username = authInputDto.getUsername();
        String password = authInputDto.getPassword();

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(username, password);

        try {
            authenticationManager.authenticate(authToken);

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthResponseDto(jwt));

        }
        catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }


    }

    // Dit is de GET voor het terug geven van de basisgegevens van de user
    @GetMapping(value = "/authenticated")
    public ResponseEntity<Object> authenticated(Authentication authentication, Principal principal) {
        return ResponseEntity.ok().body(principal);
    }



}
