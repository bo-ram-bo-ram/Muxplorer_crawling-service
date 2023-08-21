package com.example.crawlingservice.service;

import com.example.crawlingservice.DB.Food;
import com.example.crawlingservice.component.JsoupComponentLocal;
import com.example.crawlingservice.dto.FoodDto;
import com.example.crawlingservice.exception.NotFoundFoodByIdException;
import com.example.crawlingservice.repository.FoodRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{

    private final FoodRepository foodRepository;
    private final JsoupComponentLocal local;
    @Override
    public void save() {
        List<Food> info = local.getInfo();
        foodRepository.saveAll(info);

    }

    @Override
    public List<Food> findAllFoods() {
        return foodRepository.findAll();
    }

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
        food.setMenu(food.getMenu());
        food.setDate(food.getDate());
        return foodDto;
    }

    @Override
    public void deleteFoodAll() {
        foodRepository.deleteAll();
    }
}
