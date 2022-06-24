package com.plunex.emilokan.modules.subscription;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plunex.emilokan.modules.audit.AuditModel;
import com.plunex.emilokan.modules.event.Event;
import com.plunex.emilokan.modules.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Subscription extends AuditModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;


    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;


    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "event_id")
    private Event event;
}
