package org.library.management;

import service.LibraryService;
import utility.Helper;
import utility.GlobalLogger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LibraryServiceImpl implements LibraryService {
    // Centralized storage for all books in the library using a HashMap
    private static final Map<String, Book> BOOKS_IN_LIBRARY = new HashMap<>();

    // Adds a new book to the Library
    @Override
    public void addBook(Book book) {
        // Prevents duplicate books from being added
        if (searchBook(book.getTitle()) != null) {
            Helper.logAndPrintInfo("Book " + book.toStringTitleAndAuthor() + " is already in the Library!", "Book " + book.toStringTitleAndAuthor() + " is already in the Library while trying to add!");
            return;
        }

        BOOKS_IN_LIBRARY.put(book.getTitle(), book);
        Helper.logAndPrintInfo("Book: " + book.toStringTitleAndAuthor() + " is added to the Library!");
    }

    // Removes a book from the Library by title
    @Override
    public void removeBook(String title) {
        Book book = searchBook(title);

        // Ensures the book exists and it's not borrowed
        if (book == null) {
            Helper.logAndPrintInfo("Book is not in the Library!", "Book is not in the Library while trying to remove!");
            return;
        } else if (book.isBorrowed()) {
            Helper.logAndPrintInfo("Book " + book.toStringTitleAndAuthor() + " is borrowed. Please return the book before removing!", "Book " + book.toStringTitleAndAuthor() + " is borrowed while trying to remove!");
            return;
        }

        BOOKS_IN_LIBRARY.remove(title);
        Helper.logAndPrintInfo("Book " + book.toStringTitleAndAuthor() + " is removed");
    }

    // Lists all books currently in the Library.
    @Override
    public void listAllBooks() {
        if (BOOKS_IN_LIBRARY.isEmpty()) {
            Helper.logAndPrintInfo("Sorry, the Library is empty right now!", "Library is empty when trying to list!");
            return;
        }

        for (Book book : BOOKS_IN_LIBRARY.values()) {
            System.out.println(book.toString());
        }

        GlobalLogger.logEventInFile("800", "Listed books");
    }

    // Searches for a book in the Library by title.
    @Override
    public Book searchBook(String title) {
        return BOOKS_IN_LIBRARY.get(title);
    }

    // Borrows a book from the Library.
    @Override
    public void borrowBook(String title) {
        Book book = searchBook(title);

        // Ensures the book exists and it's not borrowed
        if (book == null) {
            Helper.logAndPrintInfo("Book not found in the library!", "Book is not found while trying to borrow!");
            return;
        } else if (book.isBorrowed()) {
            Helper.logAndPrintInfo("Sorry this book " + book.toStringTitleAndAuthor() + " is already borrowed", "Book " + book.toStringTitleAndAuthor() + " is borrowed while trying to borrow!");
            return;
        }

        book.setBorrowed(true);
        Helper.logAndPrintInfo("Book " + book.toStringTitleAndAuthor() + " is borrowed from the Library!");
    }

    // Returns a borrowed book to the Library.
    @Override
    public void returnBook(String title) {
        Book book = searchBook(title);

        // Ensures the book exists and it's borrowed
        if (book == null) {
            Helper.logAndPrintInfo("Book not found in the Library!", "Book not found in the Library while trying to return!");
            return;
        } else if (!book.isBorrowed()) {
            Helper.logAndPrintInfo("Sorry this book " + book.toStringTitleAndAuthor() + " is not borrowed!", "Book " + book.toStringTitleAndAuthor() + " is not borrowed while trying to return!");
            return;
        }

        book.setBorrowed(false);
        Helper.logAndPrintInfo("Book " + book.toStringTitleAndAuthor() + " is returned to the Library!", "Book " + book.toStringTitleAndAuthor() + " is returned to the Library!");
    }

    // Exports the current library to a file.
    @Override
    public void exportLibrary() {
        Properties properties = new Properties();
        String pathForExport = "D:\\Books.txt";

        for (Map.Entry<String, Book> entry : BOOKS_IN_LIBRARY.entrySet()) {
            properties.put(entry.getKey(), entry.getValue().toString());
        }

        try {
            properties.store(new FileOutputStream(pathForExport), null); // Writes the properties to a file.

            System.out.println("Library is exported to: " + pathForExport);
            GlobalLogger.logEventInFile("800", "Library is exported to: " + pathForExport);
        } catch (IOException ioException) {
            System.err.println("Library could not be exported! Check if there isn't a directory with the same name as the file name" +
                    " or if directory you are trying to export is not restricted. For further information check the Log file\n");
            GlobalLogger.logExceptionInFile("900", ioException.getMessage(), ioException);
        }
    }
}