package simplebookrestapi.infra;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import simplebookrestapi.modules.entity.Account;
import simplebookrestapi.modules.entity.AccountRole;
import simplebookrestapi.modules.entity.AccountType;
import simplebookrestapi.modules.repository.AccountRepository;

@AutoConfigureMockMvc
@SpringBootTest
class AuthJwtTest {

	@Autowired MockMvc mockMvc;
	@Autowired AccountRepository accountRepository;
	@Autowired PasswordEncoder passwordEncoder;
	
	
	@BeforeEach
	void init() {
		
		accountRepository.deleteAll();
	}
	
	private Account createAccount() {
		Account account = Account.builder().email("asodasd@naver.com").nickname("nickname").password(passwordEncoder.encode("123123")).role(AccountRole.ROLE_USER).type(AccountType.NONE).build();
		return accountRepository.save(account);
	}
	
	
	@DisplayName("인증 실패")
	@Test
	void auth_fail_unauthorized() throws Exception {
		
		mockMvc.perform(post("/oauth/token"))
				.andDo(print())
				.andExpect(status().is4xxClientError());
		
	}
	
	@DisplayName("인증 성공")
	@Test
	void auth_success() throws Exception {

		Account createAccount = createAccount();
		
		mockMvc.perform(post("/oauth/token")
							.with(httpBasic("first-client", "first-secret"))
							//.param("first-client", "first-secret")
							.param("username", createAccount.getEmail())
							.param("password", "123123")
							.param("grant_type", "password")
							
						)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("access_token").exists());
		
//				
//				
//				
//				.with(httpBasic(appProperties.getClientId(), appProperties.getClientSecret()))
//				.param("username" , "email")
//				.param("password" , "password")
//				.param("grant_type" , "password"))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("access_token").exists());
		
	}

}
