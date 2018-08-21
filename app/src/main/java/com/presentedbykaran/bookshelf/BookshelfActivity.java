package com.presentedbykaran.bookshelf;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BookshelfActivity extends AppCompatActivity {

    private static final String TAG = BookshelfActivity.class.getSimpleName();
    private static final String FILE_NAME = "my_bookshelf.txt";

    //    private String selfLink;
    private String volumeId;
    private String title;
    private List<String> authorsList;

    @BindView(R.id.bookshelfTxt) TextView volumeIdTextView;
    @BindView(R.id.titleTxt) TextView titleTextView;
    @BindView(R.id.authorsTxt) TextView authorsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookshelf);

        ButterKnife.bind(this);

        openFile();

        if (!volumeId.isEmpty()) {
             search();
//             setViews();
        }
    }

    private void openFile() {
        FileInputStream inputStream;

        try {
            inputStream = openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String text;

            while ((text = reader.readLine()) != null) {
                stringBuilder.append(text).append("\n");
            }

            volumeId = stringBuilder.toString();
            Log.d(TAG, "Bookshelf Activity: " + volumeId);
            volumeIdTextView.setText(volumeIdTextView.getText() + volumeId);

            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void search() {

//        String apiURL = selfLink + "&key=" + API.KEY;
//        Log.d(TAG, "Api URL: " + apiURL);

        // Gets a single volume
        String volumeURL = "https://www.googleapis.com/books/v1/volumes/" + volumeId + "?key=" +
                API.KEY;


        if (isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
//                    .url(apiURL)
                    .url(volumeURL)
                    .build();

//            Log.d(TAG, "selfLink = " + selfLink);
            Log.d(TAG, "volume URL = " + volumeURL);

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(TAG, "In onFailure() in BookshelfActivity");
                }

                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
//                            bookList = parseBookListData(jsonData);
                            parseBookData(jsonData);
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
        ConnectivityManager manager = (ConnectivityManager) getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            showNoInternetConnectionSnackbar();
            return false;
        }
    }

    private void showNoInternetConnectionSnackbar() {
        Snackbar.make(findViewById(R.id.constraingLayoutBookshelf), R.string.no_connection,
                Snackbar.LENGTH_LONG).show();
    }

    // TODO: Use a different method than getFragmentManager() deprecated
    private void raiseError() {
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getFragmentManager(), "error dialog");
    }

    private void parseBookData(String jsonData) throws JSONException {
        JSONObject details = new JSONObject(jsonData);
        JSONObject volumeInfo = details.getJSONObject("volumeInfo");

        Book book = new Book(this);

        if (volumeInfo.has("title")) {
//            title = volumeInfo.getString("title");
            book.setBookTitle(volumeInfo.getString("title"));
            Log.d(TAG, "There is a title: " + title);
        } else {
            Log.e(TAG,"No title in parseBookData()");
//            title = "No title found";
            book.setBookTitle(volumeInfo.getString(""));
        }

        if (volumeInfo.has("authors")) {
            JSONArray jsonAuthors = volumeInfo.getJSONArray("authors");
            String[] authors = new String[jsonAuthors.length()];

            for (int i = 0; i < authors.length; i++) {
                authors[i] = jsonAuthors.getString(i);
            }

            authorsList = Arrays.asList(authors);

        } else {
//            authorsList = Arrays.asList("No authors found");
            authorsList = Arrays.asList(null);
        }
    }

//    private void setViews() {
////        titleTextView.setText(title);
//        if (title != null && !title.isEmpty()) {
//            titleTextView.setText(title);
//        }
////        else {
////            Log.e(TAG, "setViews(): title is null");
////            titleTextView.setText("No title found");
////        }
//
//        String authors = "";
//        if (authorsList != null && !authorsList.isEmpty()) {
//            for (int i = 0; i < authorsList.size(); i++) {
//                if (i == 0) {
//                    authors = authorsList.get(i);
//                }
//                else {
//                    authors += ", " + authorsList.get(i);
//                }
//            }
//        } else {
////            Log.e(TAG, "authors is null");
//            authors = "No authors found";
//        }
//        authorsTextView.setText(authors);
//    }
}