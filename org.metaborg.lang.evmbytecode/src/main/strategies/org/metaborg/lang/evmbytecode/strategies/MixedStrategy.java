package org.metaborg.lang.evmbytecode.strategies;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTuple;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

public abstract class MixedStrategy extends SafeStrategy {
	@Override
	public final IStrategoTerm call(Context context, IStrategoTerm current) throws Exception {
		if (Tools.isTermTuple(current)) {
			return call(context, ((IStrategoTuple) current).getAllSubterms());
		} else {
			return call(context, new IStrategoTerm[] { current });
		}
	}
	
	@Override
	public final IStrategoTerm call(Context context, IStrategoTerm current, IStrategoTerm t1) throws Exception {
		if (Tools.isTermTuple(current)) {
			return call(context, ((IStrategoTuple) current).getAllSubterms(), t1);
		} else {
			return call(context, new IStrategoTerm[] { current }, t1);
		}
	}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm current, IStrategoTerm t1, IStrategoTerm t2) throws Exception {
		if (Tools.isTermTuple(current)) {
			return call(context, ((IStrategoTuple) current).getAllSubterms(), t1, t2);
		} else {
			return call(context, new IStrategoTerm[] { current }, t1, t2);
		}
	}
	
	@Override
	public final IStrategoTerm call(Context context, IStrategoTerm current, Strategy s1) throws Exception {
		if (Tools.isTermTuple(current)) {
			return call(context, ((IStrategoTuple) current).getAllSubterms(), s1);
		} else {
			return call(context, new IStrategoTerm[] { current }, s1);
		}
	}
	
	public IStrategoTerm call(Context context, IStrategoTerm[] terms) throws Exception {
		throw new InvalidArgumentsException();
	}
	
	public IStrategoTerm call(Context context, IStrategoTerm[] terms, IStrategoTerm t1) throws Exception {
		throw new InvalidArgumentsException();
	}
	
	public IStrategoTerm call(Context context, IStrategoTerm[] terms, IStrategoTerm t1, IStrategoTerm t2) throws Exception {
		throw new InvalidArgumentsException();
	}
	
	public IStrategoTerm call(Context context, IStrategoTerm[] terms, Strategy s1) throws Exception {
		throw new InvalidArgumentsException();
	}
}
