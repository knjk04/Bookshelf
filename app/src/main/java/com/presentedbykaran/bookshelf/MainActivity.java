package com.presentedbykaran.bookshelf;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private SearchView mSearchView;
    private TextView mTitleTxt;
    private TextView mAuthorTxt;
//    private TextView mBookInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiKey = readAPIKey();
        Log.d(TAG, apiKey);

        String volumesWithTxt = "q=flowers";
        String author = "inauthor:keyes";

        String bookURL = "https://www.googleapis.com/books/v1/volumes?" + volumesWithTxt + "+" +
                author + "&key=" + apiKey;

        // OkHttp asynchronous GET recipe
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(bookURL)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        Log.v(TAG, response.body().string());
                    }
                } catch (IOException e) {
                    Log.e(TAG, "IO Exception caught: " , e);
                }
            }
        });
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
        return data;
    }
}