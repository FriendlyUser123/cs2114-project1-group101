# CS 2114 Project 1 — Calculator App (Group 101)

A command-line calculator with two modes:

1. **Four-function calculator** — add, subtract, multiply, and divide decimal numbers.
   Type `ANS` to reuse the previous answer.
2. **Polynomial calculator** — enter two polynomials term by term, then add, subtract,
   or multiply them.

Every bad input (letters, bad operators, dividing by zero, negative or decimal degrees,
overflow) shows an error and asks again instead of crashing.

## How to run (Eclipse)

1. **File → Import → General → Existing Projects into Workspace**, select this folder.
2. Open `src/calculator/OverallCalculator.java` → **Run As → Java Application**.

## How to run (command line)

```bash
javac -d bin src/calculator/*.java      # needs JUnit 4 on the classpath for the *Test files
java -cp bin calculator.OverallCalculator
```

To build only the program without the tests:

```bash
javac -d bin $(ls src/calculator/*.java | grep -v Test.java)
java -cp bin calculator.OverallCalculator
```

## How to run the tests

In Eclipse: right-click `src` → **Run As → JUnit Test** (JUnit 4).

## Classes

| Class | Responsibility |
|---|---|
| `OverallCalculator` | Menu, user input, and all input validation (the `parse…` methods) |
| `FourFunctionCalculator` | `+ − × ÷` on decimals, plus last-answer memory |
| `PolynomialCalculator` | `+ − ×` on `Polynomial` objects |
| `Polynomial` | A list of `Term`s; converts to and from a coefficient array |
| `Term` | One coefficient-and-degree pair, e.g. `5x^3` |

## System diagram

_TODO: add `diagram.png` to the repo and embed it here:_ `![System diagram](diagram.png)`
