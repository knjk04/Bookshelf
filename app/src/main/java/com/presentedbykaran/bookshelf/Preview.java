package com.presentedbykaran.bookshelf;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/** Bookshelf.  Copyright (C). 2018.  Karan Kumar
 *  This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 *  This is free software, and you are welcome to redistribute it
 *  under certain conditions; type `show c' for details.
 *
 *  This is licensed under GNU General Public License v3.0 only
 */

public class Preview extends AppCompatActivity {

    private static final String TAG = Preview.class.getSimpleName();

    @BindView(R.id.previewTitleTxt) TextView bookTitleTxt;
    @BindView(R.id.previewAuthorsTxt) TextView bookAuthorsTxt;
    @BindView(R.id.previewDrawee) SimpleDraweeView bookThumbnailDrawee;
    @BindView(R.id.previewDescriptionTxt) TextView bookDescriptionTxt;
    @BindView(R.id.previewPageCountTxt) TextView pageCountTxt;
    @BindView(R.id.previewPublisherTxt) TextView publisherTxt;
    @BindView(R.id.previewPublishedDateTxt) TextView publishedDateTxt;
    @BindView(R.id.previewRatingTxt) TextView ratingsTxt;
    @BindView(R.id.previewRatingCountTxt) TextView ratingCountTxt;
    @BindView(R.id.previewAddToBookshelfBtn) Button addToBookshelfBtn;

    boolean haveClicked;

    private String title;
    private String authors;
//    private String volumeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_preview);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("bundle");
        ArrayList<Book> bookArrayList = (ArrayList<Book>) args.getSerializable("bookArrayList");
        int position = intent.getIntExtra("position", -1);

        haveClicked = false;

        if (position != -1) {
            ButterKnife.bind(this);

            title = bookArrayList.get(position).getBookTitle();
            bookTitleTxt.setText(title);

            authors = bookArrayList.get(position).getAuthors();
            bookAuthorsTxt.setText(authors);

            bookThumbnailDrawee
                    .getHierarchy()
                    .setActualImageScaleType(
                        ScalingUtils.
                        ScaleType.CENTER_CROP);

            bookThumbnailDrawee.setImageURI(bookArrayList.get(position).getStrImageURL());
            bookDescriptionTxt.setText(bookArrayList.get(position).getDescription());
            pageCountTxt.setText(bookArrayList.get(position).getPageCount());
            publisherTxt.setText(bookArrayList.get(position).getPublisher());
            publishedDateTxt.setText(bookArrayList.get(position).getPublishedDate());
            ratingsTxt.setText(bookArrayList.get(position).getRating());
            ratingCountTxt.setText(bookArrayList.get(position).getRatingsCount());

        } else Log.e(TAG, "Error in retrieving position");
    }

    @OnClick(R.id.previewAddToBookshelfBtn)
    public void addToBookShelfOnClick(View view) {
        Snackbar snackbar = null;
        if (!haveClicked) {
            snackbar.make(view, "You clicked on add to bookshelf", Snackbar.LENGTH_SHORT)
                    .show();
            addToBookshelf();
            haveClicked = true;
        } else {
            snackbar.make(view, "You have already clicked on add to bookshelf",
                    Snackbar.LENGTH_SHORT).show();
        }
    }

    // Writes to internal storage
    private void addToBookshelf() {
        final String fileName = "my_bookshelf.json";
        FileOutputStream outputStream;

        List<String> jsonData = Arrays.asList(title, authors);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String gsonString = gson.toJson(jsonData);

        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(gsonString.getBytes());

            Toast.makeText(this, "Saved to " + getFilesDir(), Toast.LENGTH_SHORT).show();

            outputStream.close();
        } catch (Exception e) {
            Log.d(TAG, "Error: " + e);
            e.printStackTrace();
        }
    }
}
