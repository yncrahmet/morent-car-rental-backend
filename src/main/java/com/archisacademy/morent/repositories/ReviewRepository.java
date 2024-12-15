package com.archisacademy.morent.repositories;

import com.archisacademy.morent.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
