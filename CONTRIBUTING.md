Thank you for wanting to contribute! :smiley:

# Before contributing
- Please inform us of the change you want to make before doing it. This way, we can ensure that people aren't working 
  on the same thing (we'll assign the task to you if it hasn't already been assigned)
  
- Please create a new branch for your changes and submit a pull request

If you're confused about some of the terminology we use, check out our dedicated [Terminology wiki page](https://github.com/knjk04/Bookshelf/wiki/Terminology). 
If what you're looking for isn't there or you're unsure about something, raise an issue as a question and 
we'll get back to you.

# Style guide

Table of contents
1. [Introduction](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#1-introduction)
1. [File organisation](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#2-file-organisation)
    1. [Import statements](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#21-import-statements)
        1. [No wildcard imports](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#211-no-wildcard-imports)
        1. [Remove unused imports](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#212-remove-unused-imports)        
1. [Formatting](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#2-formatting)
    1. [Braces](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#31-braces)
        1. [Optional braces](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#311-optional-braces)
        1. [K & R style nonempty blocks](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#312-k--r-style-nonempty-blocks)
        1, [Empty blocks style](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#313-empty-blocks-style)
    1. [Spaces around operators](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#21-spaces-around-operators)
    1. [Explicit operator precedence](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#22-explicit-operator-precedence)
    1. [One statement on every line line only](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#316-one-statement-on-every-line-only)
    1. [Column limit: 100](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#317-column-limit-100)
1. [Naming conventions](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#3-naming-conventions)
    1. [Non-constant field names](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#non-constant-field-names)
    1. [Suffixes](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#311-suffixes)
1. [Good practices](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#41-final-where-possible)
    1. [final where possible](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#final-where-possible)
    1. [Use maintained/better libraries](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#42-use-maintainedbetter-libraries)
    1. [Minimise variable scope](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#53-minimise-variable-scope)
    1. [Catch specific exceptions](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#43-catch-specific-exceptions)
1. [XML style](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#6-xml-style)
1. [Annotations](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#5-annotations)

## 1. Introduction
The style guide we follow is modelled around [Google's Java Style Guide](https://google.github.io/styleguide/javaguide.html).
**Please refer to our style guide instead of Google's if you want to contribute to this repository.**

If you see a place where the styleguide is violated, please do submit a pull request.

If in doubt, get in contact with us by raising an issue.

## 2. File organisation

### 2.1 Import statements

### 2.1.1 No wildcard imports
Please do not use wildcard imports on this repository, even if you need to import several times from a particular package.

### 2.1.2 Remove unused imports
Help keep our files tidy by removing unused imports. 

If an import is **commented out**, please do not remove it. Instead, we request that you raise an issue to ask if it is needed. Raising an issue could be really helpul. We might just have forgotten about it.

## 3 Formatting

### 3.1 Braces

#### 3.1.1 Optional braces
Braces are always used with ```if/else```, ```for```, ```while``` and ```do``` statements, even when the body
consists of no statements or one statement.

#### 3.1.2 K & R style nonempty blocks
Braces use the Kernighan and Ritchie style for nonempty blocks or block-like constructs.

#### 3.1.3 Empty blocks style
An empty block or block-like construct can be in K & R style (see section 3.1.2). 

Another option is for the brace to be closed after it has been opened with no characters or line breaks in between 
(```{}```), except when it is part of a multi-block statement -- a block that consists of multiple blocks (e.g. ```if/else``` or ```try/catch/finally```).

```java
// Acceptable
public defaultConstructor() {}

// Also acceptable
void foo() { 
}
```

Not acceptable:
```java
try {
  parseBookListData();
} catch (IOException e) {}
```

Note that the unacceptable example above is for illustrative purposes only. It also violates the 'Catch Specific Exception'
guideline (see section 6.3)


### 3.1.4 Spaces around operators 
It's just a lot clearer and nicer to read. 

  - Acceptable:
  ```java
  this.pageCount += pageCount;
  ```
  - Not acceptable:
  ```java
  this.pageCount+=pageCount;
```

### 3.1.5 Explicit operator precedence
Even where obvious. use parenthesis to make the ordering explicit. It could save someone else who does not know 
the precedence rules from having to look it up.

```java
meaningfulVariableName = (2 * SECONDS_IN_MILLIS) + OFFSET; 
```

### 3.1.6 One statement on every line only
Where every statement is separated by a line break.

### 3.1.7 Column limit: 100
For readability purposes. Some exceptions include:
- a long URL
- a TODO statement
- package statements
- import statements
and anywhere breaking a statement at the 100 character limit is invalid.

## 4. Naming conventions

### 4.1 Non-constant field names
No Hungarian notation (System or Apps notation).

Good:
```java
boolean haveClicked = false;
```

Bad:
```java
boolean mHaveClicked = false;
```

### 4.2 Prefixes

#### 4.2.1 Files

| Type of file | Prefix          |
| ------------ | --------------- |
| Activity     | ```activity_``` |
| Fragment     | ```fragment_``` |
| Menu         | ```menu_```     | 

### 4.3 Suffixes

| View             | Suffix       |
| ---------------- | ------------ |
| TextView         | ```Txt```    |
| SimpleDraweeView | ```Drawee``` |
| Button           | ```Btn```    |
  
- ```Adapter```s to be suffixed with ```Adapter```
  - ```java
    private BookListAdapter bookListAdapter; 
    ```
    
- ```Layout```s to be suffixed with ```Layout```
  - ```android:id="@+id/constraintLayout"```

## 5. Good practices

### 5.1 final where possible
If something can be made final (e.g. constants), it should be made final.

### 5.2 Use maintained/better libraries
If a third-party library is not maintained, please avoid using it. 

If something is deprecated (third-party or not), please also avoid using it.

- E.g. RecyclerView over ListView

### 5.3 Minimise variable scope
If something can be local, it should be made local. A variable should be declared as close to where it is used
as possible. This can help reduce the likelihood of a bug occuring, as well as improve readability.

### Exceptions

#### 5.4.1 Catch specific exceptions
Instead of just catching ```Exception```, if it is possible to catch (a) particular exception(s), do so.

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

#### 5.4.2 Don't drop the catch
In a catch block, don't do nothing. 

Good:
```java
try {
  execFishyCode();
} catch (IOException e) {
  e.printStackTrace();
}
```

Bad:

```java
try {
  execFishyCode();
} catch (IOException e) {
}
```

### 5.5 String arrays use string resources

Instead of using string literals, string arrays should always use String resources. Array string literals are not
translated, whereas String resources are.

Good:
```xml
<string-array name="bookInfoShortened">
  <item> @string/title </item>
  <item> @string/publishedDate </item>
  <item> @string/publisher </item>
</string-array>
```

Bad:
```xml
<string-array name="bookInfoShortened">
  <item>title</item>
  <item>publishedDate</item>
  <item>publisher</item>
</string-array>
```

### 5.6 Use single positive conditionals
It can be a lot confusing for other programmers (see [Fowler's article](https://www.refactoring.com/catalog/removeDoubleNegative.html)).

Good:
```java
if (isAvailable) {
  // do something
}
```

Bad:
``` java
if (!isNotAvailable()) {
  // do something
}
```

### 5.7 Class member ordering

This section has been modified from the [Ribot Android style guide](https://github.com/ribot/android-guidelines/blob/master/project_and_code_guidelines.md). It is licensed under the Apache license.

As the Ribot style guide points out, there is no one correct way of ordering members. However, following this format can help find, for example, 
private methods easier.

The following order should be used:
1. Constants
2. Fields
3. Constructors
4. Overidden methods and callbacks (public or private)
5. Public methods
6. Private methods
7. Inner classes or interfaces

```java
public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private String title;
    private TextView textViewTitle;

    @Override
    public void onCreate() {
        ...
    }

    public void setTitle(String title) {
    	this.title = title;
    }

    private void setUpView() {
        ...
    }

    static class InnerClass {

    }
}
```

```Activity``` or ```Fragment``` classes should follow the ordering or the activity/fragment lifecycle: 
```onCreate()```, ```onDestroy```, ```onPause()``` and ```onResume()```.

```java
public class MainActivity extends Activity {

	  // Order of methods corresponds to the order of the activity lifecycle
    @Override
    public void onCreate() {}

    @Override
    public void onResume() {}

    @Override
    public void onPause() {}

    @Override
    public void onDestroy() {}

}
```


## 6. XML style

### 6.1 Self-closing 

Use self-closing tags where possible.

## 7. Annotations

### 7.1 Annotations on separate lines
Every annotation should be on its own, separate line
  - E.g. 
    ```java
    @NonNull
    @Override
    private void foo() { ... }
    ```
    
### 7.2 Overriding methods
Where a method is overriden from a superclass, the ```@Override``` annotation should be used.

# License

For use of a part of Ribot's style guide.

```
Copyright [2018] [Karan Kumar]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
