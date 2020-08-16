package simplebookrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simplebookrestapi.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	

}
