package com.book;

import com.book.model.Author;
import com.book.model.Book;
import com.book.repository.AuthorRepository;
import com.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BookApplication implements CommandLineRunner {

	@Autowired
	private  AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Author author = new Author();
		author.setFirstname("Philip");
		author.setLastname("Covin");
		authorRepository.saveAndFlush(author);
		Author author1 = new Author();
		author1.setFirstname("James");
		author1.setLastname("King");
		authorRepository.saveAndFlush(author1);
		Author author2 = new Author();
		author2.setFirstname("Lisa");
		author2.setLastname("Gerber");
		authorRepository.saveAndFlush(author2);
		Set<Author> authors = new HashSet<>();
		authors.add(author);
		authors.add(author1);
		Book book = new Book();
		book.setTitle("Spring Boot By Example");
		book.setIsbn("9783161484100");
		book.setAuthors(authors);
		bookRepository.saveAndFlush(book);
		book = new Book();
		authors = new HashSet<>();
		authors.add(author1);
		authors.add(author2);
		book.setTitle("Spring Framework By Example");
		book.setIsbn("9783161484101");
		book.setAuthors(authors);
		bookRepository.saveAndFlush(book);

	}
}
