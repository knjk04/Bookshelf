package com.presentedbykaran.bookshelf;

import android.app.Activity;
import android.util.Log;

import java.io.Serializable;
import java.util.List;

/**
 * Created by karan on 05/08/18.
 *
 * Bookshelf.  Copyright (C). 2018.  Karan Kumar
 * This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 * This is free software, and you are welcome to redistribute it
 * under certain conditions; type `show c' for details.
 *
 * This is licensed under GNU General Public License v3.0 only
 */

// Class to store book data

// The reason why all fields are Strings (even some you may expect to be otherwise, e.g. int for
// the number of pages, is because lots of these fields are optional. Displaying, for example,
// 0 pages is silly, but displaying "Unknown number of pages" is better. However, the argument in
// the setter of these member variables is what you would expect. They correspond to the data type
// that we retrieve from the Google Books API.

// Aside from strImageUrl, the names correspond to the relevant Google Books API property name
public class Book implements Serializable {
    // --- Constants ---
    private static final String PUBLISHER_SUFFIX = "not found";
    private static final String RATING_COUNT_SUFFIX = " ratings)";
    private static final String TAG = Book.class.getSimpleName();

    // --- Fields ---
    private String bookTitle;
    private String authors = "By ";
    private String rating = "Rating on Google Books: ";
    private String ratingsCount = "(";
    private String strImageURL;
    private String publisher = "Publisher: ";
    private String publishedDate = "Published Date: ";
    private String pageCount = "Number of pages: ";
    private String description;

    // unique to every book, so use as an ID for saving to internal storage
    // This means that when adding a book to a bookshelf, only the selfLink needs to be stored, not
    // all the other info about it, since that can easily be retrieved from JSON again when needed
    private String selfLink;

//    private SimpleDraweeView draweeView;

    // --- Constructors ---
    public Book(Activity activity) {
//        findViews(activity);
    }

    public Book(String bookTitle, List<String> authors, double rating, int ratingsCount,
                String strImageURL, Activity activity) {
        this.bookTitle = bookTitle;
        setAuthors(authors); // to get the comma separated list
        this.rating += rating;
        this.ratingsCount += ratingsCount + RATING_COUNT_SUFFIX;

        changeHttpToHttps(strImageURL);

//        findViews(activity);
//        setImage(strImageURL);
    }

//    private void setImage(String strImageURL) {
//        Uri uri = Uri.parse(strImageURL);
//        draweeView.setImageURI(uri);
//    }


//    private void findViews(Activity activity) {
////        draweeView = activity.findViewById(R.id.bookCoverDrawee);
//    }

    // --- public methods ---

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
        int authorsSize = authors.size();

        // TODO: fix this
        // this doesn't seem to work
        if (authorsSize == 0) this.authors += "no authors found";

        // Makes a comma separated string if there is more than author
        for (int i = 0; i < authorsSize; i++) {
            if (i > 0) {
                this.authors += ", ";
            }
            this.authors += authors.get(i);
        }
//        Log.d(TAG, "strAuthors = " + authors);
    }

    public String getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating += rating;
    }

    public String getRatingsCount() {
        return ratingsCount;
    }

    // TODO: change the argument to a String.
    // Currently, if there isn't a ratings property, it is set to 0, Instead, it should be set to
    // "no ratings"
    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount += ratingsCount + RATING_COUNT_SUFFIX;
    }

    public String getStrImageURL() {
        return strImageURL;
    }

    public void setStrImageURL(String strImageURL) {
        changeHttpToHttps(strImageURL);
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        if (publisher.isEmpty()) {
            this.publisher += PUBLISHER_SUFFIX;
        } else {
            this.publisher += publisher;
        }
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        if (publishedDate.isEmpty()) {
            this.publishedDate += PUBLISHER_SUFFIX;
        } else {
            this.publishedDate += publishedDate;
        }
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        if (pageCount == 0) {
            this.pageCount += "unknown";
        } else {
            this.pageCount += pageCount;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.isEmpty()) {
            this.description = "Description not found";
        } else {
            this.description = description;
        }
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    // --- Private methods ---

    // This method converts a http link to a https link
    // This is needed in order to use Fresco to set the image. It won't work with a regular http link

    // TODO: change the regex to only change http to https if http appears at the beginning of the String
    // One problem with this is that it will replace 'http' with 'https' regardless of where 'http'
    // appears in the URL
    private void changeHttpToHttps(String strImageURL) {
        this.strImageURL = strImageURL.replaceFirst("http", "https");
        Log.d(TAG, "strImageURL: " + this.strImageURL);
    }
}
