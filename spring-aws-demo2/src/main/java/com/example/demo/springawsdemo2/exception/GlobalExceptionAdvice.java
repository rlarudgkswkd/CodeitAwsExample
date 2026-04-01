package com.example.demo.springawsdemo2.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 모든 처리되지 않은 예외
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("❌ Unexpected error occurred", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("서버 내부 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
    }

    /**
     * 잘못된 요청 파라미터 / 유효성 검증 실패
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) {
        log.warn("⚠ Validation failed: {}", e.getMessage());
        return ResponseEntity.badRequest()
                .body("잘못된 요청 값입니다. 입력값을 확인해주세요.");
    }

    /**
     * 접근 거부 (권한 문제)
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException e) {
        log.warn("🚫 Access denied: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("접근 권한이 없습니다.");
    }

    /**
     * 사용자 정의 예외 (예: S3 업로드 실패)
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        log.error("❌ Runtime error: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage() != null ? e.getMessage() : "잘못된 요청입니다.");
    }
}
