package br.com.haircutappoitment.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.haircutappoitment.domain.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity,Long>{
    
    Page<UserEntity> findAllBy(Pageable pageable);

    UserDetails findByEmail(String email);

}
