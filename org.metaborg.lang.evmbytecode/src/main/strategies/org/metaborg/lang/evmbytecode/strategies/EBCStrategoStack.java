package org.metaborg.lang.evmbytecode.strategies;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermPrinter;
import org.spoofax.terms.AbstractSimpleTerm;
import org.spoofax.terms.AbstractTermFactory;
import org.spoofax.terms.TermFactory;

/**
 * A Stack implementation for stratego.
 */
public class EBCStrategoStack extends AbstractSimpleTerm implements IStrategoTerm, Serializable {
	private static final long serialVersionUID = 1199176198053003100L;
	
	protected List<IStrategoTerm> list;
	
	/**
	 * Creates a new stack backed by an ArrayList.
	 * 
	 * @param initialSize
	 *     the initial size of the backing list
	 */
	public EBCStrategoStack(int initialSize) {
		list = new ArrayList<IStrategoTerm>(initialSize);
	}
	
	/**
	 * Creates a new stacked backed by the given list.
	 * 
	 * @param list
	 *     the list to use
	 */
	protected EBCStrategoStack(List<IStrategoTerm> list) {
		this.list = list;
	}
	
	/**
	 * @return
	 *     the underlying list of this stack
	 */
	public List<IStrategoTerm> getList() {
		return list;
	}
	
