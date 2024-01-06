package com.br.scheduling.repositories;

import com.br.scheduling.models.Schedule;
import com.br.scheduling.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    Page<Schedule> findPageable(Pageable pageable);

}
