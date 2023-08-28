package com.example.crawlingservice.config;

import com.example.crawlingservice.client.ReviewServiceClient;
import com.example.crawlingservice.component.JsoupComponentLocal;
import com.example.crawlingservice.repository.FoodRepository;
import com.example.crawlingservice.service.FoodService;
import com.example.crawlingservice.service.FoodServiceImpl;
import com.example.crawlingservice.service.SchedulingService;
import com.example.crawlingservice.service.SchedulingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final ReviewServiceClient reviewServiceClient;
    private final FoodRepository foodRepository;
    private final JsoupComponentLocal jsoupComponentLocal;
    @Bean
    public FoodService foodService() {
        return new FoodServiceImpl(reviewServiceClient, foodRepository, jsoupComponentLocal);
    }

    @Bean
    public SchedulingService schedulingService() {
        return new SchedulingServiceImpl(foodService());
    }
}
