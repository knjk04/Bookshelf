package com.presentedbykaran.bookshelf;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.presentedbykaran.bookshelf.data.Book;
import com.presentedbykaran.bookshelf.databinding.SingleListRowBinding;

import java.util.List;

/**
 * Created by karan on 06/08/18.
 *
 * Bookshelf.  Copyright (C). 2018.  Karan Kumar
 * This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 * This is free software, and you are welcome to redistribute it
 * under certain conditions; type `show c' for details.
 *
 * This is licensed under GNU General Public License v3.0 only
 */
public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private List<Book> bookList;
//    private ArrayList<Book> mBookArrayList;
    private Context mContext;
    public static final String TAG = BookListAdapter.class.getSimpleName();

    public BookListAdapter(List<Book> bookList, Context mContext) {
        this.bookList = bookList;
        this.mContext = mContext;

//        mBookArrayList = new ArrayList<>(bookList.size());
//        mBookArrayList.addAll(bookList);

    }

//    public BookListAdapter(ArrayList<Book> bookList, Context mContext) {
//        mBookArrayList = bookList;
//        this.mContext = mContext;
//    }

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
//        View view = inflater.inflate(R.layout.activity_search_results, viewGroup, false);
        View view = inflater.inflate(R.layout.single_list_row, viewGroup, false);


        return new ViewHolder(binding, view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Book book = bookList.get(i);
//        Book book = mBookArrayList.get(i);
        viewHolder.singleListRowBinding.setBook(book);

        Log.d(TAG, "Image URL: " + book.getStrImageURL());

        if (book.getStrImageURL() != null) {
            String sampleUrl = "https://i.imgur.com/tGbaZCY.jpg%22";

            Log.d(TAG, "image url: " + book.getStrImageURL());
            viewHolder.draweeView.setImageURI(bookList.get(i).getStrImageURL());


//            final ImageRequest imageRequest =
////                    ImageRequestBuilder.newBuilderWithSource(Uri.parse(book.getStrImageURL()))
//                    ImageRequestBuilder.newBuilderWithSource(Uri.parse(sampleUrl))
//                            .build();
//            viewHolder.draweeView.setImageRequest(imageRequest);

//            Picasso.get()
//                   .load(book.getStrImageURL())
//                    .load(sampleUrl)
//                   .placeholder(R.drawable.search_white_24dp)
//                   .into(viewHolder.imageView);

//            Glide.with(viewHolder.imageView)
//                    .load(sampleUrl)
//                    .into(viewHolder.imageView);



            //viewHolder.draweeView.setImageURI(book.getStrImageURL());
            //viewHolder.draweeView.setImageURI(sampleUrl);

            //Uri uri = Uri.parse(book.getStrImageURL());
            //viewHolder.draweeView.setImageURI(uri);

//            viewHolder.draweeView.setImageURI(bookList.get(i).getStrImageURL());

//            Uri uri = Uri.parse(book.getStrImageURL());
////            Uri uri = Uri.parse("https://i.imgur.com/tGbaZCY.jpg%22");
//            ImageRequest request = ImageRequest.fromUri(uri);
//            DraweeController controller = Fresco.newDraweeControllerBuilder().setImageRequest(request).setOldController( viewHolder.draweeView.getController()).build();
//            viewHolder.draweeView.setController(controller);

            Log.d(TAG, "Book image URL not null");
        }
    }

    @Override
    public int getItemCount() {
//        return mBookArrayList.size();
        return bookList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public SingleListRowBinding singleListRowBinding;

        private final SimpleDraweeView draweeView;
//        private final ImageView imageView;

//        public ImageView imageView;

//        public ViewHolder(SingleListRowBinding singleRowLayoutBinding) {
//            super((singleRowLayoutBinding).getRoot());
//            singleListRowBinding = singleRowLayoutBinding;
//        }

        public ViewHolder(SingleListRowBinding singleRowLayoutBinding, View view,
                          final OnItemClickListener listener) {
            super((singleRowLayoutBinding).getRoot());
            singleListRowBinding = singleRowLayoutBinding;

//            Fresco.initialize(mContext);
            draweeView = view.findViewById(R.id.bookCoverDrawee);

//            imageView = view.findViewById(R.id.recyclerViewImage);

//            imageView = view.findViewById(R.id.bookCoverImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
