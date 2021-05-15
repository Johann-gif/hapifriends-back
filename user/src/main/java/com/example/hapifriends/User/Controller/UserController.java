package com.example.hapifriends.User.Controller;

import com.example.hapifriends.User.Entity.User;
import com.example.hapifriends.User.Repository.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + id));

        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable int id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + id));
        List<User> friends = user.getFriends();

        for (User friend : friends) {
            friend.getFriends().remove(user);
        }
        user.getFriends().removeAll(user.getFriends());
        userRepository.delete(user);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<User> updateUser (@PathVariable int id, @RequestParam(required = false) String pseudo, @RequestParam(required = false) String surname, @RequestParam(required = false) String firstname, @RequestParam(required = false) String email, @RequestParam(required = false) String number) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + id));

        if (pseudo != null) {
            user.setPseudo(pseudo);
        }
        if (surname != null) {
            user.setSurname(surname);
        }
        if (firstname != null) {
            user.setFirstname(firstname);
        }
        if (email != null) {
            user.setEmail(email);
        }
        if (number != null) {
            user.setMob_number(number);
        }
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/search/{name}")
    public @ResponseBody List<User> searchUser(@PathVariable String name) {
        List<User> usersBySurname = userRepository.findBySurnameStartsWithIgnoreCase(name);
        List<User> usersByFirstname = userRepository.findByFirstnameStartsWithIgnoreCase(name);

        return Stream.concat(usersBySurname.stream(), usersByFirstname.stream())
                .collect(Collectors.toList());
    }
}
