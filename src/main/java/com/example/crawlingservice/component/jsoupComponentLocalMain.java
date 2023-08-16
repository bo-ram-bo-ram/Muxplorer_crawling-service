package com.example.crawlingservice.component;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class jsoupComponentLocalMain {
    public static void getInfo() {
        final String URL = "https://www.cbnucoop.com/service/restaurant/?week=0";

        try {
            Document doc = Jsoup.connect(URL).get();
            String restaurant = getRestName(doc);   //식당 이름 정보 조회
            String menu = getmenu(doc); //메인 메뉴 이름 정보 조회

            System.out.println(restaurant);
            System.out.println(menu);
        } catch (IOException ignored) {
        }
    }

    public static String getRestName(Document document) {
        Elements restName = document.select(".nav-link");
        StringBuilder sb = new StringBuilder(); //문자열을 효율적으로 처리하는 데 사용되는 클래스 / 작업을 빠르게 처리
        List<String> list = new ArrayList<>();
        for (Element element : restName) {
            String text = element.text();
            Boolean 식당 = text.endsWith("식당") ? list.add(text) : null;
            sb.append(text.endsWith("식당") ? text : "");
            sb.append(" ");
            //System.out.println(element.text());
        }
        System.out.println("list = " + list);
        return sb.toString();
    }
//table tbody tr td div
    public static String getmenu(Document document) {
        Elements menu = document.select("#menu-result > div.menu");
        for (Element element : menu) {
            Elements div = element.getElementsByClass("card-header");
            System.out.println("data-table = " + element.attr("data-table"));
            System.out.println("div = " + div);
            //if (div == null) {
              //  System.out.println("1231123123123123 = ");
            //}

        }
        //System.out.println("menu = " + menu);

        //Integer count = 0;

        StringBuilder sb = new StringBuilder();
        List<String> menuList = new ArrayList<>();
        for (Element element : menu) {
            String text = element.text();
            sb.append(text);
            menuList.add(text);
            //sb.append(!text.contains("미운영") ? text : "");
            sb.append(" ");
            //System.out.println(element.text());
        }
//        System.out.println("menuList = " + menuList);
        return sb.toString();
//        //System.out.println();
    }

    public static void main(String[] args){
        getInfo();
    }
}
