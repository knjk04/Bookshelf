Thank you for wanting to contribute! :smiley:

# Before contributing
- please inform us of the change you want to make before doing it. This way, we can ensure that people aren't working 
  on the same thing (we'll assign the task to you if it hasn't already been assigned).
- please create a new branch for your changes and submit a pull request.

If you're confused about some of the terminology we use, check out dedicated the Wiki page. If what you're looking for 
isn't there, raise an issue as a question and we'll get back to you.

# Style guide

Table of contents
1. [Introduction](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#1-introduction)
1. [Formatting](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#2-formatting)
    1. [Spaces around operators](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#21-spaces-around-operators)
    1. [Explicit operator precedence](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#22-explicit-operator-precedence)
1. [Naming conventions](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#3-naming-conventions)
    1. [Suffixes](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#311-suffixes)
    1. [Non-constant field names](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#non-constant-field-names)
1. [Good practices](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#41-final-where-possible)
    1. [final where possible](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#final-where-possible)
    1. [Use maintained/better libraries](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#42-use-maintainedbetter-libraries)
    1. Catch specific exceptions   
1. [Annotations](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#5-annotations)

## 1. Introduction
The style guide we follow is modelled around [Google's Java Style Guide](https://google.github.io/styleguide/javaguide.html).
Where we have added to it or our style guide differs, we have listed below. Instead of rehashing Google's style guide here,
if there is something in the Google style guide that does not clash with our style guide, you can assume we follow it.
If in doubt, get in contact with us by raising an issue.

If you see a place where the styleguide is violated, please do submit a pull request.

## 2. Formatting

### 2.1 Spaces around operators 
It's just a lot clearer and nicer to read. 

  - Acceptable:
  ```java
  this.pageCount += pageCount;
  ```
  - Not acceptable:
  ```java
  this.pageCount+=pageCount;
```

### 2.2 Explicit operator precedence
Even where obvious. use parenthesis to make the ordering explicit. It could save someone else who does not know 
the precedence rules from having to look it up.

```java
meaningfulVariableName = (2 * SECONDS_IN_MILLIS) + OFFSET; 
```

## 3. Naming conventions

### 3.1 Non-constant field names
- Member variables to be prefixed with ```m```, e.g.
```java
boolean mHaveClicked = false;
```
#### 3.1.1 Suffixes

- ```TextView```s to be suffixed with ```txt```
  - ```TextView mRatingsTxt; ```
  
- ```SimpleDraweeView```s to be suffixed with ```Drawee```
  - ```SimpleDraweeView mBookThumbnailDrawee; ```
  
- ```Button```s to be suffixed with ```btn```
  - ```Button goToBookshelfBtn;```
  
- ```Adapter```s to be suffixed with ```Adapter```
  - ```java
    private BookListAdapter bookListAdapter; 
    ```
    
- ```Layout```s to be suffixed with ```Layout```
  - ```android:id="@+id/constraintLayout"```

## 4. Good practices

### 4.1 final where possible
If something can be made final (e.g. constants), it should be made final.

### 4.2 Use maintained/better libraries
If a third-party library is not maintained, please avoid using it. 

If something is deprecated (third-party or not), please also avoid using it.

- E.g. RecyclerView over ListView

### 4.3 Catch specific exceptions
Instead of just catching Exception, if it is possible to catch (a) particular exception(s), do so.
Bad:
```java
try {
  // do something fishy
} catch (Exception e) { // lazy
  e.printStackTrace();
}
```

Good:
```java
try {
  // do something fishy
} catch (NullPointerException e) {
  e.printStackTrace();
}
```
## 5. Annotations
- Every annotation should be on its own, separate line
  - E.g. 
    ```java
    @NonNull
    @Override
    private void foo() { ... }
    ```
