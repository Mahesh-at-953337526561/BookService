package com.bookexample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.bookexample.model.Books;
import com.bookexample.repository.BooksRepository;

@Service
public class BooksServiceImpl implements BooksService {

	@Autowired
	private BooksRepository booksRepository;
	@Override
	public List<Books> getAllBooks() {
		// TODO Auto-generated method stub
		return booksRepository.findAll();
	}
	@Override
	public void saveBooks(Books books) {
		// TODO Auto-generated method stub
		this.booksRepository.save(books);
	}

	@Override
	public Books getBooksById(long bookId) {
		Optional<Books> optional = booksRepository.findById(bookId);
		Books books = null;
		if (optional.isPresent()) {
			books = optional.get();
		} else {
			throw new RuntimeException(" Books not found for id :: " + bookId);
		}
		return books;
	}

	@Override
	public void deleteBooksById(long bookId) {
		this.booksRepository.deleteById(bookId);
	}

	@Override
	public Page<Books> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.booksRepository.findAll(pageable);
	}
}
