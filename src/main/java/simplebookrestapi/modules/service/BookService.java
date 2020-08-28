package simplebookrestapi.modules.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import simplebookrestapi.modules.entity.Book;
import simplebookrestapi.modules.entity.BookDto;
import simplebookrestapi.modules.repository.AccountRepository;
import simplebookrestapi.modules.repository.BookRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {

	private final BookRepository bookRepository;
	private final ModelMapper modelMapper;
	
	
	public Book createBook(BookDto bookDto) {
		
		Book book = modelMapper.map(bookDto, Book.class);
		bookRepository.save(book);
		
		return book;
	}
}
