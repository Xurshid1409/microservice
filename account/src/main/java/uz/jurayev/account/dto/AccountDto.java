package uz.jurayev.account.dto;

import lombok.Data;

@Data
public class AccountDto {

    private Integer id;
    private String accountType;
    private String branchAddress;
    private Integer customer_id;
}
