package co.com.semillero.springBootBreB.repository;

import co.com.semillero.springBootBreB.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {}