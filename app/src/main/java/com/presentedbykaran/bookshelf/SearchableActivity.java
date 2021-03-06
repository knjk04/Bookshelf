package com.presentedbykaran.bookshelf;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    private static final String TAG = SearchableActivity.class.getSimpleName();
    private BookList bookList;
    private String searchQuery;
    private boolean isAvailable = false;

    @BindView(R.id.searchableBtn) Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            searchQuery = intent.getStringExtra(SearchManager.QUERY);

            if (isNetworkAvailable()) {
                searchFor(searchQuery);
            }
        }
    }

    @OnClick(R.id.searchableBtn)
    public void executeSearch(View view) {
        // It shouldn't be since you have to search for something to get here, but just in case
        if (searchQuery == null) {
            Log.d(TAG, "Search query was null");
            return;
        }

        if (!isAvailable) {
            showNoInternetConnectionSnackbar();
            return;
        }

        List<Book> books = Arrays.asList(bookList.getBooks());
        Intent intent = new Intent(this, SearchResultsActivity.class);
        intent.putExtra("BookList", (Serializable) books);

        startActivity(intent);
    }

    private void searchFor(String query) {
        Toast.makeText(this, "Fetching data. Please wait", Toast.LENGTH_SHORT).show();

//        String author = "inauthor:keyes";

//        String apiKey = API.KEY;
//        Log.d(TAG, "Key in API enum: " + apiKey);

        String volumesWithTxt = "q=" + query;
        String bookURL = "https://www.googleapis.com/books/v1/volumes?" + volumesWithTxt + "+" +
                "&key=" + API.KEY;

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

    // Even though the style guide states that method names should be verbs, it makes
    // more sense if this method name is a noun phrase since it is called in an if statement
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        } else {
            showNoInternetConnectionSnackbar();
        }
        return isAvailable;
    }

    // TODO: Use a different method than getFragmentManager() deprecated
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

            book.setBookTitle(getJSONString(volumeInfo, "title"));

            if (volumeInfo.has("authors")) {
                JSONArray jsonAuthors = volumeInfo.getJSONArray("authors");
                String[] arrAuthors = new String[jsonAuthors.length()];

                for (int j = 0; j < jsonAuthors.length(); j++) {
                    arrAuthors[j] = jsonAuthors.getString(j);
                }

                book.setAuthors(Arrays.asList(arrAuthors));
            } else {
//                book.setAuthors(Arrays.asList(""));
                book.setAuthors(null);
            }

            if (volumeInfo.has("averageRating")) {
                book.setRating(volumeInfo.getDouble("averageRating"));
            } else {
                book.setRating(0);
            }

            book.setRatingsCount(getJSONInt(volumeInfo, "ratingsCount"));
            book.setPublisher(getJSONString(volumeInfo, "publisher"));
            book.setPublishedDate(getJSONString(volumeInfo, "publishedDate"));
            book.setPageCount(getJSONInt(volumeInfo, "pageCount"));
            book.setDescription(getJSONString(volumeInfo, "description"));

            // Do not require an else clause since there is a placeholder
            if (volumeInfo.has("imageLinks")) {
                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                book.setStrImageURL(imageLinks.getString("smallThumbnail"));
            }

//            book.setSelfLink(getJSONString(jsonBook, "selfLink"));
            book.setVolumeId(getJSONString(jsonBook, "id"));

            books[i] = book;
        }
        return books;
    }

    // Returns the empty string if the property does not exist to let the Book class handle the case
    // where it does not exist
    private String getJSONString(JSONObject jsonObject, String property) throws JSONException {
        return (jsonObject.has(property)) ? jsonObject.getString(property) : "";
    }

    // Returns 0 if the property does not exist since the Book class handles what to do if the
    // property does not exist
    private int getJSONInt(JSONObject jsonObject, String property) throws JSONException {
        return (jsonObject.has(property)) ? jsonObject.getInt(property) : 0;
    }

    private void showNoInternetConnectionSnackbar() {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.searchableLayout),
                R.string.no_connection, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

}
