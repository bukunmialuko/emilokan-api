package com.plunex.emilokan.modules.event;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plunex.emilokan.modules.audit.AuditModel;
import com.plunex.emilokan.modules.event.enums.EEventStatus;
import com.plunex.emilokan.modules.subscription.Subscription;
import com.plunex.emilokan.modules.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Event extends AuditModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "manager_id")
    private User user;

    @NonNull
    @Column
    private String title;

    @NonNull
    @Column
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private Collection<Subscription> subscribers;

    @NonNull
    @Column
    @Enumerated(EnumType.STRING)
    private EEventStatus status;

}
