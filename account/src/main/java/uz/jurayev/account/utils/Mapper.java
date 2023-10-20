package uz.jurayev.account.utils;

import uz.jurayev.account.domain.Accounts;
import uz.jurayev.account.domain.Customer;
import uz.jurayev.account.dto.AccountDto;
import uz.jurayev.account.dto.CustomerDto;

public class Mapper {

    public static Customer customerDtoToEntity(Customer customer, CustomerDto dto) {
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        return customer;
    }

    public static CustomerDto customerToDto(Customer customer) {
        var customerDto = new CustomerDto();
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhoneNumber(customer.getPhoneNumber());
        return customerDto;
    }

    public static Accounts accountsDtoToEntity(Accounts accounts, AccountDto dto) {
        accounts.setAccountType(dto.getAccountType());
        accounts.setBranchAddress(dto.getBranchAddress());
        return accounts;
    }

    public static AccountDto accountsToDto(Accounts accounts) {
        var accountDto = new AccountDto();
        accountDto.setId(accounts.getId());
        accountDto.setCustomer_id(accounts.getCustomer_id());
        accountDto.setAccountType(accounts.getAccountType());
        accountDto.setBranchAddress(accounts.getBranchAddress());
        return accountDto;
    }
}
