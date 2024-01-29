package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books;

    public Books() {
        this.books = new ArrayList<>();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        this.books.add(book);
        return book;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAll() {
        return this.books;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getOneBook(@PathVariable int id) {
        return this.books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody Book book) {
        Book bookToUpdate = this.books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        if (bookToUpdate != null) {
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setNumPages(book.getNumPages());
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setGenre(book.getGenre());
        }
        return bookToUpdate;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book delete(@PathVariable int id) {
        Book bookToDelete = this.books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        if (bookToDelete != null) {
            this.books.remove(bookToDelete);
        }
        return bookToDelete;
    }
}