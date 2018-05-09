package org.metaborg.lang.solidity.strategies;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class sol_nearest_uint_multiple_0_0 extends SafeStrategy {
	public static final sol_nearest_uint_multiple_0_0 instance = new sol_nearest_uint_multiple_0_0();
	
	private sol_nearest_uint_multiple_0_0() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm current) {
		BigDecimal bd = BigIntHelper.toBigDecimal(current);
		
		//For negative numbers, return 0
		if (bd.signum() == -1) {
			return context.getFactory().makeInt(0);
		}
		
		//TODO Use RoundingMode.UNNECESSARY?
		//Then determine the number of bits required.
		int bitsReq = Math.max(1, bd.setScale(0, RoundingMode.CEILING).toBigInteger().bitLength());
		int eightBits = (int) (Math.ceil(bitsReq / 8.0) * 8);
		return context.getFactory().makeInt(eightBits);
	}
}
