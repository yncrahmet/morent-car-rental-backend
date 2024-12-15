package com.archisacademy.morent.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "review")
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "review_id", nullable = false, unique = true, updatable = true)
    private String reviewId = UUID.randomUUID().toString();

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "review_text", nullable = false)
    private String reviewText;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id")
    private Vehicle vehicle;

    @PrePersist
    protected void creationTime(){
        createdAt = Timestamp.from(Instant.now());
    }
}
