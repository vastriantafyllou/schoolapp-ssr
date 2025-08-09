package gr.aueb.cf.schoolapp2.model.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "capabilities")
public class Capability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "capabilities", fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    // Helper Methods
    public void addRole(Role role) {
        if (roles == null) roles = new HashSet<>();
        this.roles.add(role);
        role.getCapabilities().add(this);
    }

    public void removeRole(Role role) {
        if (roles == null) return;
        this.roles.remove(role);
        role.getCapabilities().remove(this);
    }

}
