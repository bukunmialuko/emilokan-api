package com.plunex.emilokan.modules.event;


import com.plunex.emilokan.exception.CustomException;
import com.plunex.emilokan.modules.event.dto.EventRequest;
import com.plunex.emilokan.modules.event.dto.EventResponse;
import com.plunex.emilokan.modules.event.enums.EEventStatus;
import com.plunex.emilokan.modules.user.User;
import com.plunex.emilokan.modules.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
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
//                .status(EEventStatus.CREATED)
                .build();

        Event savedEvent = eventRepository.save(newEvent);

        return new EventResponse(
                savedEvent.getId().toString(),
                savedEvent.getTitle(),
                savedEvent.getDescription(),
                savedEvent.getUser().getUserName()
        );
    }

    @Override
    public EventResponse update(String eventId, EventRequest request) {
        Event existingEvent = getById(eventId);
        // Check ownership

        existingEvent.setTitle(request.getTitle());
        existingEvent.setDescription(request.getDescription());

        Event savedEvent = eventRepository.save(existingEvent);

        return new EventResponse(
                savedEvent.getId().toString(),
                savedEvent.getTitle(),
                savedEvent.getDescription(),
                savedEvent.getUser().getUserName());
    }

    @Override
    public EventResponse changeEventStatus(String eventId, EEventStatus newStatus) {
        Event existingEvent = getById(eventId);
        // Check ownership

        existingEvent.setStatus(newStatus);
        Event savedEvent = eventRepository.save(existingEvent);

        return new EventResponse(
                savedEvent.getId().toString(),
                savedEvent.getTitle(),
                savedEvent.getDescription(),
                savedEvent.getUser().getUserName());
    }

    @Override
    public List<Event> getMyEvents(String userId) {
        return eventRepository.findByUserId(UUID.fromString(userId));
    }

    @Override
    public List<Event> getMyEventsWithStatus(String userId, String status) {
        return eventRepository.findByUseIdAndStatus(UUID.fromString(userId), status);
    }
}

