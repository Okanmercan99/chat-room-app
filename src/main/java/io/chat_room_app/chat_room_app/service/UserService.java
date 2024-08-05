package io.chat_room_app.chat_room_app.service;

import io.chat_room_app.chat_room_app.model.User;
import io.chat_room_app.chat_room_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for user management.
 */
@Service
public class UserService {

    /**
     * User repo access for database management.
     */
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get all users
     */
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    /**
     * Find an user by userId
     * @param userId ID of the user
     * @return
     */
    public Optional<User> findUserByUserId(Long userId) {
        return this.userRepository.findById(userId);
    }

    /**
     * Save a personnel
     * @param user user to be saved
     * @return
     */
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    /**
     * Update an user
     * @param userId The ID of the user
     * @param updatedUser updated version of the user
     * @return
     */
    public Optional<User> updateUser(Long userId, User updatedUser) {
        Optional<User> oldUser = this.findUserByUserId(userId);
        if (oldUser.isPresent()) {
            User user = oldUser.get();
            user.setName(updatedUser.getName());
            user.setSurname(updatedUser.getSurname());
            User savedUser = this.userRepository.save(user);
            return Optional.of(savedUser);
        }else{
            return Optional.empty();
        }
    }

    /**
     * Delete an user
     * @param userId ID of the user to be deleted
     * @return
     */
    public boolean deleteUser(Long userId) {
        if(userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }else{
            return false;
        }
    }
}
