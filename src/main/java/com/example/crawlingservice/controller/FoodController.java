package com.example.crawlingservice.controller;

import com.example.crawlingservice.dto.FoodDto;
import com.example.crawlingservice.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/create/foods")
    public ResponseEntity<?> registerFood() {
        foodService.save();
        return ResponseEntity.status(HttpStatus.CREATED).body("SUCCESS");
    }

    @GetMapping("/find/food-list")
    public ResponseEntity<?> findFoodList() {
        List<FoodDto> foodList = foodService.findAllFoods();
        return ResponseEntity.status(HttpStatus.OK).body(foodList);
    }

    @GetMapping("/find/food/{id}")
    public ResponseEntity<?> findFoodById(@PathVariable Long id){
        FoodDto food = foodService.findFoodById(id);
        return ResponseEntity.status(HttpStatus.OK).body(food);
    }

    @DeleteMapping("/delete/food-list")
    public ResponseEntity<?> deleteFood() {
        foodService.deleteFoodAll();
        return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
    }

}
