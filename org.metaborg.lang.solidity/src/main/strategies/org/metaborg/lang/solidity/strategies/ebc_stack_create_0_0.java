package org.metaborg.lang.solidity.strategies;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

/**
 * Creates a new stack.
 * 
 * ebc-stack-create size -> stack
 */
public class ebc_stack_create_0_0 extends SafeStrategy {
	public static final ebc_stack_create_0_0 instance = new ebc_stack_create_0_0();
	
	private ebc_stack_create_0_0() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm sizeTerm) throws Exception {
		if (!Tools.isTermInt(sizeTerm)) return null;
		
		int initialSize = ((IStrategoInt) sizeTerm).intValue();
		
		return new EBCStrategoStack(initialSize);
	}
}
