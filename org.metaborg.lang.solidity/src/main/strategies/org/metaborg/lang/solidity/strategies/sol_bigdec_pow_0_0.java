package org.metaborg.lang.solidity.strategies;

import java.math.BigDecimal;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class sol_bigdec_pow_0_0 extends TupleStrategy {
	public static final sol_bigdec_pow_0_0 instance = new sol_bigdec_pow_0_0();
	
	private sol_bigdec_pow_0_0() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm[] terms) {
		BigDecimal e1 = BigIntHelper.toBigDecimal(terms[0]);
		BigDecimal e2 = BigIntHelper.toBigDecimal(terms[1]);
		
		//Support negative powers
		String result;
		if (e2.signum() == -1) {
			//x ^ -n = 1/(x^n)
			result = BigDecimal.ONE.setScale(e1.scale()).divide(e1.pow(-e2.intValueExact())).toString();
		} else {
			result = e1.pow(e2.intValueExact()).toString();
		}
		return context.getFactory().makeString(result);
	}
}
