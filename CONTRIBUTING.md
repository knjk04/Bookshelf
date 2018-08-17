Thank you for wanting to contribute! :)

If you're wanting to contribute:
- please inform us of the change you want to make before doing it. This way, we can ensure that people aren't working 
  on the same thing (we'll assign the task to you if it hasn't already been assigned).
- please create a new branch for your changes and submit a pull request.

# Styleguide

Table of contents
1. Introduction
1. [Formatting](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#formatting)
    1. Spaces around operators
    1. No optional braces
1. [Naming conventions](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#naming-conventions)
    1. [Package names](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#constants)
    1. [Constants](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#constants)
    1. [Non-constant field names](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#non-constant-field-names)
    1. [Local variable names](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#local-variable-names)
    1. [Parameter names](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#local-variable-names)
    1. [Method names](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#local-variable-names)
  
4. [Annotations](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#annotations)

## Introduction
This style guide loosely follows [Google's Java Style Guide](https://google.github.io/styleguide/javaguide.html) (please 
refer to this style guide instead of Google's if you're wanting to contribute here, as this style guide may differ
slightly from Google's). 
If you see a place where the styleguide is violated, please do submit a pull request.

## Formatting

- 100 character limit where possible (acceptable exceptions include a TODO statement)

### Spaces around operators 
  - Acceptable:
  ```java
  this.pageCount += pageCount;
  ```
  - Not acceptable:
  ```java
  this.pageCount+=pageCount;
```

### No optional braces
- Braces are ommitted where optional for ```if```, ```else```, ```for``` and ```while``` statements

## Naming conventions

### Package names
- Packages names follow the Google style: "all lowercase, with consecutive words simply concatenated 
  together (no underscores)." For instance, `com.example.smallbookshelf`, not `com.example.smallBookshelf`
  or `com.example.small_bookshelf`
  
### Constants
- Constant names should be in all uppercase letters, with consecutive words separated by an underscore 
  - ```java 
    public static final String PUBLISHER_SUFFIX = "not found"; 
    ```

### Non-constant field names
- Member variables start with 'm'

- Non-constant field names are usually nouns or noun phrases (e.g. `decsription` or `searchQuery`

### Local variable names
- Local variable names should be written in `lowerCamelCase`

- Even if the local variable is final immutable, it should not be styled as constants in all uppercase letters

### Parameter names
- Single character parameter names in methods (public or private) should be avoided

- Parameter names should be written in `lowerCamelCase`

### Method names
- Method names should be written in `lowerCamelCase`

- Method name should be verbs or verb phrases. For example, `executeSearch` or `searchFor`.

## Annotations
- Every annotation should be on its own, separate line
  - E.g. 
    ```java
    @NonNull
    @Override
    private void foo() { ... }
    ```
