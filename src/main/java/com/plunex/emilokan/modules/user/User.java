package com.plunex.emilokan.modules.user;


import com.plunex.emilokan.modules.audit.AuditModel;
import com.plunex.emilokan.modules.event.Event;
import com.plunex.emilokan.modules.role.Role;
import com.plunex.emilokan.modules.subscription.Subscription;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User extends AuditModel {

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles = new ArrayList<>();

}
