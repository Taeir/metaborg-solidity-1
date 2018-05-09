package org.metaborg.lang.solidity.strategies;

import java.math.BigInteger;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class sol_bigint_mult_0_0 extends TupleStrategy {
	public static final sol_bigint_mult_0_0 instance = new sol_bigint_mult_0_0();
	
	private sol_bigint_mult_0_0() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm[] tuple) {
		BigInteger number = BigIntHelper.toBigInt(tuple[0]);
		BigInteger multiplier = BigIntHelper.toBigInt(tuple[1]);

		String result = number.multiply(multiplier).toString();
		return context.getFactory().makeString(result);
	}
}
