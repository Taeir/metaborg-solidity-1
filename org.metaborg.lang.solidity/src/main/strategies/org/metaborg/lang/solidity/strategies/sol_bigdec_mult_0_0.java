package org.metaborg.lang.solidity.strategies;

import java.math.BigDecimal;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class sol_bigdec_mult_0_0 extends TupleStrategy {
	public static final sol_bigdec_mult_0_0 instance = new sol_bigdec_mult_0_0();
	
	private sol_bigdec_mult_0_0() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm[] terms) {
		BigDecimal number = BigIntHelper.toBigDecimal(terms[0]);
		BigDecimal multiplier = BigIntHelper.toBigDecimal(terms[1]);

		String result = number.multiply(multiplier).toString();
		return context.getFactory().makeString(result);
	}
}
