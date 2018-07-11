package org.metaborg.lang.evmbytecode.strategies;

import java.util.List;

import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class StackUtil {
	/**
	 * Pops and swaps in such a way that only the element at the given index remains.
	 * 
	 * @param context
	 *     the context
	 * @param stack
	 *     the stack
	 * @param index
	 *     the index of the element to retain
	 * @param instructions
	 *     the list of instructions
	 * 
	 * @throws IndexOutOfBoundsException
	 *     If index is negative or index is greater than or equal to the stack size.
	 */
	public static void retainOnly(Context context, EBCStrategoStack stack, int index, List<IStrategoTerm> instructions) {
		if (index < 0 || index >= stack.size()) throw new IndexOutOfBoundsException("" + index);
		
		int i = 0;
		for (; stack.size() > 1 && i < 100; i++) {
			//First pop elements until the element we want to return is at the top of the stack
			int pops = stack.size() - index - 1;
			addNPops(context, instructions, pops);
			stack.pop(pops);
			
			if (stack.size() == 1) break;
			
			//There are still more elements left between us and the return address.
			//Swap our element down the stack as far as possible.
			int swapIndex = Math.min(16, stack.size() - 1);
			instructions.add(createSwap(context, swapIndex));
			stack.swapTop(swapIndex);
			index -= swapIndex;
		}
		
		if (i == 100) {
			throw new IllegalStateException("Assertion failed: infinite loop!");
		}
	}
	
	/**
	 * Adds amount pop instructions to the given instructions list.
	 * 
	 * @param context
	 *     the context
	 * @param instructions
	 *     the list of instructions
	 * @param amount
	 *     the amount to add
	 */
	public static void addNPops(Context context, List<IStrategoTerm> instructions, int amount) {
		for (int i = 0; i < amount; i++) {
			instructions.add(createPop(context));
		}
	}
	
	/**
	 * Creates a pop instruction.
	 * 
	 * @param context
	 *   the context
	 * 
	 * @return
	 *   the pop instruction
	 */
	public static IStrategoAppl createPop(Context context) {
		return context.getFactory().makeAppl(context.getFactory().makeConstructor("POP", 0));
	}
	
	/**
	 * Creates a swap instruction.
	 * 
	 * @param context
	 *     the context
	 * @param n
	 *     the amount to swap with
	 * 
	 * @return
	 *     the swap instruction
	 */
	public static IStrategoAppl createSwap(Context context, int n) {
		return context.getFactory().makeAppl(
				context.getFactory().makeConstructor("SWAP", 1),
				context.getFactory().makeString(Integer.toString(n)));
	}

	/**
	 * Creates a push instruction.
	 * 
	 * @param context
	 *     the context
	 * @param size
	 *     the size of the push (1-32)
	 * @param n
	 *     the number to push
	 * 
	 * @return
	 *     the push instruction
	 */
	public static IStrategoAppl createPush(Context context, int size, int n) {
		return context.getFactory().makeAppl(
				context.getFactory().makeConstructor("PUSH", 2),
				context.getFactory().makeInt(size),
				context.getFactory().makeInt(n));
	}
}
