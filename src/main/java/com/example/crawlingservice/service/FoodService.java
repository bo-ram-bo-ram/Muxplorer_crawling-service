package com.example.crawlingservice.service;

import com.example.crawlingservice.DB.Food;
import com.example.crawlingservice.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public void save(Food food){
        foodRepository.save(food);
    }
}
