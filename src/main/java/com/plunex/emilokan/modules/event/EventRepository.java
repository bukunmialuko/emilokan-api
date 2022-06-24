package com.plunex.emilokan.modules.event;


import com.plunex.emilokan.modules.event.enums.EEventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;


public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query(value = "SELECT * FROM event where manager_id = :uuid", nativeQuery = true)
    List<Event> findByUserId(UUID uuid);

    @Query(value = "SELECT * FROM event where manager_id = :uuid AND status =:status", nativeQuery = true)
    List<Event> findByUseIdAndStatus(UUID uuid, String status);

}

