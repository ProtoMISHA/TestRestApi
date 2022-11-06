package com.example.test.service;
import com.example.test.dao.BookDAO;
import com.example.test.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {


    @Autowired
    BookDAO bookDAO;


    @Override
    @Transactional
    public Optional<Book> findBookById(int id) {
        return bookDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookDAO.findAll();
    }



    @Override
    @Transactional
    public void createOrUpdateBook(Book book) {
        bookDAO.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(Book book){
        bookDAO.delete(book);
    }
}
