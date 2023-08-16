package com.example.crawlingservice.component;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

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

        List<String> restList = new ArrayList<>();
        for (Element element : restName) {
            String text = element.text();
            Boolean rest = text.endsWith("식당") ? restList.add(text) : null;
        }
       //System.out.println("list = " + restList);
        return restList.toString();
    }

    public static String getmenu(Document document) {

        List<String> menuList = new ArrayList<>();
        List<Integer> idList = new ArrayList<>();
        Map<Integer, String> map = new LinkedHashMap<Integer, String>();

        Elements menu = document.select("#menu-result > div.menu");
        for (Element element : menu) {
            Elements header = element.getElementsByClass("card-header");
            String 메뉴 = header.text();
            menuList.add(메뉴);

            String table = element.attr("data-table");
            String word = table.replaceAll("-","");//-제거
            String id = word.substring(word.length()-3, word.length());//끝에 3글자 추출
            idList.add(Integer.valueOf(id));


            map.put(Integer.valueOf(id),메뉴);
        }
        return map.toString();
    }



    public static void main(String[] args){
        getInfo();
    }
}
