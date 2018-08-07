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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            searchQuery = intent.getStringExtra(SearchManager.QUERY);
            searchFor(searchQuery);
        }
    }

    private void searchFor(String query) {
        Toast.makeText(this, "You searched for: " + query, Toast.LENGTH_SHORT).show();

        String apiKey = readAPIKey();
//        String volumesWithTxt = "q=flowers";
//        String author = "inauthor:keyes";
//        String bookURL = "https://www.googleapis.com/books/v1/volumes?" + volumesWithTxt + "+" +
//                author + "&key=" + apiKey;

        String volumesWithTxt = "q=" + query;
        String bookURL = "https://www.googleapis.com/books/v1/volumes?" + volumesWithTxt + "+" +
                "&key=" + apiKey;

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

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) isAvailable = true;
        else {
//            Toast.makeText(this, getString(R.string.no_connection), Toast.LENGTH_LONG).show();
            Snackbar snackbar = Snackbar.make(findViewById(R.id.layout), R.string.no_connection,
                    Snackbar.LENGTH_LONG);
            snackbar.show();
        }
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
//        private void getBookDetails(String jsonData) throws JSONException {
        JSONObject details = new JSONObject(jsonData);
        JSONArray items = details.getJSONArray("items");

        Book[] books = new Book[items.length()];
//        books = new Book[items.length()];
//            mBooks = new Book[items.length()];

        for (int i = 0; i < books.length; i++) {
//            for (int i = 0; i < mBooks.length; i++) {
            JSONObject jsonBook = items.getJSONObject(i);
            JSONObject volumeInfo = jsonBook.getJSONObject("volumeInfo");

//            Book book = new Book();
            Book book = new Book(this);

            if (volumeInfo.has("title"))
                book.setBookTitle(volumeInfo.getString("title"));
            else Log.d(TAG, "No title for index " + i);

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
//            book.setRating(volumeInfo.getDouble("averageRating"));
            } else {
                Log.d(TAG, "No averageRating for index " + i);
                book.setRating(0);
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
//                mBooks[i] = book;
        }

//        Log.d(TAG, "Book title 1: " + books[0].getBookTitle());
//        Log.d(TAG, "Author of title 1: " + books[0].getAuthors(0));
//        Log.d(TAG, "Rating of title 1: " + books[0].getBookTitle());

//        mAuthor.setText(books[0].getBookTitle());
//        mTitle.setText(books[0].getAuthors(0));

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
                // There should be a better way than this
                while ((data = bufferedReader.readLine()) != null) {
                    break; // stores api key, so can now break
                }
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.d(TAG, "API key: " + data);

        return data;
    }

    public void executeSearch(View view) {
        if (searchQuery == null) return;

        List<Book> books = Arrays.asList(bookList.getBooks());
        Intent intent = new Intent(this, SearchResultsActivity.class);
        intent.putExtra("BookList", (Serializable) books);

        startActivity(intent);
    }
}
