package org.metaborg.lang.evmbytecode.strategies;

import java.util.ArrayList;
import java.util.List;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

/**
 * Clears the stack.
 * 
 * ebc-stack-clear(|stack) _ -> list(instruction)
 */
public class ebc_stack_clear_0_1 extends SafeStrategy {
	public static final ebc_stack_clear_0_1 instance = new ebc_stack_clear_0_1();
	
	private ebc_stack_clear_0_1() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm current, IStrategoTerm stackTerm) throws Exception {
		if (!(stackTerm instanceof EBCStrategoStack)) return null;
		
		EBCStrategoStack stack = (EBCStrategoStack) stackTerm;
		
		int toPop = stack.size();
		stack.clear();
		List<IStrategoTerm> instructions = new ArrayList<>();
		StackUtil.addNPops(context, instructions, toPop);
		return context.getFactory().makeList(instructions);
	}
}
