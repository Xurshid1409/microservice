package uz.jurayev.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.jurayev.account.domain.Accounts;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Integer> {

    @Query("select a from Accounts a where a.customer_id = ?1")
    Optional<Accounts> findByCustomer_id(Integer customerId);

    @Transactional
    @Modifying
    @Query("delete from Accounts a where a.customer_id = ?1")
    boolean deleteAccountsByCustomer_id(Integer customerId);
}
