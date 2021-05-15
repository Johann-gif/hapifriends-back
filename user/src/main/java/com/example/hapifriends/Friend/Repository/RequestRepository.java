package com.example.hapifriends.Friend.Repository;

import com.example.hapifriends.Friend.Entity.Request;
import com.example.hapifriends.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {
    Request findBySenderAndReceiver(User sender, User receiver);
}
