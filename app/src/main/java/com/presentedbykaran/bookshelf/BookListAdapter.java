package com.presentedbykaran.bookshelf;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.presentedbykaran.bookshelf.databinding.SingleListRowBinding;


import java.util.List;

/**
 * Created by karan on 06/08/18.
 */
public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    private List<Book> bookList;
    private Context mContext;
    public static final String TAG = BookListAdapter.class.getSimpleName();

    public BookListAdapter(List<Book> bookList, Context mContext) {
        this.bookList = bookList;
        this.mContext = mContext;

//        Fresco.initialize(mContext);
    }

//    @NonNull
//    @Override
//    public BookListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        SingleListRowBinding binding = DataBindingUtil
//                .inflate(LayoutInflater.from(viewGroup.getContext()),
//                        R.layout.single_list_row,
//                        viewGroup,
//                        false);
//
//        return new ViewHolder(binding);
//    }

    @NonNull
    @Override
    public BookListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        SingleListRowBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.single_list_row,
                        viewGroup,
                        false);

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.activity_search_results, viewGroup, false);


        return new ViewHolder(binding, view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Book book = bookList.get(i);
        viewHolder.singleListRowBinding.setBook(book);

        Log.d(TAG, "Image URL: " + book.getStrImageURL());
        Uri uri = Uri.parse(book.getStrImageURL());

//        if (viewHolder.draweeView == null) {
//            Log.d(TAG, "draweeview is null for index " + i);
//        }
//        else
            viewHolder.draweeView.setImageURI(uri);

//        String urlStr = "https://books.google.com/books/content?id=F1wgqlNi8AMC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api";
//        Uri uri = Uri.parse(urlStr);
//        SimpleDraweeView draweeView = findViewById(R.id.my_image_view);
//        draweeView.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public SingleListRowBinding singleListRowBinding;
        public SimpleDraweeView draweeView;
//        public ImageView imageView;

//        public ViewHolder(SingleListRowBinding singleRowLayoutBinding) {
//            super((singleRowLayoutBinding).getRoot());
//            singleListRowBinding = singleRowLayoutBinding;
//        }

        public ViewHolder(SingleListRowBinding singleRowLayoutBinding, View view) {
            super((singleRowLayoutBinding).getRoot());
            singleListRowBinding = singleRowLayoutBinding;

//            Fresco.initialize(mContext);
            draweeView = view.findViewById(R.id.bookCoverDrawee);
        }
    }
}