	/**
	 * @return
	 *     the size of this stack
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * Gets the index of the given term.
	 * 
	 * @param t
	 *     the term to get the index of
	 * 
	 * @return
	 *     the index of the given term, or -1 if not in the stack
	 */
	public int getIndex(IStrategoTerm t) {
		Object toFind = toJavaObject(t);
		for (int i = 0; i < list.size(); i++) {
			Object current = toJavaObject(list.get(i));
			if (toFind.equals(current)) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * Gets the index of the given term from the top of the stack.
	 * 
	 * If the given term is at the top of the stack, this method returns 0.
	 * If it is at the bottom of the stack, this method returns stacklength - 1.
	 * 
	 * @param t
	 *     the term to get the index of
	 * 
	 * @return
	 *     the index of the given term, or -1 if not in the stack
	 */
	public int getIndexFromTop(IStrategoTerm t) {
		int index = getIndex(t);
		if (index == -1) {
			return -1;
		}
		
		return list.size() - index - 1;
	}
	
	/**
	 * Gets the item at the given index (0 = bottom).
	 * 
	 * @param index
	 *     the index
	 * 
	 * @return
	 *     the element at the given index
	 * 
	 * @throws IndexOutOfBoundsException
	 *     If there is no element at the given index.
	 */
	public IStrategoTerm getItemAtIndex(int index) {
		return list.get(index);
	}
	
	/**
	 * Gets the item at the given index (0 = top).
	 * 
	 * @param index
	 *     the (top) index
	 * 
	 * @return
	 *     the element at the given index
	 * 
	 * @throws IndexOutOfBoundsException
	 *     If there is no element at the given index.
	 */
	public IStrategoTerm getItemAtTopIndex(int index) {
		return list.get(list.size() - index - 1);
	}
	
	/**
	 * Adds the given term to the top of this stack.
	 * 
	 * @param t
	 *     the term to add
	 * 
	 * @return
	 *     the index (0 = bottom) of the newly added term
	 */
	public int add(IStrategoTerm t) {
		list.add(t);
		return list.size() - 1;
	}
	
	/**
	 * Adds the given term to the top of the stack.
	 * 
	 * @param t
	 *     the term to push
	 */
	public void push(IStrategoTerm t) {
		list.add(t);
	}
	
	/**
	 * Removes the given term from this stack.
	 * 
	 * @param t
	 *     the term to remove
	 * 
	 * @return
	 *     true if the stack changed, false otherwise
	 */
	public boolean remove(IStrategoTerm t) {
		Object needle = toJavaObject(t);
		Iterator<IStrategoTerm> it = list.iterator();
		while (it.hasNext()) {
			Object current = toJavaObject(it.next());
			if (needle.equals(current)) {
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes the top element from the stack and returns it.
	 * 
	 * @return
	 *     the element that was at the top of this stack
	 * 
	 * @throws IndexOutOfBoundsException
	 *     If this stack is empty.
	 */
	public IStrategoTerm pop() {
		return list.remove(list.size() - 1);
	}
	
	/**
	 * Removes the top n elements from the stack.
	 * 
	 * @param n
	 *     the number of elements to remove.
	 * 
	 * @throws IndexOutOfBoundsException
	 *     If this stack is empty.
	 */
	public void pop(int n) {
		for (int i = 0; i < n; i++) {
			pop();
		}
	}
	
	/**
	 * Returns the top element from the stack.
	 * 
	 * @return
	 *     the element that was at the top of this stack
	 * 
	 * @throws IndexOutOfBoundsException
	 *     If this stack is empty.
	 */
	public IStrategoTerm peek() {
		return list.get(list.size() - 1);
	}
	
	/**
	 * Clears the stack.
	 */
	public void clear() {
		list.clear();
	}
	
	/**
	 * Swaps the top element with the element at the given index from the top.
	 * 
	 * For example, swapTop(10) swaps the element at the top with the element 9 positions below it.
	 * 
	 * @param topIndex
	 *     the index
	 * 
	 */
	public void swapTop(int topIndex) {
		final int size = list.size();
		final int indexA = size - topIndex - 1;
		final int indexB = size - 1;
		
		IStrategoTerm termA = list.get(indexA);
		IStrategoTerm termB = list.get(indexB);
		list.set(indexA, termB);
		list.set(indexB, termA);
	}
	
	/**
	 * Creates a (shallow) copy of this stack.
	 * 
	 * @return
	 *     the copy
	 */
	public EBCStrategoStack copy() {
		return new EBCStrategoStack(new ArrayList<>(this.list));
	}
	
	//---------------------------------------------------------------------------------------------
	//Stratego methods
	//---------------------------------------------------------------------------------------------
	
	@Override
	public boolean isList() {
		return false;
	}

	@Override
	public Iterator<IStrategoTerm> iterator() {
		return list.iterator();
	}

	@Override
	public int getSubtermCount() {
		return 0;
	}

	@Override
	public IStrategoTerm getSubterm(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IStrategoTerm[] getAllSubterms() {
		return AbstractTermFactory.EMPTY;
	}

	@Override
	public int getTermType() {
		return BLOB;
	}

	@Override
	public int getStorageType() {
		return MUTABLE;
	}

	@Override
	public IStrategoList getAnnotations() {
		return TermFactory.EMPTY_LIST;
	}

	@Override
	public boolean match(IStrategoTerm second) {
		return second == this;
	}

	@Override
	public int hashCode() {
		return System.identityHashCode(this);
	}

	@Override
	public void prettyPrint(ITermPrinter pp) {
		pp.print(toString());
	}

	@Override
	public String toString() {
		return String.valueOf(hashCode());
	}
	
	@Override
	public String toString(int maxDepth) {
		return toString();
	}

	@Override
	public void writeAsString(Appendable output, int maxDepth) throws IOException {
		output.append(toString());
	}

	/**
	 * Converts a stratego term to a java object. This method works only on 
	 * strings, integers and reals.
	 * 
	 * @param term
	 *     the stratego term
	 * 
	 * @return
	 *     the java object representation of the given term
	 */
	private static Object toJavaObject(IStrategoTerm term) {
		if (term instanceof IStrategoString) {
			return Tools.asJavaString(term);
		} else if (term instanceof IStrategoInt) {
			return Tools.asJavaInt(term);
		} else if (term instanceof IStrategoReal) {
			return Tools.asJavaDouble(term);
		} else {
			return null;
		}
	}
}
