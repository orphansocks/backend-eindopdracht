package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.dtos.UserDto;
import nl.novi.backendeindopdracht.exceptions.RecordNotFoundException;
import nl.novi.backendeindopdracht.exceptions.UsernameNotFoundException;
import nl.novi.backendeindopdracht.models.Role;
import nl.novi.backendeindopdracht.models.User;
import nl.novi.backendeindopdracht.repositories.UserRepository;
import nl.novi.backendeindopdracht.utils.RandomStringGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(UserDto userDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);
        User newUser = userRepository.save(transferToEntity(userDto));
        return newUser.getUsername();
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
        }else {
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



    public static UserDto transferToDto(User user){

        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.apikey = user.getApikey();
        dto.email = user.getEmail();
        dto.roles = user.getRoles();

        return dto;
    }

    public User transferToEntity(UserDto userDto) {

        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.getEnabled());
        user.setApikey(userDto.getApikey());
        user.setEmail(userDto.getEmail());

        return user;
    }

    public void addRole(String username, String role) {

        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();

        user.addRole(new Role(username, role));
        userRepository.save(user);
    }


    public Set<Role> getRoles(String username) {

        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = transferToDto(user);
        return userDto.getRoles();
    }






}
