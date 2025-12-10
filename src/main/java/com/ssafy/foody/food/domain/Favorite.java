package com.ssafy.foody.food.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {
    private int id;                 // PK
    private String userId;          // 사용자 ID
    // 음식 코드 둘 중 하나는 null
    private String foodCode;        // 일반 음식 코드 
    private Integer userFoodCode;   // 사용자 정의 음식 코
}