package com.example.hapifriends.Post.Repository;


import com.example.hapifriends.Post.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByTitleIsContainingIgnoreCase(String title);
    List<Post> findByTextIsContainingIgnoreCase(String text);
}
