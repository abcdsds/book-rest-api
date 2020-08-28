package simplebookrestapi.infra;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.http.HttpHeaders;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import simplebookrestapi.modules.entity.Account;
import simplebookrestapi.modules.entity.AccountRole;
import simplebookrestapi.modules.entity.AccountType;
import simplebookrestapi.modules.repository.AccountRepository;

@AutoConfigureMockMvc
@SpringBootTest
class ResourceServerTest {

	@Autowired MockMvc mockMvc;
	@Autowired AccountRepository accountRepository;
	@Autowired PasswordEncoder passwordEncoder;
	
	private Account createAccount() {
		Account account = Account.builder().email("asodasd@naver.com").nickname("nickname").password(passwordEncoder.encode("123123")).role(AccountRole.ROLE_USER).type(AccountType.NONE).build();
		return accountRepository.save(account);
	}
	
	String getAuthToken() throws Exception {

		Account createAccount = createAccount();
		
		ResultActions andExpect = mockMvc.perform(post("/oauth/token")
							.with(httpBasic("first-client", "first-secret"))
							.param("username", createAccount.getEmail())
							.param("password", "123123")
							.param("grant_type", "password")
							
						)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("access_token").exists());

		String contentAsString = andExpect.andReturn().getResponse().getContentAsString();
		JSONTokener jsonTokener = new JSONTokener(contentAsString);
		JSONObject nextValue = (JSONObject) jsonTokener.nextValue();
		return nextValue.getString("access_token");
		
	}
	
	@Test
	void test() throws Exception {
		
		mockMvc.perform(get("/api/book")
							.header(HttpHeaders.AUTHORIZATION, "Bearer " + getAuthToken())
						)
					.andDo(print())
					.andExpect(status().isOk());
					
		
	}
}
