package org.metaborg.lang.solidity.strategies;

import java.math.BigDecimal;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

public class sol_bigdecz_mult_0_2 extends Strategy {
	public static final sol_bigdecz_mult_0_2 instance = new sol_bigdecz_mult_0_2();
	
	private sol_bigdecz_mult_0_2() {}
	
	@Override
	public IStrategoTerm invoke(Context context, IStrategoTerm current, IStrategoTerm nrTerm, IStrategoTerm multTerm) {
		BigDecimal number = BigIntHelper.toBigDecimal(nrTerm);
		BigDecimal multiplier = BigIntHelper.toBigDecimal(multTerm);

		String result = number.multiply(multiplier).toString();
		return context.getFactory().makeString(result);
	}
}
