package com.presentedbykaran.bookshelf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookshelfActivity extends AppCompatActivity {

    private static final String TAG = BookshelfActivity.class.getSimpleName();
    private static final String FILE_NAME = "my_bookshelf.txt";

    @BindView(R.id.bookshelfTxt) TextView bookshelfText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookshelf);

        ButterKnife.bind(this);

        openFile();
    }

    private void openFile() {
//        File directory = this.getFilesDir();
//        File file =

        FileInputStream inputStream = null;

        try {
            inputStream = openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String text;

            while ((text = reader.readLine()) != null) {
                stringBuilder.append(text).append("\n");
            }

            Log.d(TAG, "Bookshelf Activity: " + stringBuilder.toString());
            bookshelfText.setText(stringBuilder.toString());

            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            openFileInput("bookArrayList");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
