package com.presentedbykaran.bookshelf;

import android.app.SearchManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
//import com.squareup.picasso.Picasso;

/** Bookshelf.  Copyright (C). 2018.  Karan Kumar
  * This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
  * This is free software, and you are welcome to redistribute it
  * under certain conditions; type `show c' for details.
  *
  is licensed under GNU General Public License v3.0 only
*/

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
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