package org.metaborg.lang.solidity.strategies;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

public class sol_scientific_decimal_to_bigdec_0_0 extends Strategy {
	public static final sol_scientific_decimal_to_bigdec_0_0 instance = new sol_scientific_decimal_to_bigdec_0_0();
	
	private sol_scientific_decimal_to_bigdec_0_0() {}
	
	@Override
	public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		BigDecimal number = BigIntHelper.toBigDecimal(current);
		
		//Convert to big integer if it is possible to do so without rounding
		BigInteger binr = number.setScale(0, RoundingMode.UNNECESSARY).toBigIntegerExact();
		return context.getFactory().makeString(binr.toString());
	}
}
