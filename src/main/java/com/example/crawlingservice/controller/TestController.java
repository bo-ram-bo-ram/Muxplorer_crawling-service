package com.example.crawlingservice.controller;

import com.example.crawlingservice.component.JsoupComponentLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final JsoupComponentLocal jsoupComponentLocal;

    @GetMapping("/test")
    public String test(){
        jsoupComponentLocal.getInfo();
        return "ok";
    }
}
