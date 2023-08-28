package com.example.crawlingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;


@RequiredArgsConstructor
public class SchedulingServiceImpl implements SchedulingService {
    private final FoodService foodService;

    @Scheduled(cron = "* * 4 * * 1", zone = "Asia/Seoul")
    @Override
    public void crawlingScheduler() {

        foodService.save();
    }
}
