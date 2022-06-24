package com.plunex.emilokan.modules.event;

import com.plunex.emilokan.modules.event.dto.EventRequest;
import com.plunex.emilokan.modules.event.dto.EventResponse;
import com.plunex.emilokan.modules.event.enums.EEventStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/events/")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping()
    public EventResponse createEvent(@RequestBody EventRequest request) {
        return eventService.create(request);
    }

    @PutMapping("{eventId}")
    public EventResponse updateEvent(
            @PathVariable(value = "eventId") String eventId,
            @RequestBody EventRequest request) {
        return eventService.update(eventId, request);
    }

    @PatchMapping(value = "{eventId}")
    public EventResponse changeEventStatus(@PathVariable(value = "eventId") String eventId,
                                           @RequestParam(value = "newStatus") EEventStatus newStatus) {
        return eventService.changeEventStatus(eventId, newStatus);
    }

    @GetMapping()
    public List<Event> getMyEvents(){
        return eventService.getMyEvents("d35b6699-567a-4d37-9dd5-2d7ad1b22f36");
    }

    @GetMapping(path = "my_events_with_status")
    public List<Event> getMyEventsWithStatus(
            @RequestParam(value = "status", defaultValue = "CREATED")  String status
    ){
        return eventService.getMyEventsWithStatus("d35b6699-567a-4d37-9dd5-2d7ad1b22f36",status);
    }


}
