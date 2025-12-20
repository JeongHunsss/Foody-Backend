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
public class ChatMessage {
    private int id; // Auto Increment
    private String roomId;
    private String senderId;
    private String message;
    private LocalDateTime sentAt;
}
