package simplebookrestapi.modules.entity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.EntityModel;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import simplebookrestapi.modules.controller.BookController;

public class BookResource extends EntityModel<Book> {
	
	@JsonUnwrapped
	private Book book;
	
	public BookResource(Book book) {
		this.book = book;
		add(linkTo(BookController.class).slash(this.book.getId()).withSelfRel());
	}
	
	public Book getBook() {
		return book;
	}
}
