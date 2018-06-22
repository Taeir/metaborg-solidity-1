# Solidity
This document explains the different parts of the language definition.

## Architecture
The architecture of the language definition is as follows. The definition itself is divided into five separate components.

1. Syntax definition
2. Desugaring
3. Type Analysis and Name Binding
4. Code generation
5. Optimization

Each one of these components is defined in its own directory. Each directory then contains separate files, each representing a different language feature. These are roughly as follows:
* Pragmas
* Imports
* Contracts
* Interfaces
* Libraries
* Enums
* Events
* Structs
* Statements
* Expressions
* Functions
* Function modifiers
* Modifiers
* Types
* Using ... for ...
* Variables and State Variables

Depending on the component, there might be more or less files. Some components support more features than others.

## Syntax Definition
The syntax definition is written in SDF3 and resides in the syntax folder. It is the most complete component of the language definition. Almost all of the syntax that is valid in Solidity 4.0.23 is supported by the provided syntax definition. The syntax definition follows the rules as stated in the solidity documentation ((https://solidity.readthedocs.io/)[https://solidity.readthedocs.io/en/latest/solidity-in-depth.html]).

At the time of writing, the provided documentation is not consistent with the behavior of the solidity compiler. In the case where the compiler disagrees with the documentation, the syntax definition follows the compiler.

### Support
The following sections explain the features that are not or only partially supported, and the reasons why they are not fully supported.

#### Tuple expressions
Tuples are only partially supported. Tuple declarations, tuple assignments and tuple return types on functions are suported, as can be seen in the following example:

```
1: var (a, b) = f();
2: var (a, b, ) = g();
3: var (,) = f();
4: (c, ) = f();
5: (,) = f();
6: (a) = 10;

7: function f() returns (int, int) {}
8: function g() returns (int, int, uint) {}
```

Names can be omitted to drop those items of the tuple as defined by the solidity language definition.
Whenever all components of a tuple are dropped (line 3 and 5), the statement is converted to just the function call in the desugaring phase.
Unnecessary tuple use like in line 6 is removed.

However, tuples cannot actually be created. E.g. `(10, 20)` is not parsed by the syntax as a valid tuple.
The reason that support is lacking, is that the syntax definition for a tuple conflicts with the rule that allows parenthesis in expressions, e.g. `(10 + 5) * 3`. Having both rules causes a lot of syntax parsing problems, which is why tuple expressions were left out.

Tuples can still be created by functions with named return parameters, as can be seen in the following example.
```
function f() returns (int a, int b) {
  a = 10;
  b = 20;
}
```

#### Inline Assembly
Inline assembly is not supported. The syntax definition requires that curly brackets are matched, but puts the rest of the inline assembly into the AST unchecked. For example:

```
assembly {
  n := byte(0x0)
}
```

is parsed as

```
Assembly(ASMBlock(["n", ":=", "byte(0x0)"]))
```

This was done to ensure that any inline assembly blocks in the code do not cause parsing to fail.

### Challenges
This section outlines the main challenges in the syntax definition.

#### Modifiers
Modifiers were an interesting problem. In Solidity, as well as in many other languages, modifier positions and ordering are quite flexible. For example, the following are all valid:

```
function f() public constant {}
function f() constant public {}
function f() private {}
```

However, there are some rules for modifiers. The following are not valid:

```
function f() public private {}
function f() constant constant {}
```

In a syntax definition, it is difficult to express such a constraint. At each position, either modifier is present, as long as there are not two modifiers of the same category.

In this language definition, this constraint is actually enforced in the desugaring. The desugarer collects the modifiers from the different positions. If there are multiple modifiers in one category, it converts the modifier to an `InvalidMod` with an appropriate error message, which is then reported to the user.
By utilizing this method, the definition retains all the flexibility as defined by the language, but still enforces the logical constraints as a compiler should.

#### Calls and Casts
In solidity, calls and casts are very similar, and is not always distinguishable from each other by syntax alone. See the following example:

```
1: int8(10)
2: myFunction(10)
```

The first line is a cast, while the second is a function call. For primitive types like int8, it is not that difficult to disambiguate, but for user defined types, this is not possible with pure syntax.

In fact, it is possible to define a type and a function in such a way that the function shadows casts. For example:
```
contract a {
  function a(int x) {
    return a(x);
  }
}
```
it is impossible to distinguish between a typecast to the contract type `a` and a call to the function `a` without using name resolution.

In fact, the following is problematic regardless of name resolution:
```
contract a {}
contract b is a {
  function a(b x) {
    return a(x);
  }
}
```
There is no way to determine if this is a cast from `b` to `a` (which is valid) or a call to the function `a` with argument `x` of type `b` (which is also valid).

The solidity documentation does not provide any information on this matter. However, after investigating this case with the solidity compiler, it seems that functions actually shadow types, so in the above example `a(x)` would be a function call to the function `a` and not a cast.

For the syntax definition, I decided to give preference to casts, but to avoid any user defined types. So `int8(10)` will become `Cast(IntType(8), 10)`, but `myType(10)` will become `Call("myType", 10)`. This behavior reflects the shadowing rules properly.

#### Types
Solidity has quite an interesting type system. Solidity offers types and inheritance like Object Oriented Languages as normal. However, the primitives that it offers are quite interesting. Since with ethereum you pay per byte used, solidity offers the programmer a lot of control over the amount of memory that is used by a particular variable. It has types like `int8`, `int72`, `fixed24x7` and `bytes14`. Each of them signifies the amount of bits or bytes that is used by that type.

In the syntax, these types are not necessarily that difficult, except that types like `int16` should be considered reserved keywords, while names like `int7` should not. With the large number of additional keywords, I initially encountered some problems.

Another problem is the way these types are converted into the AST. With lexical definitions, the AST would get elements like `Type("int16")` and `Type("fixed16x8")`. These literal strings would then need to be parsed again to extract the actual numbers. Defining the items in context-free syntax allows layout between the `int` and the number, e.g. `int 16`. To avoid this, I used kernel syntax for these types, which provides more control over the type.

The types also provided some challenges in the other components, which is discussed in their appropriate sections.
Another annoyance was that the colorer was not coloring primitive types correctly.  Unfortunately, the colorer in spoofax has the assumption that keywords do not contain any numbers, which causes the number part of the type to be colored differently. I fixed this with a custom colorer definition.

## Desugaring
In the desugaring phase, a few different transformations are applied to simplify the resulting AST. Not every language feature is desugared, and some features are implemented in a separate file for clarity.

The following sections list the different items that are desugared.

#### Expressions
Unary and binary operators are desugared into binexps and unexps for consistency and simplicity.

#### For statements
For statements are converted into while statements if they only have a condition, e.g. `for (;;;) {...}` and `for (; i < 20; ) {...}`.

#### LValue Assignments
LValue assignments are converted to a simpler form, e.g. `a += 10` is converted into `a = a + 10`. In contrast to some languages, these expressions are equivalent to each other.

#### Imports
The different import formats that solidity supports are converted into one generic form:

```
import * as a           -> ImportStar(a)
import a                -> ImportItem(a, a)
import a as b           -> ImportItem(a, b)
import {a, b as c, ...} -> [ImportItem(a as a), ImportItem(b as c), ...]
```

#### Tuples
Any tuple declaration or assignment that contains only one or no items is converted into a normal declaration or assignment, or just the expression respectively.

#### Types
The different elementary types are converted to the more specific forms they are shorthands for. For example, `int` is converted into `int256` and `byte` is converted into `bytes1`.

#### Modifiers
As mentioned in section X above, the constraints on modifiers are checked by the desugarer. It ensures that there is only one modifier of each category, and provides an error message to the user otherwise.

#### Functions
Solidity has a few different types of functions in the syntax, but in terms of how they are used in the type checking, they are pretty much the same. As such, I decided to desugar functions into a generic format: `Function(name, parameters, modifiers, returnParameters, statement)`. Void functions get assigned returnParameters of `VoidType()` and the fallback function is considered as a function with the name `*fallback` (`*` is not a valid character in function names, so no conflicts can occur).

#### Constructors
For constructors, the name of the enclosing contract is added to the constructor element. This way, the constructor can be seen as a function which returns an instance of the contract type, which simplifies typechecking.

#### Return statements
In order to support return statements at arbitrary positions, the type of the function to which a return statement belongs must be available for the type checking. To make this easier, the desugarer adds the name of the enclosing function to return statements.

#### Constant folding
Literal numbers are constant folded to allow proper typechecking. This is necessary since an expression like `1.2 * 5` would be regarded as the integer number 6 by the solidity compiler, even though there are no floating point numbers in the language (partial support was added recently). The desugarer also removes units, e.g. `2 minutes` is converted into `120`. Finally, the desugarer calculates the number of bits that would be required to fit the number (rounded up to multiples of 8) into a signed integer and the number of bits required to fit it into an unsigned integer. This information is used by the type checking to determine the smallest type (int8 vs uint8 vs int16) of integer literals.

#### Big numbers
Solidity supports 256 bit integer numbers (and uses these by default). Since these numbers are much larger than those supported by most other programming languages (usually 64 bit integers are the largest), I've had to implement specialized stratego strategies for working with these large integers. These strategies use Java's BigInteger and BigDecimal to handle numbers of arbitrary size.

#### Strings
In order to determine the smallest amount of bytes required for strings, the typechecker needs to know the length of a string. This information is added in the desugarer for this reason.

#### Dead code
The desugarer is able to perform simple dead code analysis. Any statement following a return, break, continue or throw statement is wrapped in a `DeadCode` element. The user is presented with a warning that the code is unreachable.

## Type checking and Name resolution
Both type checking and name resolution are performed by creating a scope graph from the AST.

Solidity's type system is relatively difficult to model in NaBL2. This is because of the different sizes like int8 and int40. Generally, numbers need to be converted to the smallest type in which they fit. This is however, not directly possible in NaBL2, since it does not have any way of expressing such numeric conditions. As such, multiple desugaring rules have been applied to allow for these extra constraints.

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

Furthermore, variable declarations in the unreachable code are also kept:
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

### Future Optimizations
The following are ideas for future optimizations and improvements.

#### Removal of unused variables
Unused variables in functions can be removed. However, care should be taken if the variable is assigned the result of a function call. If the function call has side effects, it still needs to be executed. The optimizer makes use of the `pure` and `view` function modifiers that Solidity offers. These modifiers indicate that the function has no side effects, and thus that even the function call can be removed.

#### Cost optimization
As the user pays for each instruction that is executed, it makes sense to optimize for the cost. There are several techniques that could be employed, such as converting memory variables to local variables where possible, removing superfluous push/pop instructions and optimizing variable positions on the stack.

### Constant propagation
Constant propagation (see https://en.wikipedia.org/wiki/Constant_propagation) could be implemented as an optimization technique.

