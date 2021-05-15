package com.example.hapifriends.User.Repository;

import com.example.hapifriends.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findBySurnameStartsWithIgnoreCase(String surname);
    List<User> findByFirstnameStartsWithIgnoreCase(String firstname);
    User findByPseudo(String pseudo);
    @Query(value = "SELECT * FROM user WHERE id = (SELECT f.friend_id FROM friends f WHERE f.user_id = :id1 and f.friend_id = :id2)",
            nativeQuery = true)
    User getFriendIfExists(
            @Param("id1") Integer id1,
            @Param("id2") Integer id2
    );
}
