package uz.jurayev.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CustomerDto {

    @NotBlank
    @NotEmpty(message = "Name can not be null or empty")
    private String name;

    @NotEmpty(message = "Email can not be null or empty")
    @Email
    private String email;
    private String phoneNumber;
    private AccountDto accountDto;
}
