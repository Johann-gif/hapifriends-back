package com.example.hapifriends.Friend.Controller;

import com.example.hapifriends.Friend.Entity.Request;
import com.example.hapifriends.Friend.Repository.RequestRepository;
import com.example.hapifriends.User.Entity.User;
import com.example.hapifriends.User.Repository.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/friends")
public class FriendController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RequestRepository requestRepository;


    @GetMapping("/find/{owner_id}")
    public List<User> getFriends(@PathVariable int owner_id) throws ResourceNotFoundException {
        User owner = userRepository.findById(owner_id).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + owner_id));

        return owner.getFriends();
    }

   @GetMapping("/search/{owner_id}/{name}")
    public @ResponseBody List<User> getFriendsByName(@PathVariable int owner_id, @PathVariable String name) throws ResourceNotFoundException {
       User owner = userRepository.findById(owner_id).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + owner_id));
       List<User> result = new ArrayList<>();
       List<User> friends = owner.getFriends();

       for (User friend : friends) {
           if (friend.getPseudo().contains(name)) {
               result.add(friend);
           }
       }
       return result;
    }

    @GetMapping("/show-requests")
    public List<Request> showRequests() {
        return requestRepository.findAll();
    }

    @PostMapping("/request")
    public String createRequest(@RequestParam int owner_id, @RequestParam int to_add_id) throws ResourceNotFoundException {
        User owner = userRepository.findById(owner_id).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + owner_id));
        User to_add = userRepository.findById(to_add_id).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + to_add_id));

        Request request = new Request();
        request.setSender(owner);
        request.setReceiver(to_add);
        requestRepository.save(request);
        return "Demande bien envoyée à " + to_add.getPseudo() + ".";
    }

    @PostMapping("/reply")
    public String reply(@RequestParam int receiver_id, @RequestParam int sender_id, boolean response) throws ResourceNotFoundException {
        User sender = userRepository.findById(sender_id).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + sender_id));
        User receiver = userRepository.findById(receiver_id).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + receiver_id));
        Request request = requestRepository.findBySenderAndReceiver(sender, receiver);

        if (request != null) {
            // L'invitation est acceptée
            if (response) {
                sender.addFriend(receiver);
                receiver.addFriend(sender);
                requestRepository.delete(request);
                userRepository.save(sender);
                userRepository.save(receiver);
                return "Demande acceptée.";
            }
            // L'invitation est refusée
            requestRepository.delete(request);
            return "Demande refusée.";
        }
        // L'invitation n'existe pas
        return "Demande inexistante.";
    }

    @DeleteMapping("/{owner_id}/{to_delete_id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable int owner_id, @PathVariable int to_delete_id) throws ResourceNotFoundException {
        User owner = userRepository.findById(owner_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + owner_id));
        User to_delete = userRepository.findById(to_delete_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + to_delete_id));

        if (!owner.getFriends().remove(to_delete)) {
            return ResponseEntity.notFound().build();
        }
        to_delete.getFriends().remove(owner);
        userRepository.save(owner);
        userRepository.save(to_delete);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
