package uz.jurayev.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jurayev.account.constants.AccountConstant;
import uz.jurayev.account.domain.Accounts;
import uz.jurayev.account.domain.Customer;
import uz.jurayev.account.dto.AccountDto;
import uz.jurayev.account.dto.CustomerDto;
import uz.jurayev.account.exception.CustomerExistException;
import uz.jurayev.account.exception.ResourceNotFoundException;
import uz.jurayev.account.repository.AccountRepository;
import uz.jurayev.account.repository.CustomerRepository;
import uz.jurayev.account.utils.Mapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public void createAccount(CustomerDto customerDto) {

        Customer customer = Mapper.customerDtoToEntity(new Customer(), customerDto);
        var customerOptional = customerRepository.findByPhoneNumber(customerDto.getPhoneNumber());
        if (customerOptional.isPresent()) {
            throw new CustomerExistException("Customer already exist with phoneNumber: " + customerDto.getPhoneNumber());
        }
        Customer saveCustomer = customerRepository.save(customer);
        generateAccount(saveCustomer.getId());

    }

    private void generateAccount(Integer customerId) {
        Accounts accounts = new Accounts();
        accounts.setAccountType("Saved");
        accounts.setBranchAddress("Tashkent, Almazar");
        accounts.setCustomer_id(customerId);
        accountRepository.save(accounts);
    }

    public CustomerDto fetchAccount(String phoneNumber) {

        var customer = customerRepository.findByPhoneNumber(phoneNumber);
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer with phoneNumber: " + phoneNumber + " " + AccountConstant.NOT_FOUND);
        }
        Optional<Accounts> accounts = accountRepository.findByCustomer_id(customer.get().getId());
        if (accounts.isEmpty()) {
            throw new ResourceNotFoundException("Accounts with phoneNumber: " + phoneNumber + " " + AccountConstant.NOT_FOUND);
        }
        CustomerDto response = Mapper.customerToDto(customer.get());
        AccountDto accountDto = Mapper.accountsToDto(accounts.get());
        response.setAccountDto(accountDto);
        return response;
    }

    public Boolean updateAccount(CustomerDto dto) {

        Optional<Customer> customer = customerRepository.findByPhoneNumber(dto.getPhoneNumber());
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer with phoneNumber: " + dto.getPhoneNumber() + " " + AccountConstant.NOT_FOUND);
        }

        Optional<Accounts> accounts = accountRepository.findByCustomer_id(customer.get().getId());
        if (accounts.isEmpty()) {
            throw new ResourceNotFoundException("Accounts with phoneNumber: " + dto.getPhoneNumber() + " " + AccountConstant.NOT_FOUND);
        }
        Accounts accountsNew = Mapper.accountsDtoToEntity(accounts.get(), dto.getAccountDto());
        accountRepository.save(accountsNew);
        return true;
    }

    public Boolean deleteAccount(String phoneNumber) {

        var customer = customerRepository.findByPhoneNumber(phoneNumber);
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer with phoneNumber: " + phoneNumber + " " + AccountConstant.NOT_FOUND);
        }
        Optional<Accounts> accounts = accountRepository.findByCustomer_id(customer.get().getId());
        if (accounts.isEmpty()) {
            throw new ResourceNotFoundException("Accounts with phoneNumber: " + phoneNumber + " " + AccountConstant.NOT_FOUND);
        }
        accountRepository.deleteAccountsByCustomer_id(customer.get().getId());
        customerRepository.deleteById(customer.get().getId());
        return true;
    }


}
