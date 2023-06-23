package kkol.crc.careg.config;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
public class ErrorMessage {
    private int statusCode;
    @Builder.Default
    private LocalDateTime localDateTime = LocalDateTime.now();
    private String message;
}
