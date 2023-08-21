package com.example.crawlingservice.repository;

import com.example.crawlingservice.DB.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food,String> {
    List<Food> findAll();
    Optional<Food> findById(Long id);

    void deleteAll();
}
