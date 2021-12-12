package mialsy.project4.database;

import mialsy.project4.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * The interface User repository, for accessing User in DB
 *
 * @author Chuxi Wang
 */
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Find the user by email.
     *
     * @param email the email
     * @return optional user matching the email
     */
    Optional<User> findUserByEmail(String email);
}