package com.presentedbykaran.bookshelf;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.presentedbykaran.bookshelf.databinding.ActivitySearchResultsBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends Activity {

    private BookListAdapter bookListAdapter;
    private ActivitySearchResultsBinding activitySearchResultsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        List<Book> bookList = (ArrayList<Book>) intent.getSerializableExtra("BookList");

        activitySearchResultsBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_search_results);

//        bookListAdapter = new BookListAdapter(getBookData(), this);
        Fresco.initialize(this);
        bookListAdapter = new BookListAdapter(bookList, this);

        activitySearchResultsBinding.searchResultsRecycler.setAdapter(bookListAdapter);
        activitySearchResultsBinding.searchResultsRecycler.setLayoutManager(new
                LinearLayoutManager(this));

    }
}
