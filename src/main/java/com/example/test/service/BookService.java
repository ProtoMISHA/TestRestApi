package com.example.test.service;

import com.example.test.entity.Book;

import java.util.List;

public interface BookService {

    public Book findBookById(int id);

    public List<Book> getAllBooks();

    public void updateBook(Book book);

    public void createBook(Book book);

    public void deleteBook(int id);


}
