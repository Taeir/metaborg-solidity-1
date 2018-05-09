package org.metaborg.lang.solidity.strategies;

import java.math.BigInteger;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class sol_bigdec_bitnot_0_0 extends SafeStrategy {
	public static final sol_bigdec_bitnot_0_0 instance = new sol_bigdec_bitnot_0_0();
	
	private sol_bigdec_bitnot_0_0() { }
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm current) {
		BigInteger nr = BigIntHelper.toBigDecimal(current).toBigIntegerExact();
		return context.getFactory().makeString(nr.not().toString());
	}
}
