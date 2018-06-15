package org.metaborg.lang.solidity.strategies;

import java.util.ArrayList;
import java.util.List;

import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

/**
 * Creates a push(1, 0) instruction for every variable on the stack.
 * 
 * ebc-initial-instructions(|stack) _ -> list(Instruction)
 */
public class ebc_initial_instructions_0_1 extends SafeStrategy {
	public static final ebc_initial_instructions_0_1 instance = new ebc_initial_instructions_0_1();
	
	private ebc_initial_instructions_0_1() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm current, IStrategoTerm stackTerm) throws Exception {
		//Check arguments
		if (!(stackTerm instanceof EBCStrategoStack)) return null;
		
		EBCStrategoStack stack = (EBCStrategoStack) stackTerm;
		
		//Create the push instructions
		List<IStrategoTerm> instructions = new ArrayList<>();
		for (IStrategoTerm term : stack.getList()) {
			if (term instanceof IStrategoString) {
				instructions.add(StackUtil.createPush(context, 1, 0));
			}
		}
		
		return context.getFactory().makeList(instructions);
	}
}
