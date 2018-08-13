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


public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        String urlStr = "https://books.google.com/books/content?id=F1wgqlNi8AMC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api";
        Uri uri = Uri.parse(urlStr);
        SimpleDraweeView draweeView = findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);

//        imageView = findViewById(R.id.imageView2);
//        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(imageView);

//        imageView = findViewById(R.id.imageView2);
//        String urlStr = "https://i.imgur.com/DvpvklR.png";
//        String urlStr = "https://books.google.com/books/content?id=kotPYEqx7kMC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api";
//        Uri uri = Uri.parse(urlStr);
//        Picasso.get().load(uri).into(imageView);

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