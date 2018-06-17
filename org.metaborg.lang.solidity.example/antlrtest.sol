pragma solidity 0.4.4;
pragma solidity ^0.4.4;
pragma solidity ~0.4.4;
pragma solidity >0.4.4;
pragma solidity >=0.4.4;
pragma solidity <0.4.4;
pragma solidity <=0.4.4;
pragma solidity =0.4.4;

library a1 {}
library b1 {}
library c1 {}
library f1 {}
contract test1 {
    function f1(uint a1, uint b1);
    function g1(uint c1);
}
contract c2 {
    event e2(uint32[10] a1, bytes7[8] b1, c1[3] x);
}

contract c3 {
    function f1() {
        uint8[10 * 2] x;
    }
}
contract c4 {
    uint[10] a1;
    uint[] a2;
    struct x { uint[2**20] b1; y[0] c1; }
    struct y { uint d1; mapping(uint=>x)[] e1; }
}
contract test2 {
    function fun(uint256 a) {
        uint256 x = ([1, 2, 3 + 4][a/=9] - 3) ** 4;
    }
}
import "./abc.sol" as x3;
import * as y3 from "./abc.sol";
import {a as b3, c as d3, f} from "./abc.sol";

contract z {}
contract A {
    function f() {
        uint x = 3 < 0 ? 2 > 1 ? 2 : 1 : 7 > 2 ? 7 : 6;
    }
}
contract A2 {
    function f() {
        uint x = true ? 1 : 0;
        uint y = false ? 0 : 1;
    }
}
contract A3 {
    function f() {
        uint y = 1;
        uint x = 3 < 0 ? x = 3 : 6;
        true ? x = 3 : 4;
    }
}
contract A4 {
    function f() {
        uint x = 3 > 0 ? 3 : 0;
        uint y = (3 > 0) ? 3 : 0;
    }
}
contract A5 {
    function f() {
        uint x = 3;
        uint y = 1;
        uint z = (x > y) ? x : y;
        uint w = x > y ? x : y;
    }
}
contract base4 {
  function fun() {
    uint64(2);
  }
}

contract derived4 is base4 {
  function fun() {
    uint64(2);
  }
}
	
contract foo5 {
  function fun() {
  }
}

contract bar5 {
  function fun() {
  }
}

contract derived5 is foo5, bar5 {
  function fun() {
  }
}
contract A6 {
    fixed40x40 storeMe;
    function f(ufixed x, fixed32x32 y) {
        ufixed8x8 a;
        fixed b;
    }
}
library d6 {}
contract test3 {
  function fun(uint256 a) returns (address b) {
    if (a < 0) b = 0x67; else if (a == 0) b = 0x12; else b = 0x78;
  }
}

contract test4
{}
contract c7 {
    enum foo { }
}
contract test5 {
  uint256 stateVar;
  function functionName(bytes20 arg1, address addr) constant
    returns (int id)
  { }
}
contract c8 {
    enum validEnum { Value1, Value2, Value3, Value4 }

    function c () {
        a = validEnum.Value3;
    }

    validEnum a;
}
contract c9 {
    event e();
}
contract c10 {
    event e() anonymous;
}
contract c11 {
    event e(uint a, bytes32 s);
}
contract c12 {
    event e(uint a, bytes32 indexed s, bool indexed b);
}
contract test6 {
    function fun(uint256 a) {
        uint256 x = 3 ** a;
    }
}
contract c13 {
    function x() external {}
}
contract c14 {
    function() { }
}
contract test7 {
    function fun(uint256 a) {
        uint256 i = 0;
        for (i = 0; i < 10; i++) {
            uint256 x = i;
            break;
            continue;
        }
    }
}
contract test8 {
    function fun(uint256 a) {
        uint256 i = 0;
        for (;;) {
            uint256 x = i;
            break;
            continue;
        }
    }
}
contract test9 {
  function fun(uint256 a) {
    uint256 i =0;
    for (i = 0; i < 10; i++)
        continue;
  }
}
contract test10 {
    function fun(uint256 a) {
        for (uint256 i = 0; i < 10; i++) {
            uint256 x = i;
            break;
            continue;
        }
    }
}
 contract from15 {
 }
contract test11 {
  function functionName(bytes32 input) returns (bytes32 out);
}
contract test12 {
    string a = hex"00FF0000";
    string b = hex'00AA0000';
}
contract test13_2 {
    function fun(uint256 a) {
        if (a >= 8) {
            return a;
        } else {
            var b = 7;
        }
    }
}
import './abc.sol' as my_abc;

contract test13 {}
import { a as my_a, b as my_b } from './abc.sol';

contract test14 {}
import "./abc.sol";

contract test15 {
  function fun() {
    uint64(2);
  }
}
import * as abc from './abc.sol';

