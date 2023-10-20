package uz.jurayev.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.jurayev.account.domain.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select c from Customer c where c.phoneNumber = ?1")
    Optional<Customer> findByPhoneNumber(String phoneNumber);
}
