package simplebookrestapi.modules.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Getter @EqualsAndHashCode(of = "id")
public class Account {

	@GeneratedValue @Id
	private Integer id;
	
	private String email;
	
	private String nickname;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private AccountType type;
	
	@Enumerated(EnumType.STRING)
	private AccountRole role;

	public void updatePassword(String encodedPassword) {
		// TODO Auto-generated method stub
		this.password = encodedPassword;
	}
	
	
}
