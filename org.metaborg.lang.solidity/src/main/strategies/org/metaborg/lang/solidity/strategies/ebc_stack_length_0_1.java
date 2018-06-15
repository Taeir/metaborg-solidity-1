package org.metaborg.lang.solidity.strategies;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

/**
 * Returns the length of a stack.
 * 
 * ebc-stack-length(|stack) _ -> int
 */
public class ebc_stack_length_0_1 extends SafeStrategy {
	public static final ebc_stack_length_0_1 instance = new ebc_stack_length_0_1();
	
	private ebc_stack_length_0_1() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm current, IStrategoTerm stackTerm) throws Exception {
		if (!(stackTerm instanceof EBCStrategoStack)) return null;
		
		EBCStrategoStack stack = (EBCStrategoStack) stackTerm;
		
		return context.getFactory().makeInt(stack.size());
	}
}
