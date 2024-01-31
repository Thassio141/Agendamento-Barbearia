package br.com.haircutappoitment.models.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.com.haircutappoitment.models.enums.ActivityStatus;
import br.com.haircutappoitment.models.enums.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity  {

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

    @Column(name = "user_role")
    private UserRole userRole;

    @Column(name = "user_status")
    private ActivityStatus userStatus;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user", cascade = CascadeType.ALL)
    private Set<AppointmentEntity> appointment = new HashSet<>();
}
