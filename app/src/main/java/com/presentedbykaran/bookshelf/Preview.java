package com.presentedbykaran.bookshelf;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/** Bookshelf.  Copyright (C). 2018.  Karan Kumar
 * This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 * This is free software, and you are welcome to redistribute it
 * under certain conditions; type `show c' for details.
 *
 * This is licensed under GNU General Public License v3.0 only
 */

public class Preview extends AppCompatActivity {

    private static final String TAG = Preview.class.getSimpleName();

//    @BindView(R.id.previewTitle) TextView bookTitle;
//    @BindView(R.id.previewAuthors) TextView bookAuthors;
//    @BindView(R.id.previewDrawee) SimpleDraweeView bookThumbnail;
//    @BindView(R.id.previewDescription) TextView bookDescription;

//    @BindView(R.id.previewAddToBookshelfBtn) Button addBtn;

    boolean mHaveClicked;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_preview);
//        setContentView(R.layout.preview1);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("bundle");
        ArrayList<Book> bookArrayList = (ArrayList<Book>) args.getSerializable("bookArrayList");
        int position = intent.getIntExtra("position", -1);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

//        mAdapter = new PreviewAdapter();
        mAdapter = new PreviewAdapter(bookArrayList, position);
        mRecyclerView.setAdapter(mAdapter);

        mHaveClicked = false;

//        if (position != -1) {
//            ButterKnife.bind(this);
//
//            bookTitle.setText(bookArrayList.get(position).getBookTitle());
//            bookAuthors.setText(bookArrayList.get(position).getAuthors());
//
//            bookThumbnail.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
//            bookThumbnail.setImageURI(bookArrayList.get(position).getStrImageURL());
//
//            bookDescription.setText(bookArrayList.get(position).getDescription());
//
//        } else {
//            Log.e(TAG, "Error in retrieving position");
//        }
    }

    public void addToBookShelfOnClick(View view) {
        Snackbar snackbar = null;
        if (!mHaveClicked) {
            snackbar.make(view, "You clicked on add to bookshelf", Snackbar.LENGTH_SHORT).show();
//            Toast.makeText(this, "You clicked on add to bookshelf", Toast.LENGTH_SHORT).show();
            mHaveClicked = true;
        } else {
            snackbar.make(view, "You have already clicked on add to bookshelf", Snackbar.LENGTH_SHORT).show();
        }
    }
}
