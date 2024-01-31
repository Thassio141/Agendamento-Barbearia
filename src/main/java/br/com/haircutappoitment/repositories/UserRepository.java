package br.com.haircutappoitment.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.haircutappoitment.models.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>{
    
    Page<UserEntity> findAllBy(Pageable pageable);

}
