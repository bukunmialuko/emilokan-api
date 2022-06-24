package com.plunex.emilokan.modules.role;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NonNull
    @Column(unique = true)
    private String name;

//    This is causing issues, when you call the user entity
//    e.g when you call who am i, without model mapper.
//    But roles has to be eager loaded
//    @ManyToMany(mappedBy = "roles")
//    private Collection<User> users;

    @Override
    public String getAuthority() {
        return name;
    }
}
