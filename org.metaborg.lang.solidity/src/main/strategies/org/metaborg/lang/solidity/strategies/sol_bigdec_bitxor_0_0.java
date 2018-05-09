package org.metaborg.lang.solidity.strategies;

import java.math.BigInteger;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class sol_bigdec_bitxor_0_0 extends TupleStrategy {
	public static final sol_bigdec_bitxor_0_0 instance = new sol_bigdec_bitxor_0_0();
	
	private sol_bigdec_bitxor_0_0() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm[] terms) {
		BigInteger e1 = BigIntHelper.toBigDecimal(terms[0]).toBigIntegerExact();
		BigInteger e2 = BigIntHelper.toBigDecimal(terms[1]).toBigIntegerExact();

		String result = e1.xor(e2).toString();
		return context.getFactory().makeString(result);
	}
}
