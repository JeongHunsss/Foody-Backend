package com.ssafy.foody.common.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 비즈니스 로직 에러
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Business Logic Error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // @Valid 유효성 검사 실패 (DTO 검증 에러)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        log.warn("Validation Error: {}", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // SQL 에러
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDBException(DataIntegrityViolationException ex) {
        log.error("DB Error: {}", ex.getMessage());

        if (ex instanceof DuplicateKeyException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 데이터입니다.");
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("데이터베이스 처리 중 오류가 발생했습니다. (입력값 확인 필요)");
    }

    // 그 외 모든 에러
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        log.error("Unexpected Error: ", ex); // 스택트레이스 출력
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류가 발생했습니다. 관리자에게 문의하세요.");
    }
}
