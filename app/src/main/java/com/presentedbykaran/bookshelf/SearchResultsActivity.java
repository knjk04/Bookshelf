package com.presentedbykaran.bookshelf;

import android.os.Bundle;
import android.app.Activity;

public class SearchResultsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
