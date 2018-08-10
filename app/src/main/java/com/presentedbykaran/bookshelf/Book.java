package com.presentedbykaran.bookshelf;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.util.List;

/**
 * Created by karan on 05/08/18.
 */

// Class to store book data
public class Book implements Serializable {
    private String bookTitle;
    private String authors = "By ";
    private String rating = "Rating on Google Books: ";
    private String ratingsCount = "(";

    private String strImageURL;
    private SimpleDraweeView draweeView;

    private static final String RATING_COUNT_SUFFIX = " ratings)";
    public static final String TAG = Book.class.getSimpleName();

    public Book(Activity activity) {
        findViews(activity);
    }

//    public Book(String bookTitle, List<String> authors, double rating, int ratingsCount,
//                ImageView image, Activity activity) {

    public Book(String bookTitle, List<String> authors, double rating, int ratingsCount,
                String strImageURL, Activity activity) {
        this.bookTitle = bookTitle;
        setAuthors(authors); // to get the comma separated list
        this.rating += rating;
        this.ratingsCount += ratingsCount + RATING_COUNT_SUFFIX;

//        this.strImageURL = strImageURL;
        changeHttpToHttps(strImageURL);

        findViews(activity);
//        setImage(strImageURL);
    }

//    private void setImage(String strImageURL) {
//        Uri uri = Uri.parse(strImageURL);
//        draweeView.setImageURI(uri);
//    }


    private void findViews(Activity activity) {
        draweeView = activity.findViewById(R.id.bookCoverDrawee);
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
//        Log.d(TAG, "In setAuthors");

        // Makes a comma separated string if there is more than author
        for (int i = 0; i < authors.size(); i++) {
            if (i > 0) this.authors += ", ";
            this.authors += authors.get(i);
        }

        Log.d(TAG, "strAuthors = " + authors);
    }

//    public double getRating() {
//        return rating;
//    }

    public String getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating += rating;
    }

    public String getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount += ratingsCount + RATING_COUNT_SUFFIX;
    }

//    public ImageView getImage() {
//        return image;
//    }
//
//    public void setImage(Uri linkToImage) {
//        if (linkToImage == null) image.setImageResource(R.drawable.clear_white_24dp);
//        else image.setImageURI(linkToImage);
//    }

    public String getStrImageURL() {
        return strImageURL;
    }

    public void setStrImageURL(String strImageURL) {
//        this.strImageURL = strImageURL;
//        setImage(strImageURL);

        changeHttpToHttps(strImageURL);
    }

    // This method converts a http link to a https link
    // This is needed in order to use Fresco to set the image. It won't work with a regular http link
    private void changeHttpToHttps(String strImageURL) {
        this.strImageURL = strImageURL.replace("http","https");
        Log.d(TAG, "strImageURL: " + this.strImageURL);
    }
}
