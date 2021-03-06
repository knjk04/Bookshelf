package com.presentedbykaran.bookshelf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookshelfActivity extends AppCompatActivity {

    private static final String TAG = BookshelfActivity.class.getSimpleName();
    private static final String FILE_NAME = "my_bookshelf.json";

    //    private String selfLink;
//    private String title;
//    private List<String> authorsList;

    @BindView(R.id.titleTxt) TextView titleTextView;
    @BindView(R.id.authorsTxt) TextView authorsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookshelf);

        ButterKnife.bind(this);

        openFile();
    }

    private void openFile() {
        try {
            FileInputStream fileInputStream = openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            String json = stringBuilder.toString();
            Gson gson = new Gson();
            List<String> list = gson.fromJson(json, List.class);
//            Log.d(TAG, "Reached here. All is well.");
            titleTextView.setText(list.get(0));
            authorsTextView.setText(list.get(1));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
