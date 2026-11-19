package com.example.librarymanagement.controller;

import com.example.librarymanagement.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    // Collection to store books
    private List<Book> books = new ArrayList<>();

    // B) Add a new book using POST
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        books.add(book);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // C) Retrieve all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {

        return ResponseEntity.ok(books);
    }

    // C) Retrieve a specific book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {

        for (Book book : books) {

            if (book.getId().equals(id)) {
                return ResponseEntity.ok(book);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // D) Update book details using PUT
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Integer id,
            @RequestBody Book updatedBook) {

        for (Book book : books) {

            if (book.getId().equals(id)) {

                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setCategory(updatedBook.getCategory());
                book.setAvailableCopies(updatedBook.getAvailableCopies());

                return ResponseEntity.ok(book);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // E) Delete a book using DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {

        for (Book book : books) {

            if (book.getId().equals(id)) {

                books.remove(book);

                return ResponseEntity.ok("Book deleted successfully!");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Book not found!");
    }
}