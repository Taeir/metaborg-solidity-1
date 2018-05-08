package org.metaborg.lang.solidity.strategies;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

public class sol_bigdec_to_bigint_0_0 extends Strategy {
	public static final sol_bigdec_to_bigint_0_0 instance = new sol_bigdec_to_bigint_0_0();
	
	private sol_bigdec_to_bigint_0_0() {	}
	
	@Override
	public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		BigDecimal nr = BigIntHelper.toBigDecimal(current);
		try {
			BigDecimal nr2 = nr.setScale(0, RoundingMode.UNNECESSARY);
			return context.getFactory().makeString(nr2.toBigIntegerExact().toString());
		} catch (ArithmeticException ex) {
			//Number is not an integer
			//TODO
			throw ex;
		}
	}
}
