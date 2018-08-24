package com.presentedbykaran.bookshelf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;

/** Bookshelf.  Copyright (C). 2018.  Karan Kumar
 *  This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 *  This is free software, and you are welcome to redistribute it
 *  under certain conditions; type `show c' for details.
 *
 *  This is licensed under GNU General Public License v3.0 only
 */

public class BookshelfActivity extends AppCompatActivity {

    private static final String TAG = BookshelfActivity.class.getSimpleName();
    private static final String FILE_NAME = "my_bookshelf.json";

    @BindView(R.id.titleTxt) TextView titleTextView;
    @BindView(R.id.authorsTxt) TextView authorsTextView;
    @BindView(R.id.dateTxt) TextView dateTextView;
    @BindView(R.id.thumbnailDrawee) SimpleDraweeView thumbnailDrawee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookshelf);

        Fresco.initialize(this);
        ButterKnife.bind(this);
        thumbnailDrawee
                .getHierarchy()
                .setActualImageScaleType(ScalingUtils
                        .ScaleType
                        .CENTER_CROP);

        openFile();
    }

    private void openFile() {
        String jsonString = readFromFile();
        Log.d(TAG, "jsonString: " + jsonString);

        try {
//            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject = new JSONObject(jsonString);

            authorsTextView.setText(jsonObject.getString("authors"));
            titleTextView.setText(jsonObject.getString("bookTitle"));
            thumbnailDrawee.setImageURI(jsonObject.getString("strImageURL"));
            dateTextView.setText(jsonObject.getString("dateAdded"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String readFromFile() {
        String data = "";

        try {
            InputStream inputStream= openFileInput(FILE_NAME);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                StringBuilder stringBuilder = new StringBuilder();

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                inputStream.close();
                data = stringBuilder.toString();
            }

        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            Log.e(TAG, "File not found: " + e.toString());
        } catch (IOException e) {
//            e.printStackTrace();
            Log.e(TAG, "Cannot read file: " + e.toString());
        }

        return data;
    }
}
