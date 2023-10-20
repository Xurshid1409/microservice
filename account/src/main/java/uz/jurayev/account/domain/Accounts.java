package uz.jurayev.account.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Accounts extends AbstractEntity {

    private String accountType;
    private String branchAddress;
    private Integer customer_id;
}
