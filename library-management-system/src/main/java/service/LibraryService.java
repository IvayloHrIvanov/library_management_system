package service;

import org.example.Book;

public interface LibraryService {
    void addBook(Book book) throws Exception;
    void removeBook(String title);
    void listAllBooks();
    void borrowBook(String title);
    void returnBook(String title);
    void exportLibrary();

    Book searchBook(String title);
}