# Bookshelf
  <p align="center">
	<img src="/repoMedia/bookshelf_icon_512.png" alt="App icon"/>
  </p>

  
  Android app that uses the Google Books API to let you search for book titles 
  and/or authors, which you can save to a wishlist (WIP).
  
  Please note that the screenshots below are for demonstration purposes only. 
  The actual app may differ slightly in terms of design. In the event of a major
  design change, the screenshots will be updated.
  
  ![Screenshots](/repoMedia/all-screens-quarter-res.png)

  Currently, you can only search for book titles. The wishlist part of the app 
  is still under development (only possible to save the book title and authors for
  one book right now).
  
  Min SDK version: API level 21 (Android 5.0 Lollipop)
  
  This app has been tested on a Pixel XL running Android Pie (API 28).
  
# Features
  - Search for a book title and get a list of the top results showing the book
	title, the corresponding author, the rating of the book on Google Books and
	the number of ratings
	
  - Find out more information about a book (number of pages, publisher and published date)
	
  - Voice search
  
  And more on its way!
  
# Setup
  Add your [Google Books API](https://developers.google.com/books/docs/v1/getting_started) 
  key into a String in an enum called API in the default package.
  The String should be called key.
  
  E.g.
  ```java
  public enum API {
    ;
    public static final String key = "[your API key should go here]";
  }
  ```

# Third-party libraries used
  - [OkHttp](https://github.com/square/okhttp) by Square
  
  - [Fresco](https://github.com/facebook/fresco) by Facebook
  
  - [Butter Knife](https://github.com/JakeWharton/butterknife) by Jake Wharton
  
  - [Gson](https://github.com/google/gson) by Google

# Attributions
  - Search functionality powered by [Google Books API](https://developers.google.com/books/)

  - Icon made by [Freepik](https://www.flaticon.com/authors/freepik) from 
    www.flaticon.com

# License
[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

  Licensed under GPL-3.0 only. See [LICENSE](https://github.com/knjk04/Bookshelf/blob/master/LICENSE)
