package simplebookrestapi.modules.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import simplebookrestapi.modules.entity.Account;
import simplebookrestapi.modules.entity.AccountAdapter;
import simplebookrestapi.modules.repository.AccountRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService implements UserDetailsService {

	private final AccountRepository accountRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	public Account saveAccount(Account account) {
		account.updatePassword(passwordEncoder.encode(account.getPassword()));
		return accountRepository.save(account);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Account account = accountRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
		
		log.info("aaaaaaaaaaaaaaaaaaaaaaaaPASSWORDaaaaaaaaaaaaaaaaaaaaaaa {}", account.getPassword());
		
		return new AccountAdapter(account);
	}
}
