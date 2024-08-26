package service;

import org.library.management.Book;

public interface LibraryService {
    void addBook(Book book);
    void removeBook(String title);
    void listAllBooks();
    void borrowBook(String title);
    void returnBook(String title);
    void exportLibrary();

    Book searchBook(String title);
}