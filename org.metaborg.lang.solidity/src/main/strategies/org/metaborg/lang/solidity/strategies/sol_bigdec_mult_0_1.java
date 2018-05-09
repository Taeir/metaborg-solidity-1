package org.metaborg.lang.solidity.strategies;

import java.math.BigDecimal;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class sol_bigdec_mult_0_1 extends SafeStrategy {
	public static final sol_bigdec_mult_0_1 instance = new sol_bigdec_mult_0_1();
	
	private sol_bigdec_mult_0_1() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm current, IStrategoTerm multTerm) {
		BigDecimal number = BigIntHelper.toBigDecimal(current);
		BigDecimal multiplier = BigIntHelper.toBigDecimal(multTerm);

		String result = number.multiply(multiplier).toString();
		return context.getFactory().makeString(result);
	}
}
