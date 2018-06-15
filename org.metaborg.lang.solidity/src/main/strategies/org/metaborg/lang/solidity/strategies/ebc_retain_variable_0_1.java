package org.metaborg.lang.solidity.strategies;

import java.util.ArrayList;
import java.util.List;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

/**
 * Retains only the value of the variable with the given name on the stack.
 * 
 * ebc-retain-variable(|stack) "variableName" -> list(Instruction)
 */
public class ebc_retain_variable_0_1 extends SafeStrategy {
	public static final ebc_retain_variable_0_1 instance = new ebc_retain_variable_0_1();
	
	private ebc_retain_variable_0_1() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm elementTerm, IStrategoTerm stackTerm) throws Exception {
		//Check arguments
		if (!(stackTerm instanceof EBCStrategoStack)) return null;
		if (!Tools.isTermString(elementTerm)) return null;
		
		EBCStrategoStack stack = (EBCStrategoStack) stackTerm;
		if (stack.size() == 0) return null;
		
		//Check if the element is in the stack, fail otherwise
		int index = stack.getIndex(elementTerm);
		if (index == -1) return null;
		
		//Clear the stack except for our element
		List<IStrategoTerm> instructions = new ArrayList<>();
		StackUtil.retainOnly(context, stack, index, instructions);
		
		return context.getFactory().makeList(instructions);
	}
}
