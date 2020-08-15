package simplebookrestapi.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {

	@GeneratedValue @Id
	private Long id;
	
	private String name;
	
	//private String EpisodesCount;
	
	private String author;
	
	private boolean completion;
	
	@Enumerated(EnumType.STRING)
	private bookGenre genre;
	
	
}
