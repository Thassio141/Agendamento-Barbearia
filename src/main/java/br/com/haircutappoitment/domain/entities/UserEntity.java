package br.com.haircutappoitment.domain.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.haircutappoitment.domain.enums.ActivityStatus;
import br.com.haircutappoitment.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_role")
    private UserRole userRole;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_status")
    private ActivityStatus userStatus;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user", cascade = CascadeType.ALL)
    private Set<AppointmentEntity> appointment = new HashSet<>();

    public UserEntity(String name, String email, String password, UserRole userRole, ActivityStatus userStatus) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        switch (this.userRole) {

            case ADMIN -> {
                return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ROLE_USER"),
                        new SimpleGrantedAuthority("ROLE_BARBER"),
                        new SimpleGrantedAuthority("ROLE_MANAGER"));
            }

            case MANAGER -> {
                return List.of(new SimpleGrantedAuthority("ROLE_MANAGER"),
                        new SimpleGrantedAuthority("ROLE_BARBER"),
                        new SimpleGrantedAuthority("ROLE_USER"));
            }

            case BARBER -> {
                return List.of(new SimpleGrantedAuthority("ROLE_BARBER"));
            }

            default -> {
                return List.of(new SimpleGrantedAuthority("ROLE_USER"));
            }
        }
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
