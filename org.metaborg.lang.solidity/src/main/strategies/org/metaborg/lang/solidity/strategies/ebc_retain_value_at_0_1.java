package org.metaborg.lang.solidity.strategies;

import java.util.ArrayList;
import java.util.List;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

/**
 * Retains only the value at the given stack index (top = 0).
 * 
 * ebc-retain-value-at(|stack) index -> list(Instruction)
 */
public class ebc_retain_value_at_0_1 extends SafeStrategy {
	public static final ebc_retain_value_at_0_1 instance = new ebc_retain_value_at_0_1();
	
	private ebc_retain_value_at_0_1() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm indexTerm, IStrategoTerm stackTerm) throws Exception {
		//Check arguments
		if (!(stackTerm instanceof EBCStrategoStack)) return null;
		if (!Tools.isTermInt(indexTerm)) return null;
		
		EBCStrategoStack stack = (EBCStrategoStack) stackTerm;
		if (stack.size() == 0) return null;
		
		//Convert from top index to normal index
		int index = Tools.asJavaInt(indexTerm);
		index = stack.size() - index - 1;
		
		//Clear the stack except for our element
		List<IStrategoTerm> instructions = new ArrayList<>();
		StackUtil.retainOnly(context, stack, index, instructions);
		
		return context.getFactory().makeList(instructions);
	}
}
