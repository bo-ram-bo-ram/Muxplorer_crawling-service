package com.example.crawlingservice.service;

import com.example.crawlingservice.dto.FoodDto;

import java.util.List;

public interface FoodService {

    void save();
    List<FoodDto> findAllFoods();
    FoodDto findFoodById(Long id);
    void deleteFoodAll();


}
