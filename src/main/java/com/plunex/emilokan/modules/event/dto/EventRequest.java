package com.plunex.emilokan.modules.event.dto;

import lombok.Data;

@Data
public class EventRequest {

    private String ownerId;

    private String title;

    private String description;

}
