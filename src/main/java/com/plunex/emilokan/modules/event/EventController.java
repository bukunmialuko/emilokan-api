package com.plunex.emilokan.modules.event;

import com.plunex.emilokan.modules.event.dto.EventRequest;
import com.plunex.emilokan.modules.event.dto.EventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/events/")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping()
    public EventResponse addStore(@RequestBody EventRequest request) {
        return eventService.create(request);
    }

}
