package com.example.crawlingservice.repository;

import com.example.crawlingservice.DB.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food,String> {

}
