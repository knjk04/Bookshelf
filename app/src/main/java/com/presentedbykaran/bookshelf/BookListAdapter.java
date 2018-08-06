package com.presentedbykaran.bookshelf;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.presentedbykaran.bookshelf.databinding.SingleListRowBinding;

import java.util.List;

/**
 * Created by karan on 06/08/18.
 */
public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    private List<Book> bookList;
    private Context mContext;

    public BookListAdapter(List<Book> bookList, Context mContext) {
        this.bookList = bookList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public BookListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        SingleListRowBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.single_list_row,
                        viewGroup,
                        false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Book book = bookList.get(i);
        viewHolder.singleListRowBinding.setBook(book);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public SingleListRowBinding singleListRowBinding;

        public ViewHolder(SingleListRowBinding singleRowLayoutBinding) {
            super((singleRowLayoutBinding).getRoot());
            singleListRowBinding = singleRowLayoutBinding;
        }
    }
}
