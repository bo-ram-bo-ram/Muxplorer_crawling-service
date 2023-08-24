package com.example.crawlingservice.service;

import com.example.crawlingservice.DB.Food;
import com.example.crawlingservice.dto.FoodDto;
import com.example.crawlingservice.dto.ReviewDto;
import com.example.crawlingservice.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FoodService {


    void save();
    List<FoodDto> findAllFoods();
    FoodDto findFoodById(Long id);
    void deleteFoodAll();
    List<ReviewDto> getReviewInfo();

}
