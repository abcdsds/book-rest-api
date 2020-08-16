package simplebookrestapi.modules.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import simplebookrestapi.modules.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

	Optional<Account> findByEmail(String username);

}
