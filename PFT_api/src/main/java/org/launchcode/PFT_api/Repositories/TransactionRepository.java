package org.launchcode.PFT_api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.launchcode.PFT_api.Models.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction , Long> {

}
