package org.metaborg.lang.solidity.strategies;

import java.math.BigInteger;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

public class sol_nearest_uint_multiple_0_0 extends Strategy {
	public static final sol_nearest_uint_multiple_0_0 instance = new sol_nearest_uint_multiple_0_0();
	
	private sol_nearest_uint_multiple_0_0() {}
	
	@Override
	public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		final String str = Tools.asJavaString(current);
		BigInteger bi = new BigInteger(str);
		
		int bitsReq = Math.max(bi.bitLength(), 1);
		int eightBits = (int) (Math.ceil(bitsReq / 8.0) * 8);
		return context.getFactory().makeInt(eightBits);
	}
}
