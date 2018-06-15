package org.metaborg.lang.evmbytecode.strategies;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

/**
 * Pushes an element to the stack.
 * 
 * ebc-stack-push(|stack) element -> stack
 */
public class ebc_stack_push_0_1 extends SafeStrategy {
	public static final ebc_stack_push_0_1 instance = new ebc_stack_push_0_1();
	
	private ebc_stack_push_0_1() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm elementTerm, IStrategoTerm stackTerm) throws Exception {
		if (!(stackTerm instanceof EBCStrategoStack)) return null;
		
		EBCStrategoStack stack = (EBCStrategoStack) stackTerm; 
		stack.push(elementTerm);
		
		return stack;
	}
}
