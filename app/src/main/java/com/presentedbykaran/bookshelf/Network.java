package com.presentedbykaran.bookshelf;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by karan on 19/08/18.
 */

// Class for accessing and manipulating the API
// BookshelfActivity and SearchableActivity share a lot of the same networking code, so should
// inherit from this base class
public class Network {

    private boolean isAvailable;

//    private boolean isNetworkAvailable() {
//        ConnectivityManager manager = (ConnectivityManager) getSystemService(
//                Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
//
//        isAvailable = false;
//        if (networkInfo != null && networkInfo.isConnected()) {
//            isAvailable = true;
//        } else {
//            showNoInternetConnectionSnackbar();
//        }
//        return isAvailable;
//    }
//
//    private void raiseError() {
//        AlertDialogFragment dialogFragment = new AlertDialogFragment();
//        dialogFragment.show(getFragmentManager(), "error dialog");
//    }
}
