package com.presentedbykaran.bookshelf;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/** Bookshelf.  Copyright (C). 2018.  Karan Kumar
 *  This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 *  This is free software, and you are welcome to redistribute it
 *  under certain conditions; type `show c' for details.
 *
 *  This is licensed under GNU General Public License v3.0 only
 */

public class Preview extends AppCompatActivity {

    private static final String TAG = Preview.class.getSimpleName();

    @BindView(R.id.previewTitle) TextView bookTitle;
    @BindView(R.id.previewAuthors) TextView bookAuthors;
    @BindView(R.id.previewDrawee) SimpleDraweeView bookThumbnail;
    @BindView(R.id.previewDescription) TextView bookDescription;

    @BindView(R.id.previewPageCountTxt) TextView pageCount;
    @BindView(R.id.previewPublisherTxt) TextView publisher;
    @BindView(R.id.previewPublishedDateTxt) TextView publishedDate;

    @BindView(R.id.previewRatingTxt) TextView ratings;
    @BindView(R.id.previewRatingCountTxt) TextView ratingCount;

    boolean mHaveClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_preview);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("bundle");
        ArrayList<Book> bookArrayList = (ArrayList<Book>) args.getSerializable("bookArrayList");
        int position = intent.getIntExtra("position", -1);

        mHaveClicked = false;

        if (position != -1) {
            ButterKnife.bind(this);
            bookTitle.setText(bookArrayList.get(position).getBookTitle());
            bookAuthors.setText(bookArrayList.get(position).getAuthors());

            bookThumbnail.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
            bookThumbnail.setImageURI(bookArrayList.get(position).getStrImageURL());

            bookDescription.setText(bookArrayList.get(position).getDescription());

            pageCount.setText(bookArrayList.get(position).getPageCount());

            publisher.setText(bookArrayList.get(position).getPublisher());
            publishedDate.setText(bookArrayList.get(position).getPublishedDate());

            ratings.setText(bookArrayList.get(position).getRating());
            ratingCount.setText(bookArrayList.get(position).getRatingsCount());

        } else Log.e(TAG, "Error in retrieving position");
    }

    public void addToBookShelfOnClick(View view) {
        Snackbar snackbar = null;
        if (!mHaveClicked) {
            snackbar.make(view, "You clicked on add to bookshelf", Snackbar.LENGTH_SHORT).show();
            mHaveClicked = true;
        } else {
            snackbar.make(view, "You have already clicked on add to bookshelf", Snackbar.LENGTH_SHORT).show();
        }
    }
}
