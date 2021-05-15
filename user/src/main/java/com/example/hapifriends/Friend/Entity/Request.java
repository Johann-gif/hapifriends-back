package com.example.hapifriends.Friend.Entity;

import com.example.hapifriends.User.Entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Request {
    @Id
    private int id;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
}
