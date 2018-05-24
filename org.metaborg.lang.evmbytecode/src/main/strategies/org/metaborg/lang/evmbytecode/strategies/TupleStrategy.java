package org.metaborg.lang.evmbytecode.strategies;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTuple;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

public abstract class TupleStrategy extends SafeStrategy {
	@Override
	public final IStrategoTerm call(Context context, IStrategoTerm current) {
		if (Tools.isTermTuple(current)) {
			return call(context, ((IStrategoTuple) current).getAllSubterms());
		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	@Override
	public final IStrategoTerm call(Context context, IStrategoTerm current, IStrategoTerm t1) {
		if (Tools.isTermTuple(current)) {
			return call(context, ((IStrategoTuple) current).getAllSubterms(), t1);
		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm current, IStrategoTerm t1, IStrategoTerm t2) {
		if (Tools.isTermTuple(current)) {
			return call(context, ((IStrategoTuple) current).getAllSubterms(), t1, t2);
		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	@Override
	public final IStrategoTerm call(Context context, IStrategoTerm current, Strategy s1) {
		if (Tools.isTermTuple(current)) {
			return call(context, ((IStrategoTuple) current).getAllSubterms(), s1);
		} else {
			throw new InvalidArgumentsException();
		}
	}
	
	public IStrategoTerm call(Context context, IStrategoTerm[] terms) {
		throw new InvalidArgumentsException();
	}
	
	public IStrategoTerm call(Context context, IStrategoTerm[] terms, IStrategoTerm t1) {
		throw new InvalidArgumentsException();
	}
	
	public IStrategoTerm call(Context context, IStrategoTerm[] terms, IStrategoTerm t1, IStrategoTerm t2) {
		throw new InvalidArgumentsException();
	}
	
	public IStrategoTerm call(Context context, IStrategoTerm[] terms, Strategy s1) {
		throw new InvalidArgumentsException();
	}
}
