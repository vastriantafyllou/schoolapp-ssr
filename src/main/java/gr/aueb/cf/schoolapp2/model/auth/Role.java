package gr.aueb.cf.schoolapp2.model.auth;

import gr.aueb.cf.schoolapp2.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "roles_capabilities",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "capability_id")
    )
    private Set<Capability> capabilities = new HashSet<>();

    // Helper Methods
    public void addCapability(Capability capability) {
        if (capabilities == null) capabilities = new HashSet<>();
        this.capabilities.add(capability);
        capability.getRoles().add(this);
    }

    public void removeCapability(Capability capability) {
        if (capabilities == null) return;
        this.capabilities.remove(capability);
        capability.getRoles().remove(this);
    }

    public void addUser(User user) {
        if (users == null) users = new HashSet<>();
        this.users.add(user);
        user.setRole(this);
    }

    public void removeUser(User user) {
        if (users == null) return;
        this.users.remove(user);
        user.setRole(null);
    }

    //Bulk insert
    public void addUsers(Collection<User> users) {
        users.forEach(this::addUser);
    }
}
