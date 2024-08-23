package org.library.management;

public class Book extends LibraryServiceImpl {
    private final String title;
    private final String author;
    private final String description;
    private boolean isBorrowed;

    public Book(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.isBorrowed = false;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[").append(title)
                .append(" by ").append(author)
                .append(", Description - ").append(description)
                .append(". Borrowed - ").append(isBorrowed).append("]");

        return sb.toString();
    }
}