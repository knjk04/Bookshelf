package com.presentedbykaran.bookshelf;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by karan on 05/08/18.
 */

// Class to store book data
public class Book {
    private String bookTitle;
    private double rating;
    private ImageView image;

//    private List<String> authors;
    private String authors = "";

    public static final String TAG = Book.class.getSimpleName();

//    public Book() {
//    }

    public Book(Activity activity) {
        findViews(activity);
    }

    private void findViews(Activity activity) {
        image = activity.findViewById(R.id.imageView);
    }

    public Book(String bookTitle, List<String> authors, double rating, ImageView image,
            Activity activity) {
        this.bookTitle = bookTitle;
//        this.authors = authors;
        this.rating = rating;
        this.image = image;

        findViews(activity);
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

//    public String getAuthors(int position) {
//        return authors.get(position);
//    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
//        Log.d(TAG, "In setAuthors");
//        this.authors = authors;

        for (int i = 0; i < authors.size(); i++) {
            if (i > 0) this.authors += ", ";
            this.authors += authors.get(i);
        }

        Log.d(TAG, "strAuthors = " + authors);
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

    public void setImage(Uri linkToImage) {
        if (linkToImage == null) image.setImageResource(R.drawable.clear_white_24dp);
        else image.setImageURI(linkToImage);
    }

}
