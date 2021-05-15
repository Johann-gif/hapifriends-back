package com.example.hapifriends.Post.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.hapifriends.User.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String text;
    @Column(columnDefinition = "boolean default false")
    private Boolean shared;

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("friends")
    private User author;
}
