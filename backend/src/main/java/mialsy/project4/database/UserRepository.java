package mialsy.project4.database;

import mialsy.project4.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByGithubId(Long githubId);
}