package simplebookrestapi.modules.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class BookDto {
	
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String author;
	
	@NotBlank
	private boolean completion;
	
	@NotBlank
	private bookGenre genre;
	
}
