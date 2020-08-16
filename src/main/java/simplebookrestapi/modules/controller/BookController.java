package simplebookrestapi.modules.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.var;
import simplebookrestapi.modules.entity.Book;
import simplebookrestapi.modules.repository.BookRepository;


@RequiredArgsConstructor
@RequestMapping(value = "/api/book" , produces = MediaTypes.HAL_JSON_VALUE)
@RestController
public class BookController {

	
	private final BookRepository bookRespository;
	
	@GetMapping("")
	public ResponseEntity getBooks(Pageable pageable, PagedResourcesAssembler<Book> assemble) {
		
		Page<Book> findAll = bookRespository.findAll(pageable);
		var model = assemble.toModel(findAll).add(Link.of("/docs/index.html#resources-books-list").withRel("profile"));
		
		return ResponseEntity.ok(model);
	}
	
	
}
