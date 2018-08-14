package com.presentedbykaran.bookshelf;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by karan on 14/08/18.
 */
public class PreviewAdapter extends RecyclerView.Adapter<PreviewAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.previewTitle) TextView bookTitle;
        TextView bookTitle;
        TextView bookAuthors;
        SimpleDraweeView bookThumbnail;
        TextView bookDescription;
//        @BindView(R.id.previewAuthors) TextView bookAuthors;
//        @BindView(R.id.previewDrawee) SimpleDraweeView bookThumbnail;
//        @BindView(R.id.previewDescription) TextView bookDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.previewTitle);
            bookAuthors = itemView.findViewById(R.id.previewAuthors);
            bookThumbnail = itemView.findViewById(R.id.previewDrawee);
            bookDescription = itemView.findViewById(R.id.previewDescription);
        }
    }

    private ArrayList<Book> mBookArrayList;
    private int mPosition;
    public static final String TAG = PreviewAdapter.class.getSimpleName();

    public PreviewAdapter(ArrayList<Book> bookArrayList, int position) {
        mBookArrayList = bookArrayList;
        mPosition = position;
    }

    @NonNull
    @Override
    public PreviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        Include
//        ViewHolder vh = new ViewHolder(inc);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.preview1, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        String title = mBookArrayList.get(position).getBookTitle();
        Log.d(TAG, "title in onBindViewHolder(): " + title);

        viewHolder.bookTitle.setText(mBookArrayList.get(position).getBookTitle());
        viewHolder.bookAuthors.setText(mBookArrayList.get(position).getAuthors());
        viewHolder.bookThumbnail.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
        viewHolder.bookThumbnail.setImageURI(mBookArrayList.get(position).getStrImageURL());
        viewHolder.bookDescription.setText(mBookArrayList.get(position).getDescription());

//            bookTitle.setText(bookArrayList.get(position).getBookTitle());
//            bookAuthors.setText(bookArrayList.get(position).getAuthors());
//
//            bookThumbnail.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
//            bookThumbnail.setImageURI(bookArrayList.get(position).getStrImageURL());
//
//            bookDescription.setText(bookArrayList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return mBookArrayList.size();
    }
}
