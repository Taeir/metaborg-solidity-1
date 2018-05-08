package org.metaborg.lang.solidity.strategies;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class BigIntHelper {
	/**
	 * Converts the given stratego term (int/string) to a BigInteger.
	 * 
	 * @param term
	 *   the term to convert
	 *   
	 * @return
	 *   the corresponding BigInteger
	 */
	public static BigInteger toBigInt(IStrategoTerm term) {
		if (Tools.isTermInt(term)) {
			return BigInteger.valueOf(Tools.asJavaInt(term));
		} else {
			return new BigInteger(Tools.asJavaString(term));
		}
	}
	
	/**
	 * Converts the given stratego term (int/real/string) to a BigDecimal.
	 * 
	 * @param term
	 *   the term to convert
	 *   
	 * @return
	 *   the corresponding BigDecimal
	 */
	public static BigDecimal toBigDecimal(IStrategoTerm term) {
		if (Tools.isTermInt(term)) {
			return BigDecimal.valueOf(Tools.asJavaInt(term));
		} else if (Tools.isTermReal(term)) {
			return BigDecimal.valueOf(Tools.asJavaDouble(term));
		} else {
			return new BigDecimal(Tools.asJavaString(term));
		}
	}
}
