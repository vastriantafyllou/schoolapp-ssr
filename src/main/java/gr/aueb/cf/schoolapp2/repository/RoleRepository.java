package gr.aueb.cf.schoolapp2.repository;

import gr.aueb.cf.schoolapp2.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long > {
}
