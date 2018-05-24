package org.metaborg.lang.evmbytecode.strategies;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

/**
 * Strategy that fails when an exception is thrown, instead of crashing stratego.
 */
public abstract class SafeStrategy extends Strategy {
	public SafeStrategy() { }
	
	@Override
	public final IStrategoTerm invoke(Context context, IStrategoTerm current) {
		try {
			return call(context, current);
		} catch (InvalidArgumentsException ex) {
			throw ex;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@Override
	public final IStrategoTerm invoke(Context context, IStrategoTerm current, IStrategoTerm t1) {
		try {
			return call(context, current, t1);
		} catch (InvalidArgumentsException ex) {
			throw ex;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@Override
	public final IStrategoTerm invoke(Context context, IStrategoTerm current, IStrategoTerm t1, IStrategoTerm t2) {
		try {
			return call(context, current, t1, t2);
		} catch (InvalidArgumentsException ex) {
			throw ex;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@Override
	public IStrategoTerm invoke(Context context, IStrategoTerm current, IStrategoTerm t1, IStrategoTerm t2, IStrategoTerm t3) {
		try {
			return call(context, current, t1, t2, t3);
		} catch (InvalidArgumentsException ex) {
			throw ex;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@Override
	public final IStrategoTerm invoke(Context context, IStrategoTerm current, Strategy s1) {
		try {
			return call(context, current, s1);
		} catch (InvalidArgumentsException ex) {
			throw ex;
		} catch (Exception ex) {
			return null;
		}
	}
	
	public IStrategoTerm call(Context context, IStrategoTerm current) throws Exception {
		throw new InvalidArgumentsException();
	}
	
	public IStrategoTerm call(Context context, IStrategoTerm current, IStrategoTerm t1) throws Exception {
		throw new InvalidArgumentsException();
	}
	
	public IStrategoTerm call(Context context, IStrategoTerm current, IStrategoTerm t1, IStrategoTerm t2) throws Exception {
		throw new InvalidArgumentsException();
	}
	
	public IStrategoTerm call(Context context, IStrategoTerm current, IStrategoTerm t1, IStrategoTerm t2, IStrategoTerm t3) throws Exception {
		throw new InvalidArgumentsException();
	}
	
	public IStrategoTerm call(Context context, IStrategoTerm current, Strategy s1) throws Exception {
		throw new InvalidArgumentsException();
	}
	
	protected class InvalidArgumentsException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public InvalidArgumentsException() {}
		public InvalidArgumentsException(String msg) {
			super(msg);
		}
	}
}
