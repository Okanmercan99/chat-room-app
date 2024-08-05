package io.chat_room_app.chat_room_app.controller;

import io.chat_room_app.chat_room_app.model.User;
import io.chat_room_app.chat_room_app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Class which stores the generated HTTP requests related to user operations.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * User repo access for database management.
     */
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users.
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return ResponseEntity.ok().body(users);
    }

    /**
     * Read user by userId
     * @param userId ID of the user.
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserByUserId(
            @PathVariable("userId") Long userId) {

        Optional<User> user = this.userService.findUserByUserId(userId);

        if(user.isPresent()) {
            return ResponseEntity.ok().body(user);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create an User.
     * @param user User model instance to be created.
     * @return
     */
    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try{
            User savedUser = this.userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Update User.
     * @param userId ID of the user that is to be updated.
     * @param updatedUser User model instance with updated details.
     * @return
     */
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        try{
            Optional<User> savedUser = this.userService.updateUser(userId, updatedUser);
            if(savedUser.isPresent()) {
                return ResponseEntity.ok().body(savedUser.get());
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Delete an User by User ID.
     * @param userId ID of the user that is to be deleted.
     * @return
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try{
            boolean isDeleted = this.userService.deleteUser(userId);
            if(isDeleted) {
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
