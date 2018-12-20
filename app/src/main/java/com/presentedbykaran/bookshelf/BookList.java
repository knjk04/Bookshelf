package com.presentedbykaran.bookshelf;

import com.presentedbykaran.bookshelf.data.Book;

/**
 * Created by karan on 07/08/18.
 *
 * Bookshelf.  Copyright (C). 2018.  Karan Kumar
 * This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 * This is free software, and you are welcome to redistribute it
 * under certain conditions; type `show c' for details.
 *
 * This is licensed under GNU General Public License v3.0 only
 */
public class BookList {
    private Book[] books;

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }
}
