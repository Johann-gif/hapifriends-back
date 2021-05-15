package com.example.hapifriends.Post.Controller;

import com.example.hapifriends.Post.Entity.Post;
import com.example.hapifriends.Post.Repository.PostRepository;
import com.example.hapifriends.User.Entity.User;
import com.example.hapifriends.User.Repository.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable int id) throws ResourceNotFoundException {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found :: " + id));

        return ResponseEntity.ok().body(post);
    }

    @GetMapping("/owner/{id}")
    public List<Post> getOwnerPost(@PathVariable int id) throws ResourceNotFoundException
    {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + id));
        return postRepository.findAllByAuthor(user);
    }

    @PostMapping("/add")
    public ResponseEntity<Post> addPost(@RequestParam String title, @RequestParam String text, @RequestParam Boolean shared, @RequestParam int user_id)  throws ResourceNotFoundException {
        Post post = new Post();

        post.setTitle(title);
        post.setText(text);
        post.setShared(shared);
        post.setAuthor(userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + user_id)));
        postRepository.save(post);
        return ResponseEntity.ok().body(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable int id) throws ResourceNotFoundException {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found :: " + id));
        postRepository.delete(post);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable int id, @RequestParam(required = false) String title, @RequestParam(required = false) String text, @RequestParam(required = false) Boolean shared) throws ResourceNotFoundException {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found :: " + id));

        if (title != null) {
            post.setTitle(title);
        }
        if (text != null) {
            post.setText(text);
        }
        if (shared != null) {
            post.setShared(shared);
        }
        postRepository.save(post);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping("/search/{name}")
    public @ResponseBody List<Post> searchPostByName(@PathVariable String name) {
        return postRepository.findByTitleIsContainingIgnoreCase(name);
    }

    @GetMapping("/searchText/{text}")
    public @ResponseBody List<Post> searchPostByText(@PathVariable String text) {
        return postRepository.findByTextIsContainingIgnoreCase(text);
    }

}
