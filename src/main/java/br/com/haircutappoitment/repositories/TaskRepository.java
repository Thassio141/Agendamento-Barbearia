package br.com.haircutappoitment.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.haircutappoitment.models.entities.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity,Long>{

    Page<TaskEntity> findAllBy(Pageable pageable);

}
