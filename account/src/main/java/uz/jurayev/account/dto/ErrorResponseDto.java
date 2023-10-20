package uz.jurayev.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

    private String errorCode;
    private String errorMessage;
    private String apiPath;
    private LocalDateTime errorTime;

}
