package org.metaborg.lang.solidity.strategies;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class sol_bigdec_lshift_0_0 extends TupleStrategy {
	public static final sol_bigdec_lshift_0_0 instance = new sol_bigdec_lshift_0_0();
	
	private sol_bigdec_lshift_0_0() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm[] terms) {
		BigInteger e1 = BigIntHelper.toBigDecimal(terms[0]).toBigIntegerExact();
		BigDecimal e2 = BigIntHelper.toBigDecimal(terms[1]);

		//Disallow shifting with negative numbers
		if (e2.signum() == -1) return null;
		
		String result = e1.shiftLeft(e2.intValueExact()).toString();
		return context.getFactory().makeString(result);
	}
}
