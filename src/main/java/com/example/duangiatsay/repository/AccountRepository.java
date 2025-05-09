package com.example.duangiatsay.repository;

import com.example.duangiatsay.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
    // AccountRepository.java
    @Query("SELECT a FROM Account a WHERE a.role.name = 'SHIPPER'")
    List<Account> findAllShipperAccounts();

}
