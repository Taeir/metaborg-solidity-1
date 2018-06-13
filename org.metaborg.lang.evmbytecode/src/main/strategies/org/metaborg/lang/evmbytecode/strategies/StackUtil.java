package org.metaborg.lang.evmbytecode.strategies;

import java.util.List;

import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class StackUtil {
	/**
	 * Pops and swaps in such a way that only the bottom element of the stack and the element at
	 * the given index remain. (The bottom element is the return address)
	 * 
	 * For example, retaining index 2 ("b") in
	 *   [returnAddress, a, b, c, d, e] (<- top)
	 * yields
	 *   [returnAddress, b]
	 * 
	 * @param context
	 *     the context
	 * @param stack
	 *     the stack
	 * @param index
	 *     the index of the element to retain
	 * @param instructions
	 *     the list of instructions
	 */
	public static void retainZeroAnd(Context context, EBCStrategoStack stack, int index, List<IStrategoTerm> instructions) {
		while (stack.size() != 2) {
			//First pop elements until the element we want to return is at the top of the stack
			int pops = stack.size() - index - 1;
			addNPops(context, instructions, pops);
			
			if (stack.size() == 2) break;
			
			//There are still more elements left between us and the return address.
			//Swap our element down the stack as far as possible.
			//It will never swap with the return address.
			int swapIndex = Math.min(16, stack.size() - 2);
			instructions.add(createSwap(context, swapIndex));
			stack.swapTop(swapIndex);
		}
	}
	
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
		
		while (stack.size() > 1) {
			//First pop elements until the element we want to return is at the top of the stack
			int pops = stack.size() - index - 1;
			addNPops(context, instructions, pops);
			
			if (stack.size() == 1) break;
			
			//There are still more elements left between us and the return address.
			//Swap our element down the stack as far as possible.
			int swapIndex = Math.min(16, stack.size() - 1);
			instructions.add(createSwap(context, swapIndex));
			stack.swapTop(swapIndex);
		}
		
		//TODO This is a temporary assertion
		if (stack.size() == 0) {
			throw new IllegalStateException("Assertion failed: Stack should not be empty here!");
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
}
