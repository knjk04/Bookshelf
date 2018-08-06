package com.presentedbykaran.bookshelf;

import android.net.Uri;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by karan on 05/08/18.
 */

// Class to store book data
public class Book {
    private String bookTitle;
    private List<String> authors;
    private double rating;
    private ImageView image;

    public Book() {
    }

    public Book(String bookTitle, List<String> authors, double rating, ImageView image) {
        this.bookTitle = bookTitle;
        this.authors = authors;
        this.rating = rating;
        this.image = image;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthors(int position) {
        return authors.get(position);
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public double getRating() {
        return rating;
    }


    public void setRating(double rating) {
        this.rating = rating;
    }

    public ImageView getImage() {
        return image;
    }

//    public void setImage(ImageView image) {
//        this.image = image;
//    }

    public void setImage(Uri linkToImage) {
        if (linkToImage == null) {
            image.setImageResource(R.drawable.clear_white_24dp);
        } else image.setImageURI(linkToImage);
    }

}
