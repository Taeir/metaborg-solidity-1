package org.metaborg.lang.solidity.strategies;

import java.math.BigDecimal;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class sol_bigdec_to_bigint_0_0 extends SafeStrategy {
	public static final sol_bigdec_to_bigint_0_0 instance = new sol_bigdec_to_bigint_0_0();
	
	private sol_bigdec_to_bigint_0_0() { }
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm current) {
		BigDecimal nr = BigIntHelper.toBigDecimal(current);
		return context.getFactory().makeString(nr.toBigIntegerExact().toString());
	}
}
