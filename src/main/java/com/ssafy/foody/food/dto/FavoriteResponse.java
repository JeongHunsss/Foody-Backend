package com.ssafy.foody.food.dto;

import lombok.Data;

@Data
public class FavoriteResponse {
    private int favoriteId;         // id
    
    // 어떤 음식인지 구분
    private String foodCode;        // DB 음식
    private Integer userFoodCode;   // 사용자 입력 음식
    
    private String name;
    private String standard;
    private double kcal;
    private double carb;
    private double protein;
    private double fat;
    private double sugar;
    private double natrium;    
}