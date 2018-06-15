package org.metaborg.lang.solidity.strategies;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

/**
 * Gets the index (top = 0) of the given element (variable) in the stack.
 * 
 * ebc-stack-get-index(|stack) element -> index
 */
public class ebc_stack_get_index_0_1 extends SafeStrategy {
	public static final ebc_stack_get_index_0_1 instance = new ebc_stack_get_index_0_1();
	
	private ebc_stack_get_index_0_1() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm varTerm, IStrategoTerm stackTerm) throws Exception {
		if (!(stackTerm instanceof EBCStrategoStack)) return null;
		
		EBCStrategoStack stack = (EBCStrategoStack) stackTerm;
		int index = stack.getIndexFromTop(varTerm);
		return context.getFactory().makeInt(index);
	}
}
