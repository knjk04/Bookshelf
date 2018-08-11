# Bookshelf
  <p align="center">
	<img src="/repoMedia/bookshelf_icon_512.png" alt="App icon"/>
  </p>
  
  [![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
  
  Android app that uses the Google Books API to let you search for book titles 
  and/or authors, which you can save to a wishlist (WIP)

  Currently, you can only search for book titles. The wishlist part of the app 
  hasn't been developed yet
  
  Min SDK version: API level 21 (Android 5.0 Lollipop)
  
  This app has been tested on a Pixel XL running Android Pie (API 28).
  
# Features
  - Search for a book title and get a list of the top results showing the book
	title, the corresponding author, the rating of the book on Google Books and
	the number of ratings
	
  - Voice search
  
  And more on its way!
  
# Setup
  Add your Google Books API key into /app/src/main/res/raw/api_key.txt (it was 
  added to the .gitignore of this project, so you will need to create your own
  raw directory and your own api_key.txt file).
  
  The file should only contain your API key without quotes or any blank lines.

# Libraries used
  - [OkHttp](https://github.com/square/okhttp) by Square
  
  - [Fresco](https://github.com/facebook/fresco) by Facebook

# Attributions
  - Search functionality powered by [Google Books API](https://developers.google.com/books/)

  - Icon made by [Freepik](https://www.flaticon.com/authors/freepik) from 
    www.flaticon.com
