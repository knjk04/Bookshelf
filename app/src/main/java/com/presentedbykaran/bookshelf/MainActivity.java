package com.presentedbykaran.bookshelf;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

//    private SearchView mSearchView;
    private BookList bookList;
    private String searchQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);




//        search();

//        mAuthor = findViewById(R.id.authorText);
//        mTitle = findViewById(R.id.titleText);
//        imageView = findViewById(R.id.imageView);
//
//        mAuthor.setText(mBooks[0].getBookTitle());
//        mTitle.setText(mBooks[0].getAuthors(0));


//        mSearchView = findViewById(R.id.search);
//        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                searchQuery = query;
//                Log.d(TAG, "You searched for: " + searchQuery);
//                search(searchQuery);
//
////                List<Book> books = Arrays.asList(bookList.getBooks());
////                Intent intent = new Intent(MainActivity.this,
////                        SearchResultsActivity.class);
////                intent.putExtra("BookList", (Serializable) books);
////
////                startActivity(intent);
//
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                return false;
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.searchBtn:
                onSearchRequested();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


//    // On Click Listener
//    public void testRunActivity(View view) {
//        if (searchQuery == null) {
//            Log.d(TAG, "Search query is null");
//            Toast.makeText(this, "Please search for something", Toast.LENGTH_SHORT).show();
//            return;
//        } else {
//            List<Book> books = Arrays.asList(bookList.getBooks());
//            Intent intent = new Intent(MainActivity.this,
//                    SearchResultsActivity.class);
//            intent.putExtra("BookList", (Serializable) books);
//
//            startActivity(intent);
//        }
//    }

}