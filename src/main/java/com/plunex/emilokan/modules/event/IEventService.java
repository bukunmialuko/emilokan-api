package com.plunex.emilokan.modules.event;


import com.plunex.emilokan.modules.event.dto.EventRequest;
import com.plunex.emilokan.modules.event.dto.EventResponse;
import com.plunex.emilokan.modules.event.enums.EEventStatus;
import com.plunex.emilokan.modules.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface IEventService {

    Event getById(String eventId);

    EventResponse create(EventRequest request);

    EventResponse update(String eventId, EventRequest request);

    EventResponse changeEventStatus(String eventId, EEventStatus newStatus);

    List<Event> getMyEvents(String userId);

    List<Event> getMyEventsWithStatus(String userId, String status);

}

