package gr.aueb.cf.schoolapp2.repository;

import gr.aueb.cf.schoolapp2.core.enums.Role;
import gr.aueb.cf.schoolapp2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByRole(Role role);
    Optional<User> findByUsername(String username);
}
