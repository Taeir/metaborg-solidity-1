package org.metaborg.lang.evmbytecode.strategies;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

/**
 * Pops the top element from the stack.
 * 
 * ebc-stack-pop(|stack) _ -> element
 */
public class ebc_stack_pop_0_1 extends SafeStrategy {
	public static final ebc_stack_pop_0_1 instance = new ebc_stack_pop_0_1();
	
	private ebc_stack_pop_0_1() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm current, IStrategoTerm stackTerm) throws Exception {
		if (!(stackTerm instanceof EBCStrategoStack)) return null;
		
		EBCStrategoStack stack = (EBCStrategoStack) stackTerm; 
		return stack.pop();
	}
}
