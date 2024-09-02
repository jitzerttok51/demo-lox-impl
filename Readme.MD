# Lox design

## Core features
- Dynamic typing
- Automatic memory management

## Data types
- boolean - "true", "false"
- number - decimal, floating-point
- string - any ""
- Nil - for null

## Expressions

### Prefix
- (+|-) first

### Arithmetic
- Addition: add + me
- Subtraction: subtract - me
- Multiplication: multiply * me
- Division: dived / me

_work only with numbers throw exception otherwise_


### Comparison and equality
- Greater than: first > second
- Lest than: first < second
- Greater than or equal: first <= second
- Less than or equal: first >= second
- Equal: first == second
- Not equal: first != second

_false if invalid types used_

### Logical
- Not: !true
- And: first && second
- Or: first || second

_throw exception if not boolean_

### Grouping and Precedence
- Multiplication/division before addition/subtraction
- Sub-expression in parentheses comes first


## Statements

### Print statement
```js
    print <expression>;
```

### Declare a variable
```js
    var x = <expression>;
```

### Update a variable
```js
    x = <expression>;
```

### Blocks 
```js
   {
        <statement>
        <statement>
        ...    
   }
```
