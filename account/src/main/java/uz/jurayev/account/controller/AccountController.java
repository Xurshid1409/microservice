package uz.jurayev.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.jurayev.account.constants.AccountConstant;
import uz.jurayev.account.dto.CustomerDto;
import uz.jurayev.account.dto.ResponseDto;
import uz.jurayev.account.service.AccountService;

@Tag(name = "AccountController", description = "CRUD operation in account microservice")
@RestController
@RequestMapping("/api/")
@Validated
public class AccountController {

    private final AccountService accountService;

    @Value("${build.message}")
    private String message;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("create")
    @Operation(description = "Create account rest API", summary = "Create account rest API")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "STATUS CODE 201")
    })
    public ResponseEntity<?> create(@RequestBody @Valid CustomerDto customerDto) {

        accountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstant.STATUS_CODE_201.getMessage(), AccountConstant.CREATED.getMessage()));
    }

    @GetMapping("fetchAccount")
    public ResponseEntity<?> fetchAccount(@RequestParam(value = "phoneNumber") String phoneNumber) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.fetchAccount(phoneNumber));
    }

    @PutMapping("updateAccount")
    public ResponseEntity<?> updateAccount(@RequestBody @Valid CustomerDto customerDto) {

        Boolean isSuccess = accountService.updateAccount(customerDto);
        if (isSuccess) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstant.STATUS_CODE_200.getMessage(), AccountConstant.SUCCESSFULLY.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDto(AccountConstant.STATUS_CODE_400.getMessage(), AccountConstant.ERROR.getMessage()));
    }

    @DeleteMapping("deleteAccount")
    public ResponseEntity<?> deleteAccount(@RequestParam(value = "phoneNumber") String phoneNumber) {

        Boolean isSuccess = accountService.deleteAccount(phoneNumber);
        if (isSuccess) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstant.STATUS_CODE_200.getMessage(), AccountConstant.SUCCESSFULLY.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDto(AccountConstant.STATUS_CODE_400.getMessage(), AccountConstant.ERROR.getMessage()));
    }

    @GetMapping("info")
    public String getInfo() {
        return message;
    }
}
