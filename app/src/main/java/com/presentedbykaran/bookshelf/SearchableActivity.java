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

public class SearchableActivity extends AppCompatActivity {
    public static final String TAG = SearchableActivity.class.getSimpleName();
    private BookList bookList;
    private String searchQuery;
    private boolean isAvailable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            searchQuery = intent.getStringExtra(SearchManager.QUERY);

            if (isNetworkAvailable()) searchFor(searchQuery);
        }
    }

    private void searchFor(String query) {
        Toast.makeText(this, "You searched for: " + query, Toast.LENGTH_SHORT).show();

        String apiKey = readAPIKey();
//        String author = "inauthor:keyes";

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
                book.setAuthors(Arrays.asList("No authors found"));
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


            Uri thumbnailUri;
            if (volumeInfo.has("imageLinks")) {
//            String imageThumbnail = volumeInfo.getString("image")
                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
//                imageView.setImageURI(Uri.parse(imageLinks.getString("smallThumbnail")));
                thumbnailUri = Uri.parse(imageLinks.getString("smallThumbnail"));
//                book.setImage(thumbnailUri);
            } else {
                Log.d(TAG, "No imageLinks for index " + i);
                thumbnailUri = null;
            }
//            book.setImage(thumbnailUri);

            books[i] = book;
        }
        return books;
    }

    // This method is for reading the API key and should be listed in the gitignore file.
    // This allows you to store your secret API key safely
    private String readAPIKey() {
        String data = "";
        InputStream inputStream = this.getResources().openRawResource(R.raw.api_key);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        if (inputStream != null) {
            try {
                // There should be a better way than this: do nothing if it isn't null since we only
                // need the first line and if the file has a line, it is stored in data
                if ((data = bufferedReader.readLine()) != null) ;
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        Log.d(TAG, "API key: " + data);

        return data;
    }

    private void noInternetConnectionSnackbar() {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.searchableLayout),
                R.string.no_connection, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void executeSearch(View view) {
        // It shouldn't be since you have to search for something to get here, but just in case
        if (searchQuery == null) return;

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
