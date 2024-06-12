package com.khasanov.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString(exclude = {"manager"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "store", schema = "movie")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "manager_staff_id", referencedColumnName = "staff_id", nullable = false)
    private Staff manager;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
    private Address address;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;
}
