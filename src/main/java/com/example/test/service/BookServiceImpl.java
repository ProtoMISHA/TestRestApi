package com.example.test.service;


import com.example.test.dao.BookDAO;
import com.example.test.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    @Autowired
    BookDAO bookDAO;


    @Override
    @Transactional
    public Book findBookById(int id) {
        return bookDAO.findBookById(id);
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    @Override
    @Transactional
    public void createBook(Book book) {
        bookDAO.createBook(book);
    }

    @Override
    @Transactional
    public void deleteBook(int id) {
        bookDAO.deleteBook(id);
    }
}
