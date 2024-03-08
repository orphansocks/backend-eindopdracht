package nl.novi.backendeindopdracht.controllers;

import nl.novi.backendeindopdracht.dtos.auth.AuthInputDto;
import nl.novi.backendeindopdracht.dtos.auth.AuthResponseDto;
import nl.novi.backendeindopdracht.services.CustomUserDetailsService;
import nl.novi.backendeindopdracht.utils.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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

    @PostMapping(value = "/authenticate")
    public ResponseEntity<Object> logIn(@RequestBody AuthInputDto authInputDto) {

        String username = authInputDto.getUsername();
        String password = authInputDto.getPassword();

        UsernamePasswordAuthenticationToken upAuthToken =
                new UsernamePasswordAuthenticationToken(username, password);

        try {
            authenticationManager.authenticate(upAuthToken);

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            String jwtToken = jwtUtil.generateToken(userDetails);

            String tokenWithBearer = "Bearer " + jwtToken;

//            AuthResponseDto authResponseDto = new AuthResponseDto(tokenWithBearer);

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, tokenWithBearer)
                    .body("Token generated");
        }
        catch (AuthenticationException authenticationException) {
            return new ResponseEntity<>(authenticationException.getMessage(), HttpStatus.UNAUTHORIZED);
        }

    }

//    @GetMapping(value = "/authenticated")
//    public ResponseEntity<Object> authenticated(Authentication authentication, Principal principal) {
//        return ResponseEntity.ok().body(principal);
//    }





}
