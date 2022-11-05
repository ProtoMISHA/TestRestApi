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

@RestController
@RequestMapping("/")
public class BaseController {


    @Autowired
    BookService bookService;



    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/books/book/{id}")
    public Book getBook(@PathVariable("id") int id){
        Book book = bookService.findBookById(id);
        if(book==null) throw new NoSuchBookException("Book with id " + id +" not exist");

        return book;
    }


    @PutMapping("/books")
    public Book editBook(@RequestBody Book book){
        bookService.updateBook(book);
        return book;
    }


    @PostMapping("/books")
    public Book addBook(@RequestBody Book book){
        bookService.createBook(book);
        return book;
    }

    @DeleteMapping("/books/book/{id}")
    public String deleteBook(@PathVariable int id){
        if(bookService.findBookById(id)==null) throw new NoSuchBookException("Book with id " + id +" not exist");


        bookService.deleteBook(id);
        return "Книга была удалена, ее id " + id;
    }

    @ExceptionHandler
    public ResponseEntity<NotCorrectDataResponse> exceptionHandle(NoSuchBookException exception){
        NotCorrectDataResponse inf = new NotCorrectDataResponse();
        inf.setInfo(exception.getMessage());
        return new ResponseEntity<NotCorrectDataResponse>(inf, HttpStatus.NOT_FOUND);
    }

}
