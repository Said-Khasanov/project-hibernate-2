package com.khasanov.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "film_text", schema = "movie")
public class FilmText {
    @Id
    @Column(name = "film_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", nullable = false)
    private Film film;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Column(name = "description", columnDefinition = "text")
    @Type(type = "text")
    private String description;
}
