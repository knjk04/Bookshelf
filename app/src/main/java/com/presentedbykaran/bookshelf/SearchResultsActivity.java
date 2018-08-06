package com.presentedbykaran.bookshelf;

import android.app.SearchManager;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.app.Activity;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;

import com.presentedbykaran.bookshelf.databinding.ActivitySearchResultsBinding;
import com.presentedbykaran.bookshelf.databinding.SingleListRowBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchResultsActivity extends Activity {

    private BookListAdapter bookListAdapter;
    private ActivitySearchResultsBinding activitySearchResultsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_results);
//        getActionBar().setDisplayHomeAsUpEnabled(true);

//        Intent intent = getIntent();
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            executeSearch(query);
//        }

        activitySearchResultsBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_search_results);

        bookListAdapter = new BookListAdapter(getBookData(), this);

        activitySearchResultsBinding.searchResultsRecycler.setAdapter(bookListAdapter);
        activitySearchResultsBinding.searchResultsRecycler.setLayoutManager(new
                LinearLayoutManager(this));

    }

//    public Book(String bookTitle, List<String> authors, double rating, ImageView image) {

    private List<Book> getBookData() {
        List<Book> books = new ArrayList<>();


        Book book = new Book("Stardust", Arrays.asList("Neil Gaiman"), 4.8, null);
        books.add(book);
        book = new Book("The Subtle Knife", Arrays.asList("Phillip Pullman"), 4.5, null);
        books.add(book);
        book = new Book("Leonardo Da Vinci", Arrays.asList("Walter Isaacson"), 4.0, null);
        books.add(book);
        book = new Book("Mort", Arrays.asList("Terry Pratchett"), 4.0, null);
        books.add(book);
        book = new Book("Career of Evil", Arrays.asList("Robert Galbraith"), 4.7, null);
        books.add(book);
        book = new Book("Creativity, Inc.", Arrays.asList("Ed Catmull"), 4.1, null);
        books.add(book);
        book = new Book("The Hobbit", Arrays.asList("J.R.R Tolkien"), 4.0, null);
        books.add(book);
        book = new Book("The Great Gatsby", Arrays.asList("F Scott Fitzgerald"), 4.0, null);
        books.add(book);
        book = new Book("The Casual Vacancy", Arrays.asList("J.K Rowling"), 3.0, null);
        books.add(book);
        book = new Book("The Adventures of Sherlock Holmes", Arrays.asList("Sir Arthur " +
                "Conan Doyle"), 4.2, null);
        books.add(book);
//    private void executeSearch(String query) {
//    }

        return books;
    }
}
