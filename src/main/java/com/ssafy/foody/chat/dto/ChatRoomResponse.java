package com.ssafy.foody.chat.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomResponse {
    private String id;
    private String userId;
    private String userName; // 유저 이름 (JOIN으로 조회)
    private Long reportId;
    private String lastMessage; // 마지막 메시지 (선택적)
    private LocalDateTime createdAt;
}
