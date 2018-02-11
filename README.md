# Substring expressions

## Overview

The goal of this project is to provided syntax similar to regular expressions that allows to check if provided expression is part of another string.

### Project requirements:
Write a program that takes 2 string parameters from the command line. Program must verify if the second string is a substring of the first string 
(you cannot use substr, substring or any other standard function including regular expression libraries).

Each occurrence of \* in the second substring means that it can be a match for zero or more characters of the first string.

Consider the example:
```
Input string 1: abcd
Input string 2: a*c
```
Program should evaluate that the string 2 is a substring of the string 1.

Additionally asterisk (\*) may be considered as a regular character, if it is preceded by a backslash (\\). Backslash (\\) is considered as a regular 
character in all cases other than preceding the asterisk (\*).

## Building project
The project can be built with single command:
```
./gradlew clean fatJar
```

## Usage
```
java -cp build/libs/substring-expressions-1.0-SNAPSHOT.jar it.ruczaj.expressions.SubstringCheckerMain inputString comparedExpression
```

## Example of usage
```
java -cp build/libs/substring-expressions-1.0-SNAPSHOT.jar it.ruczaj.expressions.SubstringCheckerMain "1234*a" "1234\**"
```
