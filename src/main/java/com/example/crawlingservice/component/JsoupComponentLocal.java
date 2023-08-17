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

    public  void getInfo() {
        final String URL = "https://www.cbnucoop.com/service/restaurant/?week=0";

        try {
            Document doc = Jsoup.connect(URL).get();
            Object menu = getmenu(doc); //메인 메뉴 이름 정보 조회
            //System.out.println(menu);

        } catch (IOException ignored) {
        }
    }

    public  Object getmenu(Document document) {
        Elements dateHtml = document.select(".weekday-title");
        Elements menuHtml = document.select("#menu-result > div.menu");
        Food foodObject = null;

        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<String> idList = new ArrayList<>();
        ArrayList<String> foodList = new ArrayList<>();



         for (Element element : dateHtml) {
            dateList.add(element.text());
        }

        for (Element element : menuHtml) {
            Elements headerHtml = element.getElementsByClass("card-header");
            foodList.add(headerHtml.text());

            String table = element.attr("data-table");
            String word = table.replaceAll("-", "");//-제거
            idList.add(word.substring(word.length() - 3, word.length()));//끝에 3글자 추출
        }

        for(int i=0;i<foodList.size();i++){
            foodObject = new Food();

            foodObject.setMenu(foodList.get(i));   //디비에 저장
            //foodObject.setDate(dateList.get(i));
            division(idList.get(i),foodObject);

            switch (idList.get(i).substring(idList.get(i).length()-1)){
                case"0":
                    foodObject.setDate(dateList.get(0));
                    break;
                case"1":
                    foodObject.setDate(dateList.get(1));
                    break;
                case"2":
                    foodObject.setDate(dateList.get(2));
                    break;
                case"3":
                    foodObject.setDate(dateList.get(3));
                    break;
                case"4":
                    foodObject.setDate(dateList.get(4));
                    break;
            }

            foodService.save(foodObject);

        }
        return foodObject;
    }


    public Object division(String parameter, Food food) {

        switch (parameter.substring(0,2)){
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
        return null;
    }

}


