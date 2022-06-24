package com.plunex.emilokan.modules.event;


import com.plunex.emilokan.exception.CustomException;
import com.plunex.emilokan.modules.event.dto.EventRequest;
import com.plunex.emilokan.modules.event.dto.EventResponse;
import com.plunex.emilokan.modules.user.User;
import com.plunex.emilokan.modules.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventService implements IEventService {
    private final EventRepository eventRepository;
    private final UserService userService;


    @Autowired
    public EventService(EventRepository eventRepository, UserService userService) {
        this.eventRepository = eventRepository;
        this.userService = userService;
    }

    @Override
    public Event getById(String eventId) {
        return eventRepository.findById(UUID.fromString(eventId)).orElseThrow(
                () -> new CustomException("Resource Not Found", HttpStatus.NOT_FOUND));
    }

    @Override
    public EventResponse create(EventRequest request) {

        User user = userService.getById(request.getOwnerId());
        Event newEvent = Event.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .user(user)
                .build();

        Event savedEvent = eventRepository.save(newEvent);

        return new EventResponse(
                savedEvent.getId().toString(),
                savedEvent.getTitle(),
                savedEvent.getDescription(),
                savedEvent.getUser().getUserName()
        );
    }
}

