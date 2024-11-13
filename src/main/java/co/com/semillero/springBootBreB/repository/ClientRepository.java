package co.com.semillero.springBootBreB.repository;

import co.com.semillero.springBootBreB.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {}
