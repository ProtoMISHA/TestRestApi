package com.example.test.service;
import com.example.test.entity.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {

    public Optional<Book> findBookById(int id);

    public List<Book> getAllBooks();

    public void createOrUpdateBook(Book book);

    public void deleteBook(Book book);


}
