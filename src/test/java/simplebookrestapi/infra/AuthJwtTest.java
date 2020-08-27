package simplebookrestapi.infra;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class AuthJwtTest {

	@Autowired MockMvc mockMvc;
	
	@DisplayName("인증 실패")
	@Test
	void auth_fail_unauthorized() throws Exception {
		

		mockMvc.perform(post("/oauth/token"))
				.andDo(print())
				.andExpect(status().is4xxClientError());
		
	}
	
	@Test
	void test() throws Exception {
		

		mockMvc.perform(post("/oauth/token"))
				.andDo(print())
				.andExpect(status().is4xxClientError());
		
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
