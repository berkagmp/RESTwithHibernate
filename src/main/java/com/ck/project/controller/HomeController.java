package com.ck.project.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ck.project.model.Book;
import com.ck.project.service.BookService;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/api")
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private BookService bookService;

	/*---Add new book---*/
	//@PostMapping("/book")
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> save(@RequestBody Book book) {
		long id = bookService.save(book);
		return ResponseEntity.ok().body("New Book has been saved with ID:" + id);
	}

	/*---Get a book by id---*/
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> get(@PathVariable("id") long id) {
		Book book = bookService.get(id);
		return ResponseEntity.ok().body(book);
	}

	/*---get all books---*/
	//@GetMapping("/book")
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Book>> list() {
		logger.info("CK: " + this.toString());
		List<Book> books = bookService.list();
		return ResponseEntity.ok().body(books);
	}

	/*---Update a book by id---*/
	@PutMapping("/book/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Book book) {
		bookService.update(id, book);
		return ResponseEntity.ok().body("Book has been updated successfully.");
	}

	/*---Delete a book by id---*/
	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		bookService.delete(id);
		return ResponseEntity.ok().body("Book has been deleted successfully.");
	}

}
