package com.example.crawlingservice.client;

import com.example.crawlingservice.dto.FoodRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "review-service")
public interface ReviewServiceClient {
    @PostMapping("/api/send/foods")
    void foodRegister(@RequestBody List<FoodRequest> foodRequest);
}
