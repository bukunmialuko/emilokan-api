package com.plunex.emilokan.modules.event.dto;


import com.plunex.emilokan.modules.user.User;
import jakarta.persistence.Column;
import org.springframework.lang.NonNull;

public record EventResponse(String id,String title, String description, String createdBy) {

}