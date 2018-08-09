package com.presentedbykaran.bookshelf;

import android.app.SearchManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.widget.ImageView;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        imageView = findViewById(R.id.imageView2);

//        Picasso.get().load("http://books.google.com/books/content?id=5QRZ4z6A1WwC&printsec=frontcover&img=1&zoom=5&edge=curl&imgtk=AFLRE73eezZyNKE1zd4jSv2IiUUlNwd-fE8KT9Hhaaaup-4Q_nsq2o5747MvQAVC00DQ6d-MAuJ7BRQ_jkhy8fUq19GagSS75c-al97XfLgX9iJyURE-qNLXJ7zh23LmOhAODCMM4sep&source=gbs_api.png").into(imageView);
        Picasso.get().load("https://i.imgur.com/6zDqjm8.jpg").into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(
                R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
}