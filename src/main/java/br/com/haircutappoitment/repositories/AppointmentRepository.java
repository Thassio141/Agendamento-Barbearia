package br.com.haircutappoitment.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.haircutappoitment.domain.entities.AppointmentEntity;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity,Long>{
    
    Page<AppointmentEntity> findAllBy(Pageable pageable);

}
