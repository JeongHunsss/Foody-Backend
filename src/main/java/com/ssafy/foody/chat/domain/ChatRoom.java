package com.ssafy.foody.chat.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
    private String id; // UUID (랜덤)
    private Long reportId;
    private String userId;
    private String expertId;
    private LocalDateTime createdAt;
}
