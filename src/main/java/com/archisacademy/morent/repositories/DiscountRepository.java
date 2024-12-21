package com.archisacademy.morent.repositories;


import com.archisacademy.morent.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DiscountRepository extends JpaRepository<Discount, UUID> {
    boolean existsByCode(String code);
}
