package org.metaborg.lang.solidity.strategies;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

import com.google.common.io.Files;

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
			log(ex);
			throw ex;
		} catch (Exception ex) {
			log(ex);
			return null;
		}
	}
	
	@Override
	public final IStrategoTerm invoke(Context context, IStrategoTerm current, IStrategoTerm t1) {
		try {
			return call(context, current, t1);
		} catch (InvalidArgumentsException ex) {
			log(ex);
			throw ex;
		} catch (Exception ex) {
			log(ex);
			return null;
		}
	}
	
	@Override
	public final IStrategoTerm invoke(Context context, IStrategoTerm current, IStrategoTerm t1, IStrategoTerm t2) {
		try {
			return call(context, current, t1, t2);
		} catch (InvalidArgumentsException ex) {
			log(ex);
			throw ex;
		} catch (Exception ex) {
			log(ex);
			return null;
		}
	}
	
	@Override
	public IStrategoTerm invoke(Context context, IStrategoTerm current, IStrategoTerm t1, IStrategoTerm t2, IStrategoTerm t3) {
		try {
			return call(context, current, t1, t2, t3);
		} catch (InvalidArgumentsException ex) {
			log(ex);
			throw ex;
		} catch (Exception ex) {
			log(ex);
			return null;
		}
	}
	
	@Override
	public final IStrategoTerm invoke(Context context, IStrategoTerm current, Strategy s1) {
		try {
			return call(context, current, s1);
		} catch (InvalidArgumentsException ex) {
			log(ex);
			throw ex;
		} catch (Exception ex) {
			log(ex);
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
	
	/**
	 * Logs the given message.
	 * 
	 * @param msg
	 *     the message to log
	 */
	protected void log(String msg) {
		try {
			Files.append(msg + "\n", new File("stratego-log.txt"), StandardCharsets.UTF_8);
		} catch (Exception ioex) {
			//Swallow
		}
	}
	
	/**
	 * Logs the given message and exception.
	 * 
	 * @param msg
	 *     the message to log
	 * @param ex
	 *     the exception to log
	 */
	protected void log(String msg, Exception ex) {
		try {
			Files.append(msg + ": " + ex.toString() + "\n", new File("stratego-log.txt"), StandardCharsets.UTF_8);
		} catch (Exception ioex) {
			//Swallow
		}
	}
	
	/**
	 * Logs the given exception.
	 * 
	 * @param ex
	 *     the exception to log
	 */
	protected void log(Exception ex) {
		try {
			Files.append(ex.toString() + "\n", new File("stratego-log.txt"), StandardCharsets.UTF_8);
		} catch (Exception ioex) {
			//Swallow
		}
	}
	
	protected class InvalidArgumentsException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public InvalidArgumentsException() {}
		public InvalidArgumentsException(String msg) {
			super(msg);
		}
	}
}
