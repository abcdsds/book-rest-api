package simplebookrestapi.modules.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import simplebookrestapi.modules.entity.Account;
import simplebookrestapi.modules.entity.AccountAdapter;
import simplebookrestapi.modules.repository.AccountRepository;

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
		
		return new AccountAdapter(account);
	}
}
