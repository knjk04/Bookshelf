package com.presentedbykaran.bookshelf;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;

import com.presentedbykaran.bookshelf.databinding.ActivitySearchResultsBinding;

import java.util.ArrayList;
import java.util.List;

/** Bookshelf.  Copyright (C). 2018.  Karan Kumar
  * This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
  * This is free software, and you are welcome to redistribute it
  * under certain conditions; type `show c' for details.
  *
  * This is licensed under GNU General Public License v3.0 only
*/

public class SearchResultsActivity extends Activity {

    private BookListAdapter bookListAdapter;
    private ActivitySearchResultsBinding activitySearchResultsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        final List<Book> bookList = (ArrayList<Book>) intent.getSerializableExtra("BookList");

        activitySearchResultsBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_search_results);

//        bookListAdapter = new BookListAdapter(getBookData(), this);

//        Fresco.initialize(this);

        bookListAdapter = new BookListAdapter(bookList, this);

        activitySearchResultsBinding.searchResultsRecycler.setAdapter(bookListAdapter);
        activitySearchResultsBinding.searchResultsRecycler.setLayoutManager(new
                LinearLayoutManager(this));

        bookListAdapter.setOnItemClickListener(new BookListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String title = bookList.get(position).getBookTitle();
//                Toast.makeText(SearchResultsActivity.this, title, Toast.LENGTH_SHORT).show();

//                ArrayList<Object> object = new ArrayList<Object>();
                ArrayList<Book> bookArrayList = new ArrayList<>(bookList.size());
                bookArrayList.addAll(bookList);

                Intent intent = new Intent(SearchResultsActivity.this, Preview.class);
                Bundle args = new Bundle();
                args.putSerializable("bookArrayList", bookArrayList);
                intent.putExtra("bundle", args);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }
}
