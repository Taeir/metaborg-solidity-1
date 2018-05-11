# Solidity
This document explains the different parts of the language definition.

## Syntax Definition
Almost all of the syntax that is valid in Solidity 4.0.23 is supported by the syntax definition. The syntax definition follows the rules as stated in the solidity documentation ((https://solidity.readthedocs.io/)[https://solidity.readthedocs.io/en/latest/solidity-in-depth.html]). At the time of writing, the provided documentation is not consistent with the behavior of the solidity compiler. In the case where the compiler disagrees with the documentation, the syntax definition follows the compiler.

The syntax definition is broken up into about 20 files, each representing a different language feature:
* Programs, Pragmas and Imports
* Contracts, Interfaces and Libraries
* Enums
* Events
* Structs
* Statements
* Expressions
* Functions
* Variables and State Variables
* Modifiers
* Types
* Inline Assembly (partially)
* Lexical syntax: Strings, Literal Numbers, Layout, etc.

There are some features that are currently not supported. These are as follows.
* Inline Assembly
  * Anything inside an inline assembly block is accepted as valid.
* Tuple Expressions
  * `(10, 20)`
  * `return (1, 2, 5);`

Furthermore, there are a few limitations/problems with the syntax definition. For example, the specific variants of the elementary types, such as `int40` and `bytes5`, are not considered as reserved words. As such, they can be used as variable names, function names, etc., even though this is rejected by the solidity compiler. This is due to problems with adding all the different types as keywords, which causes the parsing to fail.

## Desugaring
In the desugaring phase, a few different transformations are applied to simplify the resulting AST.

- **For statements:** for statements are converted into while statements if they only have a condition, e.g. `for (;;;) {...}` and `for (; i < 20; ) {...}`.
- **LValue assignments:** LValue assignments are converted to a simpler form, e.g. `a += 10` is converted into `a = a + 10`.
- **Imports:** the different import formats are converted into the same generic form.
- **Types:** the different elementary types are converted to the more specific forms they are shorthands for. For example, `int` is converted into `int256` and `byte` is converted into `bytes1`.
- **Constructors:** for constructors, the name of the enclosing contract is added to the constructor element. This way, the constructor can be seen as a function which returns an instance of the contract type, which simplifies typechecking.
- **Return statements:** the name of the enclosing function is added to return statements to simplify typechecking.
- **Numbers**: Literal numbers are constant folded to allow proper typechecking. This is necessary since an expression like `1.2 * 5` would be regarded as the integer number 6 by the solidity compiler, even though there are no floating point numbers. Units are also removed, e.g. `2 minutes` is converted into `120`. Finally, the desugarer calculates the number of bits that would be required to fit the number (rounded up to multiples of 8) into a signed integer and into an unsigned integer, which is used by the type checking to determine the precise type (int8 vs uint8 vs int16).

Still to be implemented:
Since fixed size arrays are part of the type system, constant expressions may need to be evaluated before typechecking. NaBL2 is not turing-complete, and does not have the ability to compute arbitrary expressions.

## Type checking and Name resolution
Both type checking and name resolution are performed by creating a scope graph from the AST.

Solidity's type system is relatively difficult to model in NaBL2. This is because of the different sizes like int8 and int40. Generally, numbers need to be converted to the smallest type in which they fit. This is however, not directly possible in NaBL2, since it does not have any way of expressing such numeric conditions. As such, type checking the numbers themselves is not possible in NaBL2 without extra desugaring rules. To combat this problem, I will probably switch to Statix at some point.

Implicit type conversions, e.g. from int8 to int16, are modelled with relations.

## Code generation
Solidity code is compiled to bytecode for the Ethereum Virtual Machine (EVM). There is a second language project for the EVM Bytecode. It has a rather simple syntax for the different instructions:

```
PUSH2 0x20
DUP2
PUSH1 0x1
ADD
STOP
```

The corresponding AST is very similar:

```
Code([
  PUSH("2", "20"),
  DUP("2"),
  PUSH("1", "1"),
  ADD(),
  STOP()
])
```

The code generation (compilation) is performed in two steps. First, the desugared Solidity AST is transformed into an EVM Bytecode AST via a set of transformation rules. Then, the EVM Bytecode AST is converted into a hexadecimal string by translating each instruction into its corresponding opcode. This hexadecimal string can then be executed by an Ethereum VM.

> TODO: More details about code generation.

## Optimization
Optimization is performed at two stages. First, optimization is applied to the Solidity AST before the transformation into an EVM Bytecode AST. Then, the transformation from the Solidity AST to the EVM Bytecode AST happens as usual. The second phase of optimization happens on the EVM Bytecode AST.

### Solidity optimizations
There are multiple optimizations which are applied to the solidity AST. These are as follows.

#### Unreachable code
Unreachable code is removed as much as possible. For example, one branch of if-else statements where the condition is constant can be removed. For example, the statement
```
if (true) {
  x = 5;
} else {
  x = 10;
}
```
is converted into
```
{
  x = 5;
}
```

Note that the block is kept to ensure that break statements still work as intended. Furthermore, variable declarations in the unreachable code are also kept:
```
if (false) {
  int y;
  int z = 20;
} else {
  x = 10;
}
```
is transformed into
```
{
  int y;
  int z;
  x = 10;
}
```
This is because solidity has Javascript-like scoping rules. In contrast to programming languages like C and Java, variables are scoped per function, and not per block. But more importantly, in Solidity, referring to a variable that is not declared is an error, but referring to a variable before it is declared is valid. The variable initially holds the default value corresponding to its type. As such, all the variable declarations need to be kept, even those in unreachable code.

The same technique is applied to for loops, while loops and do-while loops, if the condition is determined to be always `false`.

**The following techniques are not yet implemented**
#### Removal of unused variables
Unused variables in functions can be removed. However, care should be taken if the variable is assigned the result of a function call. If the function call has side effects, it still needs to be executed. The optimizer makes use of the `pure` and `view` function modifiers that Solidity offers. These modifiers 

#### Cost optimization
Idea: convert memory variables to local variables where possible?
Idea: turn storage variables that are never assigned into memory?

#### Constant folding
Actually happens in the desugaring, because the type checker needs this information.

### Constant propagation
https://en.wikipedia.org/wiki/Constant_propagation

