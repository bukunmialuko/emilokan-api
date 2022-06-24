package com.plunex.emilokan.modules.user;


import com.plunex.emilokan.modules.event.Event;
import com.plunex.emilokan.modules.subscription.Subscription;
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
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NonNull
    @Column
    private String firstName;

    @NonNull
    @Column
    private String lastName;

    @NonNull
    @Column
    private String userName;

    @Column
    private String phoneNumber;

    @NonNull
    @Column(unique = true)
    private String email;

    @NonNull
    @Column
    private String password;

    @ManyToOne()
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "user")
    private Collection<Event> myEvents;

    @OneToMany(mappedBy = "user")
    private Collection<Subscription> subscriptions;

}
