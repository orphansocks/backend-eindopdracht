package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.dtos.user.UserDto;
import nl.novi.backendeindopdracht.dtos.user.UserInputDto;
import nl.novi.backendeindopdracht.exceptions.RecordNotFoundException;
import nl.novi.backendeindopdracht.exceptions.UsernameAlreadyExists;
import nl.novi.backendeindopdracht.exceptions.UsernameNotFoundException;
import nl.novi.backendeindopdracht.models.Role;
import nl.novi.backendeindopdracht.models.User;
import nl.novi.backendeindopdracht.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder1) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder1;
    }


    public String createUser(UserInputDto userInputDto) {

        User user = transferToEntity(userInputDto);

        String username = user.getUsername();

        if (!userRepository.existsById(username)) {

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            userRepository.save(user);
            return user.getUsername();
        } else {
            throw new UsernameAlreadyExists(username);
        }
    }

    public String createDesigner(UserInputDto userInputDto) {

        User user = transferToEntity(userInputDto);

        String username = user.getUsername();

        if (!userRepository.existsById(username)) {

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            userRepository.save(user);
            return user.getUsername();
        } else {
            throw new UsernameAlreadyExists(username);
        }
    }

    public String createAdmin(UserInputDto userInputDto) {

        User user = transferToEntity(userInputDto);

        String username = user.getUsername();

        if (!userRepository.existsById(username)) {

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            userRepository.save(user);
            return user.getUsername();
        } else {
            throw new UsernameAlreadyExists(username);
        }
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public void updateUser(String username, UserDto newUser) {

        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        User user = userRepository.findById(username).get();
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }

    public UserDto getUser(String username) {

        UserDto dto = new UserDto();

        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()){
            dto = transferToDto(user.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public List<UserDto> getUsers() {

        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();

        for (User user : list) {
            collection.add(transferToDto(user));
        }
        return collection;
    }


    // DE TRANSFERS

    public static UserDto transferToDto(User user){

        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.email = user.getEmail();
        dto.roles = user.getRoles();

        return dto;
    }

    public User transferToEntity(UserInputDto dto) {

        User user = new User();

        user.setUsername(dto.username);
        user.setPassword(dto.password);
        user.setEmail(dto.email);
        user.setEnabled(user.isEnabled());


        return user;
    }


    // ROLLEN

    public void addRole(String username, String role) {

        Optional<User> userOptional = userRepository.findById(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        User user = userOptional.get();
//        user.addRole(role);
        user.addRole(new Role(username, role));
        userRepository.save(user);
    }


    public Set<Role> getRoles(String username) {

        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = transferToDto(user);
        return userDto.getRoles();
    }

    public void removeRole(String username, String role) {
        if (!userRepository.existsById(username)) throw new org.springframework.security.core.userdetails.UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Role roleToRemove = user.getRoles().stream().filter((a) -> a.getRole().equalsIgnoreCase(role)).findAny().get();
        user.removeRole(roleToRemove);
        userRepository.save(user);
    }








}
