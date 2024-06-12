package com.khasanov.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = "films")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "actor", schema = "movie")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Long id;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = {@JoinColumn(name = "actor_id")},
            inverseJoinColumns = {@JoinColumn(name = "film_id")}
    )
    private Set<Film> films;
}