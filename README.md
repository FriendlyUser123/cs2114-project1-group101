# myFieldCalc — CS 2114 Project 1 (Group 101)

A command-line calculator with two modes:

1. **Four-function calculator.** Add, subtract, multiply, and divide decimal numbers.
   Type `ANS` to reuse the last answer.
2. **Polynomial calculator.** Enter two polynomials, then add, subtract, or multiply them.

Bad input never crashes the program. It prints what went wrong and asks again.

## Run it

**Eclipse:** open `src/calculator/OverallCalculator.java`, then **Run As → Java Application**.
(If you just cloned the repo, first use **File → Import → Existing Projects into Workspace**.)

**Command line** (from this folder):

```
javac -d bin -sourcepath src src/calculator/OverallCalculator.java
java -cp bin calculator.OverallCalculator
```

## Use it

At the menu, type **1** (four-function), **2** (polynomial), or **3** (quit).

**Four-function:** enter a number, an operator (`+ - * /`), and a second number.
Type `ANS` for the last answer, or `EXIT` to go back to the menu.

```
5    +  8   →  Result: 13
ANS  *  2   →  Result: 26
```

**Polynomial:** enter each polynomial's degree, then its coefficients from the highest
power down. Leave a coefficient blank to use 0. Then pick `+`, `-`, or `*`.

```
(x + 2) * (x + 3)   →   The resulting polynomial is: x^2 + 5x + 6
```

**Bad input the program catches:** letters where a number goes, a wrong operator,
dividing by 0, a result too large to calculate, and a degree that is negative,
a decimal, or over 100.

## Run the tests

In Eclipse, right-click `src` and choose **Run As → JUnit Test**.
The tests use the course's `CS2-Support` project, which must be in your workspace.

## Design

| Class | Job |
|---|---|
| `OverallCalculator` | Menu, reading input, and checking input (the `parse…` methods) |
| `FourFunctionCalculator` | `+ - * /` on decimals and remembers the last answer |
| `PolynomialCalculator` | `+ - *` on two `Polynomial`s |
| `Polynomial` | A list of `Term`s |
| `Term` | One coefficient and degree, like `5x^3` |

### System diagram

![System diagram](diagram.png)

### User flow

![User flow chart](flowchart.png)
