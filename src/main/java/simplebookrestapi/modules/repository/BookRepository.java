package simplebookrestapi.modules.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import simplebookrestapi.modules.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	

}
