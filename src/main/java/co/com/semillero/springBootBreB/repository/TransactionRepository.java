package co.com.semillero.springBootBreB.repository;

import co.com.semillero.springBootBreB.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {}
