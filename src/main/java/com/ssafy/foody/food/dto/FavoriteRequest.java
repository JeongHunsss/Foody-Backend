package com.ssafy.foody.food.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FavoriteRequest {
    private String foodCode;        // 일반 음식 코드
    private Integer userFoodCode;   // 사용자 음식 코드
}