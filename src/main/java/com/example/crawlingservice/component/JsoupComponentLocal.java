package com.example.crawlingservice.component;

import com.example.crawlingservice.DB.Food;
import com.example.crawlingservice.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JsoupComponentLocal {
    private final FoodService foodService;

    public void getInfo() {
        final String URL = "https://www.cbnucoop.com/service/restaurant/?week=0";

        try {
            Document doc = Jsoup.connect(URL).get();
            String menu = getmenu(doc); //메인 메뉴 이름 정보 조회

            System.out.println(menu);

        } catch (IOException ignored) {
        }
    }

    public String getmenu(Document document) {

        Map<Integer, String> map = new LinkedHashMap<Integer, String>();//ID-대표메뉴 저장

        Elements menu = document.select("#menu-result > div.menu");
        for (Element element : menu) {
            Food foodObject = new Food();

            Elements header = element.getElementsByClass("card-header");
            String food = header.text();

            String table = element.attr("data-table");
            String word = table.replaceAll("-", "");//-제거
            String id = word.substring(word.length() - 3, word.length());//끝에 3글자 추출

            map.put(Integer.valueOf(id), food);

            foodObject.setMenu(food);   //디비에 저장
            division(id,foodObject);
            System.out.println(foodObject);
        }
        return map.toString();
    }


    public Object division (String parameter, Food food) {

        switch (parameter.substring(0, 2)){
            case "25" :
                food.setRest("은하수");
                food.setTime("저녁");
                break;
            case "12" :
                food.setRest("은하수");
                food.setTime("점심");
                break;
            case "14" :
                food.setRest("별빛");
                food.setTime("점심");
                break;
            case "16" :
                food.setRest("한빛");
                food.setTime("점심");
                break;
            case "17" :
                food.setRest("한빛");
                food.setTime("아점");
                break;
            case "18" :
                food.setRest("한빛");
                food.setTime("석식");
                break;
        }
        switch (parameter.substring(parameter.length()-1)){
            case"0":
                food.setDate("월");
                break;
            case"1":
                food.setDate("화");
                break;
            case"2":
                food.setDate("수");
                break;
            case"3":
                food.setDate("목");
                break;
            case"4":
                food.setDate("금");
                break;
        }

        foodService.save(food);

        return food;
    }

}


