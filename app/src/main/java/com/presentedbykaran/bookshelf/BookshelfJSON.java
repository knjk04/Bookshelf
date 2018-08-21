package com.presentedbykaran.bookshelf;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by karan on 20/08/18.
 *  Bookshelf.  Copyright (C). 2018.  Karan Kumar
 *  This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 *  This is free software, and you are welcome to redistribute it
 *  under certain conditions; type `show c' for details.
 *
 *  This is licensed under GNU General Public License v3.0 only
 */

// Class to store the JSON data for a book saved to the bookshelf
public class BookshelfJSON extends JSONObject {

    public JSONObject create (String title, String authors) {
        JSONObject object = new JSONObject();

        try {
            object.put("title", title);
            object.put("authors", authors);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }
}
