package co.com.semillero.springBootBreB.repository;

import co.com.semillero.springBootBreB.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Account.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {}