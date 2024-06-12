package com.khasanov.entity;

import com.khasanov.util.RatingAttributeConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"categories", "actors"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "film", schema = "movie")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long id;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Column(name = "description", columnDefinition = "text")
    @Type(type = "text")
    private String description;

    @Column(name = "release_year")
    private int releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "language_id", nullable = false)
    private Language language;

    @ManyToOne
    @JoinColumn(name = "original_language_id", referencedColumnName = "language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration", nullable = false, columnDefinition = "tinyint default 3")
    @Builder.Default
    private int rentalDuration = 3;

    @Builder.Default
    @Column(name = "rental_rate", nullable = false)
    @ColumnDefault("4.99")
    private BigDecimal rentalRate = BigDecimal.valueOf(4.99);

    @Column(name = "length")
    private int length;

    @Builder.Default
    @Column(name = "replacement_cost", nullable = false)
    @ColumnDefault("19.99")
    private BigDecimal replacementCost = BigDecimal.valueOf(19.99);

    @Builder.Default
    @Convert(converter = RatingAttributeConverter.class)
    @Column(name = "rating")
    @ColumnDefault("'G'")
    private Rating rating = Rating.G;

    @Column(name = "special_features")
    private String specialFeatures;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JoinTable(
            name = "film_category",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private Set<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    private Set<Actor> actors;

}