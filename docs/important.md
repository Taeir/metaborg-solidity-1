#### Return statement anywhere
Managed to remove restriction "return statement is last statement of function", by doing the following in desugaring.
In desugaring the name of the function is added to every return statement.
e.g. Return(exp) -> Return(functionName, exp)
With the name of the function in the return, it is possible to lookup the enclosing function in NaBL2 and then do the type checking by checking the expression type with the return type of the function.

#### Modifier fixes
Some modifiers that can occur in multiple positions are now checked in the desugaring. If there are 2 conflicting modifiers for constructors or state variables, then InvalidMod is put in it's place. Whenever an InvalidMod is encountered during type checking, an error is added in its place.

#### Function calls and casts
The syntax for function calls and for casts is not distinguishable from each other by syntax alone.
e.g. `int8(10)` and `myFunction(10)`
However, simple examples like this can be distinguished by giving preference to "casts with simple types" over calls.
However, in the following example:
```
contract a {
  function a(int x) {
    return a(x);
  }
}
```
it is impossible to distinguish between a typecast to the contract `a` and a call to the function `a` without using name resolution.

In fact, the following is ambiguous regardless of name resolution:
```
contract a {}
contract b is a{
  function a(b x) {
    return a(x);
  }
}
```
There is no way to determine if this is a cast from `b` to `a` (which is valid) or a call to the function `a` with argument `x` of type `b` (which is also valid).

After investigating this case with the solidity compiler, it seems that functions actually shadow types, so in the above example `a(x)` would be a function call to the function `a` and not a cast.

#### Big numbers
Solidity supports 256 bit integer numbers. Since these numbers are much larger than those supported by most other programming languages (usually 64 bit integers are the largest), I've had to implement specialized stratego strategies for working with these large integers. These strategies use Java's BigInteger and BigDecimal to handle numbers of arbitrary size.