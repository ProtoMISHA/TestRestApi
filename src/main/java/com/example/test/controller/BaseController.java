package com.example.test.controller;
import com.example.test.entity.Book;
import com.example.test.excepiton.NoSuchBookException;
import com.example.test.excepiton.NotCorrectDataResponse;
import com.example.test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BaseController {


    @Autowired
    BookService bookService;



    @GetMapping("")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") int id){
        Optional<Book> book = bookService.findBookById(id);
        if(book.isEmpty()) throw new NoSuchBookException("Book with id " + id +" not exist");
        return book.get();
    }


    @PutMapping("/book")
    public Book editBook(@RequestBody Book book){
        bookService.createOrUpdateBook(book);
        return book;
    }


    @PostMapping("/book")
    public Book addBook(@RequestBody Book book){
        bookService.createOrUpdateBook(book);
        return book;
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id){
        Optional<Book> book = bookService.findBookById(id);
        if(book.isEmpty()) throw new NoSuchBookException("Book with id " + id + " not exist");
        bookService.deleteBook(book.get());
        return "Book was deleted " + id;
    }

    @ExceptionHandler
    public ResponseEntity<NotCorrectDataResponse> exceptionHandle(NoSuchBookException exception){
        NotCorrectDataResponse inf = new NotCorrectDataResponse();
        inf.setInfo(exception.getMessage());
        return new ResponseEntity<NotCorrectDataResponse>(inf, HttpStatus.NOT_FOUND);
    }

}
