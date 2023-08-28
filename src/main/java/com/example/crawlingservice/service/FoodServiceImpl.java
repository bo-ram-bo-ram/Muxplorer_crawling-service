package com.example.crawlingservice.service;

import com.example.crawlingservice.DB.Food;
import com.example.crawlingservice.client.ReviewServiceClient;
import com.example.crawlingservice.component.JsoupComponentLocal;
import com.example.crawlingservice.dto.FoodDto;
import com.example.crawlingservice.dto.FoodRequest;
import com.example.crawlingservice.exception.NotFoundFoodByIdException;
import com.example.crawlingservice.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{

    private final ReviewServiceClient reviewServiceClient;
    //private final CircuitBreakerFactory circuitBreakerFactory;
    private final FoodRepository foodRepository;
    private final JsoupComponentLocal local;
    @Transactional
    @Override
    public void save() {
        List<Food> info = local.getInfo();
        deleteFoodAll();
        foodRepository.saveAll(info);

        List<FoodRequest> reviewDtoList = info.stream().map(food -> new FoodRequest(food.getRest() + "식당", food.getMenu()))
                .collect(Collectors.toList());

       // CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
//        circuitBreaker.run(() -> reviewServiceClient.foodRegister(reviewDtoList), throwable -> {
//            // 아무것도 하지 않습니다.
//        });
        try {
            reviewServiceClient.foodRegister(reviewDtoList);
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    @Override
    public List<FoodDto> findAllFoods() {
        List<Food> foods = foodRepository.findAll();

        return foods.stream()
                .map(food -> new FoodDto(food.getId(), food.getTime(), food.getRest(), food.getMenu(), food.getDate(), food.getDay()))
                .collect(Collectors.toList());
    }   //fooddto 반환으로 바꿈 . 리스트를 쓸 땐 stream 쓰는게 좋음

    @Override
    public FoodDto findFoodById(Long id) {
        Food food = foodRepository.findById(id).orElse(null);//비었으면 null값 넣기
        if (food == null) {
            throw new NotFoundFoodByIdException("아이디로 찾을 수 없습니다");
        }
        FoodDto foodDto = new FoodDto();
        foodDto.setId(food.getId());
        foodDto.setTime(food.getTime());
        foodDto.setRest(food.getRest());
        foodDto.setMenu(food.getMenu());
        foodDto.setDate(food.getDate());
        return foodDto;
    }

    @Override
    public void deleteFoodAll() {
        foodRepository.deleteAll();
    }

}
