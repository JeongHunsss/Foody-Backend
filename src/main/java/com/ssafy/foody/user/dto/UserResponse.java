package com.ssafy.foody.user.dto;

import com.ssafy.foody.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String id;
    private String name;
    private String email;
    private Integer age;
    private Double height;
    private Double weight;
    private String gender;
    private Integer activityLevel;
    private Boolean isDiabetes;
    private String role; // "ROLE_USER"

    // 표준 영양정보 (StdInfo)
    private Double stdWeight;
    private Double stdKcal;
    private Double stdCarb;
    private Double stdProtein;
    private Double stdFat;
    private Double stdSugar;
    private Double stdNatrium;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.age = user.getAge();
        this.height = user.getHeight();
        this.weight = user.getWeight();
        this.gender = user.getGender();
        this.activityLevel = user.getActivityLevel();
        this.isDiabetes = user.getIsDiabetes();
        this.role = user.getRole();

        // StdInfo가 있으면 추가
        if (user.getStdInfo() != null) {
            this.stdWeight = user.getStdInfo().getStdWeight();
            this.stdKcal = user.getStdInfo().getStdKcal();
            this.stdCarb = user.getStdInfo().getStdCarb();
            this.stdProtein = user.getStdInfo().getStdProtein();
            this.stdFat = user.getStdInfo().getStdFat();
            this.stdSugar = user.getStdInfo().getStdSugar();
            this.stdNatrium = user.getStdInfo().getStdNatrium();
        }
    }
}