contract test16 {}
contract c {
    uint[] a;
    function f() returns (uint, uint) {
        a = [1,2,3];
//        return (a[3], [2,3,4][0]);
    }
}
library Lib {
    function f() { }
}
contract test17 {
    function test() {
        a = 1 wei;
        a = 2 szabo;
        a = 3 finney;
        a = 4 ether;

        a = 1 seconds;
        a = 2 minutes;
        a = 3 hours;
        a = 4 days;
        a = 5 weeks;
        a = 6 years;
    }

    uint256 a;
}
contract c18 {
    function c18 ()
    {
        a = 1 wei * 100 wei + 7 szabo - 3;
    }
    uint256 a;
}
contract Foo19 {
    function f() {
        uint[] storage x;
        uint[] memory y;
    }
}
// contract Foo20 {
//     function f(uint[] constant x, uint[] memory y) { }
// }
contract test21 {
    mapping(address => bytes32) names;
}
contract test22 {
    struct test_struct {
        address addr;
        uint256 count;
        mapping(bytes32 => test_struct) self_reference;
    }
}
contract test23 {
    struct test_struct {
        address addr;
        mapping (uint64 => mapping (bytes32 => uint)) complex_mapping;
    }
}
contract c24 {
    modifier mod { if (msg.sender == 0) _; }
}
contract c25 {
    modifier mod(address a) { if (msg.sender == a) _; }
}
contract c26 {
    modifier mod1(address a) { if (msg.sender == a) _; }
    modifier mod2 { if (msg.sender == 2) _; }
    function f() mod1(7) mod2 { }
}
contract c27 {
    mapping(uint => mapping(uint => int8)[8][][9])[] x;
}
contract C28 {
    function f() {
        var (a,b,c) = g();
        var (d) = 2;
        var (,e) = 3;
        var (f,) = 4;
        var (x,,) = g();
        var (,y,) = g();
        var (,,) = g();
    }
    function g() returns (uint, uint, uint) {}
}
contract test29 {
  function fun() {
    uint64(2);
  }
}

contract test30 {
  function fun() {
    uint64(2);
  }
}
import "./abc.sol";

contract test31 {
  function fun() {
  }
}

contract test32 {
  function fun() {
  }
}

import "./def.sol";

contract foo33 {
  function foo33(uint a) {
  }
}

contract bar34 {
    function bar34(string a, string b) {
    }
}

contract derived35 is foo33(2), bar34("abc", "def") {
  function fun() {
  }
}
contract test36 {
    function f() returns(bool succeeded) {
        return false;
    }
}
contract test37 {
  uint256 stateVar;
  function functionName() {}
}
contract test38 {
    function fun(int256 a) {
        int256 x = (1 + 4) * (a - 12) + -9;
        bool y = true && (a < 6) || false;
    }
}
//contract test39 {
////TODO OVERLOADING
//    function fun(uint a) returns(uint r) { return a; }
//    function fun(uint a, uint b) returns(uint r) { return a + b; }
//}
contract c40 {
    function fun() returns (uint r) {
        var _ = 8;
        return _ + 1;
    }
}
pragma solidity ^0.4.4;

contract test41 {}
contract test42 {
  uint256 public stateVar;
  function functionName(bytes32 input) returns (bytes32 out) {}
}
contract test43 {
  uint256 stateVariable1;
}
contract test44 {
    function fun() {
        uint64(2);
    }
}
contract test45 {
    uint256 stateVar;
    struct MyStructName {
        address addr;
        uint256 count;
    }
}
contract C46 {
    function f() {
        uint a = (1);
        var (b,) = 1;
//        var (c,d) = (1, 2 + a);
//        var (e,) = (1, 2, b);
        (a) = 3;
    }
}
contract test47 {
  function fun() {
    var x = new uint64[](3);
  }
}
contract test48 {
    function f() {
        uint a = +10;
        a--;

        a = ~a;
        delete a;

        bool b = !true;
    }
}
library Lib49 {
}

contract C50 {
    struct s { uint a; }
    using Lib49 for uint;
    using Lib49 for *;
    using Lib49 for s;

    function f() {
    }
}
contract test51 {
    function fun(uint256 a) {
        var b = 5;
        uint256 c;
        mapping(address=>bytes32) d;
    }
}
contract test52 {
    function fun(uint256 a) {
        var b = 2;
        uint256 c = 0x87;
        mapping(address=>bytes32) d;
        bytes32 name = "Solidity";
    }
}
contract c53 {
    uint private a;
    uint internal b;
    uint public c;
    uint d;
    function f() {}
    function f_priv() private {}
    function f_public() public {}
    function f_internal() internal {}
}
contract test54 {
    function fun(uint256 a) {
        while (true) { uint256 x = 1; break; continue; } x = 9;
    }
}

contract test55 {
  function() {
		assembly {
			mstore(0x40, 0x60) // store the "free memory pointer"
			// function dispatcher
			switch div(calldataload(0), exp(2, 226))
			case 0xb3de648b {
				let (r) := f(calldataload(4))
				let ret := $allocate(0x20)
				mstore(ret, r)
				return(ret, 0x20)
			}
			default { revert(0, 0) }
			// memory allocator
			function $allocate(size) -> pos {
				pos := mload(0x40)
				mstore(0x40, add(pos, size))
			}
			// the contract function
			function f(x) -> y {
				y := 1
				for { let i := 0 } lt(i, x) { i := add(i, 1) } {
					y := mul(2, y)
				}
				if gt(y, 2) { revert(0, 0) }
			}
		}
  }
}

contract test56 {
  function f() view {
    int y = z(10);
    return 2;
  }
  function g() pure {
    return 2;
  }
  
  function z(int a) returns (int) {
    return a;
  }
}

contract test57 {
  function f() {
    uint256 a = 2.3e5;
  }
}

contract test58 {
  function f() {
    uint256 a;
    (a,) = g();
    (,) = g();
//    () = ();
  }
}

contract test59 {
  function foo() public returns (byte b) {
    assembly {
      n := byte(0x0)
    }
  }
}

contract test60 {
    event EventCalled(int x, int y, int z);
    
    function() {
        emit EventCalled(1, 2, 3);
    }
}

contract test61 {
    modifier withModifier {}

    function x(uint a, uint b) withModifier {}
}