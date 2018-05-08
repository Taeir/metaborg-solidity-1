package org.metaborg.lang.solidity.strategies;

import java.math.BigInteger;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

public class sol_bigint_mult_0_1 extends Strategy {
	public static final sol_bigint_mult_0_1 instance = new sol_bigint_mult_0_1();
	
	private sol_bigint_mult_0_1() {}
	
	@Override
	public IStrategoTerm invoke(Context context, IStrategoTerm current, IStrategoTerm multTerm) {
		BigInteger number = BigIntHelper.toBigInt(current);
		BigInteger multiplier = BigIntHelper.toBigInt(multTerm);

		String result = number.multiply(multiplier).toString();
		return context.getFactory().makeString(result);
	}
}
