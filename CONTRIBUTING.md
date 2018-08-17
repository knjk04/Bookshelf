Thank you for wanting to contribute! :smiley:

Before contributing:
- please inform us of the change you want to make before doing it. This way, we can ensure that people aren't working 
  on the same thing (we'll assign the task to you if it hasn't already been assigned).
- please create a new branch for your changes and submit a pull request.

# Styleguide

Table of contents
1. [Introduction](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#introduction)
1. [Formatting](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#formatting)
    1. [Spaces around operators](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#spaces-around-operators)
1. [Naming conventions](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#naming-conventions)
    1. [Non-constant field names](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#non-constant-field-names)
1. [Annotations](https://github.com/knjk04/Bookshelf/blob/master/CONTRIBUTING.md#annotations)

## Introduction
The style guide we follow is modelled around [Google's Java Style Guide](https://google.github.io/styleguide/javaguide.html)
Where we have added to it or it differs, we have listed below. 

If you see a place where the styleguide is violated, please do submit a pull request.

## Formatting

### Spaces around operators 
It's just a lot clearer and nicer to read. 

  - Acceptable:
  ```java
  this.pageCount += pageCount;
  ```
  - Not acceptable:
  ```java
  this.pageCount+=pageCount;
```

### Explicit operator precedence
Even where obvious. use parenthesis to make the ordering explicit. It could save someone else who does not know 
the precedence rules from having to look it up.

```java
meaningfulVariableName = (2 * SECONDS_IN_MILLIS) + OFFSET; 
```

## Naming conventions

### Non-constant field names
- Member variables start with 'm'

## Good practices

### final where possible
If something can be made final (e.g. constants), it should be made final.

### Use maintained/better libraries
If a third-party library is not maintained, please avoid using it. 

If something is deprecated (third-party or not), please also avoid using it.

- E.g. RecyclerView over ListView

## Annotations
- Every annotation should be on its own, separate line
  - E.g. 
    ```java
    @NonNull
    @Override
    private void foo() { ... }
    ```
