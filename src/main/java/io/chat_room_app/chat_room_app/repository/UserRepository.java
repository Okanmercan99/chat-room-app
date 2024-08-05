package io.chat_room_app.chat_room_app.repository;


import io.chat_room_app.chat_room_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User repository for database management.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
