package org.metaborg.lang.solidity.strategies;

import java.math.BigDecimal;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class sol_bigdec_uminus_0_0 extends SafeStrategy {
	public static final sol_bigdec_uminus_0_0 instance = new sol_bigdec_uminus_0_0();
	
	private sol_bigdec_uminus_0_0() { }
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm current) {
		BigDecimal nr = BigIntHelper.toBigDecimal(current).negate();
		return context.getFactory().makeString(nr.toString());
	}
}
