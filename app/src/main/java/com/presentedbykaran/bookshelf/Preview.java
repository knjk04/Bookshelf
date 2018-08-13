package com.presentedbykaran.bookshelf;

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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Preview extends AppCompatActivity {

    private static final String TAG = Preview.class.getSimpleName();
    @BindView(R.id.previewTitle) TextView bookTitle;
    @BindView(R.id.previewAuthors) TextView bookAuthors;
    @BindView(R.id.previewDrawee) SimpleDraweeView bookThumbnail;
//    @BindView(R.id.previewAddToBookshelfBtn) Button addBtn;

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

        } else {
            Log.e(TAG, "Error in retrieving position");
        }
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
