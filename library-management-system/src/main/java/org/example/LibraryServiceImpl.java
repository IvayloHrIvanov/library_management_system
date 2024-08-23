package org.example;

import service.LibraryService;
import utility.GlobalLogger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LibraryServiceImpl implements LibraryService {
    private static final Map<String, Book> BOOKS_IN_LIBRARY = new HashMap<>();

    @Override
    public void addBook(Book book) {
        if (searchBook(book.getTitle()) != null) {
            logAndPrintInfo("Book is already in the Library!", "Book is already in the Library while trying to add!");
            return;
        }

        BOOKS_IN_LIBRARY.put(book.getTitle(), book);
        logAndPrintInfo("Book is added to the Library!");
    }

    @Override
    public void removeBook(String title) {
        Book book = searchBook(title);

        if (book == null) {
            logAndPrintInfo("Book is not in the Library!", "Book is not in the Library while trying to remove!");
            return;
        } else if (book.isBorrowed()) {
            logAndPrintInfo("Book is borrowed. Please return the book before removing!", "Book is borrowed while trying to remove!");
            return;
        }

        BOOKS_IN_LIBRARY.remove(title);
        logAndPrintInfo("Book is removed");
    }

    @Override
    public void listAllBooks() {
        if (BOOKS_IN_LIBRARY.isEmpty()) {
            logAndPrintInfo("Sorry, the Library is empty right now!", "Library is empty when trying to list!");
            return;
        }

        for (Book book : BOOKS_IN_LIBRARY.values()) {
            System.out.println(book.toString());
        }
        GlobalLogger.logInfoInFile("800", "Listed books");
    }

    @Override
    public Book searchBook(String title) {
        return BOOKS_IN_LIBRARY.get(title);
    }

    @Override
    public void borrowBook(String title) {
        Book book = searchBook(title);

        if (book == null) {
            logAndPrintInfo("Book not found in the library!", "Book is not found while trying to borrow!");
            return;
        } else if (book.isBorrowed()) {
            logAndPrintInfo("Sorry this book is already borrowed", "Book is borrowed while trying to borrow!");
            return;
        }

        book.setBorrowed(true);
        logAndPrintInfo("Book is borrowed from the Library!");
    }

    @Override
    public void returnBook(String title) {
        Book book = searchBook(title);

        if (book == null) {
            logAndPrintInfo("Book not found in the Library!", "Book not found in the Library while trying to return!");
            return;
        } else if (!book.isBorrowed()) {
            logAndPrintInfo("Sorry this book is not borrowed!", "Book is not borrowed while trying to return!");
            return;
        }

        book.setBorrowed(false);
        logAndPrintInfo("Book is returned to the Library!", "Book is returned to the Library!");
    }

    @Override
    public void exportLibrary() {
        Properties properties = new Properties();
        String pathForExport = "C:\\Users\\halo3\\Desktop\\Books.txt";

        for (Map.Entry<String, Book> entry : BOOKS_IN_LIBRARY.entrySet()) {
            properties.put(entry.getKey(), entry.getValue().toString());
        }

        try {
            properties.store(new FileOutputStream(pathForExport), null);

            System.out.println("Library is exported to: " + pathForExport);
            GlobalLogger.logInfoInFile("800", "Library is exported to: " + pathForExport);
        } catch (IOException ioException) {
            System.err.println("Library could not be exported! Check if there isn't a directory with the same name as the file name" +
                    " or if directory you are trying to export is not restricted. For further information check the Log file\n");
            GlobalLogger.logExceptionsInFile("900", ioException.getMessage(), ioException);
        }
    }

    private void logAndPrintInfo(String consoleMessage, String logMessage) {
        System.out.println(consoleMessage);
        GlobalLogger.logInfoInFile("800", logMessage);
    }

    private void logAndPrintInfo(String message) {
        System.out.println(message);
        GlobalLogger.logInfoInFile("800", message);
    }
}