package org.metaborg.lang.solidity.strategies;

import java.math.BigInteger;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class sol_bigint_to_hex_0_0 extends SafeStrategy {
	public static final sol_bigint_to_hex_0_0 instance = new sol_bigint_to_hex_0_0();
	
	private sol_bigint_to_hex_0_0() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm current) {
		BigInteger number = BigIntHelper.toBigInt(current);

		String result = number.toString(16);
		return context.getFactory().makeString(result);
	}
}
