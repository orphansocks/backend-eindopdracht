package nl.novi.backendeindopdracht.config;

import nl.novi.backendeindopdracht.filter.JwtRequestFilter;
import nl.novi.backendeindopdracht.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    public final CustomUserDetailsService customUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    // Authenticatie met userDetailsService en passwordEncoder
    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
        var auth = new DaoAuthenticationProvider();

        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(customUserDetailsService);

        return new ProviderManager(auth);
    }

// Authorisatie met jwt
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                        .requestMatchers("/authenticate").authenticated()

                        .requestMatchers(HttpMethod.POST,"/users").permitAll()
                                .requestMatchers(HttpMethod.POST,"/users/designers").permitAll()
                        .requestMatchers(HttpMethod.GET,"/users/{username}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/users/{username}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/users/{username}").hasAnyRole("ADMIN", "USER")


                        .requestMatchers(HttpMethod.POST, "/relatives").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET,"/relatives").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET,"/relatives/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET,"/relatives/name/{name}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET,"/relatives/relation/{relation}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT,"/relatives/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/relatives/{id}").hasAnyRole("ADMIN", "USER")

                        .requestMatchers(HttpMethod.POST, "/groups").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET,"/groups").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET,"/groups/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT,"/groups/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE,"/groups/{id}").hasAnyRole("ADMIN", "USER")

                        .requestMatchers(HttpMethod.POST, "/cards").hasAnyRole("ADMIN", "DESIGNER")
                        .requestMatchers(HttpMethod.GET,"/cards").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/cards").hasAnyRole("ADMIN", "DESIGNER")

                        .requestMatchers(HttpMethod.POST, "/designers").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/designers/{id}").hasAnyRole("ADMIN", "DESIGNER")
                        .requestMatchers(HttpMethod.PUT, "/designers/{id}").hasAnyRole("ADMIN")

                        .anyRequest().denyAll()

                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}
