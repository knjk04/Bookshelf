package com.presentedbykaran.bookshelf;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.presentedbykaran.bookshelf.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/** Bookshelf.  Copyright (C). 2018.  Karan Kumar
 * This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 * This is free software, and you are welcome to redistribute it
 * under certain conditions; type `show c' for details.
 *
 * This is licensed under GNU General Public License v3.0 only
 */

public class SearchableActivity extends AppCompatActivity {
    public static final String TAG = SearchableActivity.class.getSimpleName();
    private BookList bookList;
    private String searchQuery;
    private boolean isAvailable = false;

//    SimpleDraweeView draweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Fresco.initialize(this);

        setContentView(R.layout.activity_searchable);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            searchQuery = intent.getStringExtra(SearchManager.QUERY);

            if (isNetworkAvailable()) searchFor(searchQuery);
        }

//        draweeView = findViewById(R.id.bookCoverDrawee);
    }

    private void searchFor(String query) {
        Toast.makeText(this, "You searched for: " + query, Toast.LENGTH_SHORT).show();

//        String apiKey = readAPIKey();
//        String author = "inauthor:keyes";

        String apiKey = API.key;
        Log.d(TAG, "Key in API enum: " + apiKey);

        String volumesWithTxt = "q=" + query;
        String bookURL = "https://www.googleapis.com/books/v1/volumes?" + volumesWithTxt + "+" +
                "&key=" + apiKey;

//        Log.d(TAG, bookURL);

        if (isNetworkAvailable()) {

            // OkHttp asynchronous GET recipe
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(bookURL)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(TAG, "In onFailure()");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
//                            getBookDetails(jsonData);
//                            mBooks = getBookDetails(jsonData);
                            bookList = parseBookListData(jsonData);
                        } else {
                            raiseError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "IO Exception caught: ", e);
                    } catch (JSONException e) {
                        Log.e(TAG, "JSON Exception caught:", e);
                    }
                }
            });
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) isAvailable = true;
        else noInternetConnectionSnackbar();
        return isAvailable;
    }

    private void raiseError() {
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getFragmentManager(), "error dialog");
    }

    private BookList parseBookListData(String jsonData) throws JSONException {
        BookList bookList = new BookList();
        bookList.setBooks(getBookDetails(jsonData));
        return bookList;
    }

    // TODO: refactor method to shorten in. It's rather long
    private Book[] getBookDetails(String jsonData) throws JSONException {
        JSONObject details = new JSONObject(jsonData);
        JSONArray items = details.getJSONArray("items");

        Book[] books = new Book[items.length()];

        for (int i = 0; i < books.length; i++) {
            JSONObject jsonBook = items.getJSONObject(i);
            JSONObject volumeInfo = jsonBook.getJSONObject("volumeInfo");

            Book book = new Book(this);

            if (volumeInfo.has("title"))
                book.setBookTitle(volumeInfo.getString("title"));
            else {
                // Do not set a generic title. There should really be a title at the very least
                Log.d(TAG, "No title for index " + i);
            }

            if (volumeInfo.has("authors")) {
                JSONArray jsonAuthors = volumeInfo.getJSONArray("authors");
                String[] arrAuthors = new String[jsonAuthors.length()];
                for (int j = 0; j < jsonAuthors.length(); j++)
                    arrAuthors[j] = jsonAuthors.getString(j);
                book.setAuthors(Arrays.asList(arrAuthors));
            } else {
                // if there isn't an authors property, let the Book class handle the logic
                book.setAuthors(Arrays.asList(""));
//                book.setAuthors(Arrays.asList("No authors found"));
                Log.d(TAG, "No authors");
            }

            // Ratings don't work for some reason. It can't find averageRating, so exception thrown
            if (volumeInfo.has("averageRating")) {
                double rating = volumeInfo.getDouble("averageRating");
                book.setRating(rating);
            } else {
                Log.d(TAG, "No averageRating for index " + i);
                book.setRating(0);
            }

            if (volumeInfo.has("ratingsCount")) {
                int ratingsCount = volumeInfo.getInt("ratingsCount");
                book.setRatingsCount(ratingsCount);
            } else {
                Log.d(TAG,"No ratingsCount for index " + i);
                book.setRatingsCount(0);
            }

            if (volumeInfo.has("publisher")) {
                String publisher = volumeInfo.getString("publisher");
                book.setPublisher(publisher);
            } else {
                Log.d(TAG,"No publisher for index " + i);
                // if there isn't a publisher property, let the Book class handle the logic
                book.setPublisher("");
            }

            if (volumeInfo.has("publishedDate")) {
                String publishedDate = volumeInfo.getString("publishedDate");
                book.setPublishedDate(publishedDate);
            } else {
                Log.d(TAG,"No published date for index " + i);
                // if there isn't a published date property, let the Book class handle the logic
                book.setPublishedDate("");
            }

            if (volumeInfo.has("pageCount")) {
                int pageCount = volumeInfo.getInt("pageCount");
                book.setPageCount(pageCount);
            } else {
                Log.d(TAG,"No publisher for index " + i);
                // if there isn't a page count property, set the page count to 0 and let the Book
                // class handle what to set the data as
                book.setPageCount(0);
            }

            if (volumeInfo.has("description")) {
                String description = volumeInfo.getString("description");
                book.setDescription(description);
            } else {
                Log.d(TAG,"No description for index " + i);
                // if there isn't a description property, let the Book class handle the logic
                book.setDescription("");
            }


            if (volumeInfo.has("imageLinks")) {
                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                String strSmallThumbnailURL = imageLinks.getString("smallThumbnail");
                book.setStrImageURL(strSmallThumbnailURL);

//                Uri uri = Uri.parse(strSmallThumbnailURL);
//                SimpleDraweeView draweeView = findViewById(R.id.bookCoverDrawee);
//                draweeView.setImageURI(uri);
            }

            books[i] = book;
        }
        return books;
    }

    private void noInternetConnectionSnackbar() {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.searchableLayout),
                R.string.no_connection, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void executeSearch(View view) {
        // It shouldn't be since you have to search for something to get here, but just in case
        if (searchQuery == null) {
            Log.d(TAG, "Search query was null");
            return;
        }

        if (!isAvailable) {
            noInternetConnectionSnackbar();
            return;
        }

        List<Book> books = Arrays.asList(bookList.getBooks());
        Intent intent = new Intent(this, SearchResultsActivity.class);
        intent.putExtra("BookList", (Serializable) books);

        startActivity(intent);
    }
}
