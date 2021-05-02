package com.bookexample.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bookexample.model.Books;

public interface BooksService {
 List <Books>getAllBooks();
 
 void saveBooks(Books books);
	Books getBooksById(long bookId);
	void deleteBooksById(long bookId);
	Page<Books> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
