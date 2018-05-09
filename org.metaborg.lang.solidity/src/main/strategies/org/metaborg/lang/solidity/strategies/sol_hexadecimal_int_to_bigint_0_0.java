package org.metaborg.lang.solidity.strategies;

import java.math.BigInteger;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class sol_hexadecimal_int_to_bigint_0_0 extends SafeStrategy {
	public static final sol_hexadecimal_int_to_bigint_0_0 instance = new sol_hexadecimal_int_to_bigint_0_0();
	
	private sol_hexadecimal_int_to_bigint_0_0() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm current) {
		BigInteger nr = new BigInteger(Tools.asJavaString(current), 16);
		return context.getFactory().makeString(nr.toString());
	}
}
