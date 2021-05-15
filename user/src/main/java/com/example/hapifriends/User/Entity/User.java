package com.example.hapifriends.User.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String pseudo;
    private String password;
    private String surname;
    private String firstname;
    private String email;
    private String mob_number;

    @ManyToMany()
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    @JsonIgnoreProperties("friends")
    private List<User> friends;

    public void addFriend(User user) {
        friends.add(user);
    }
}
