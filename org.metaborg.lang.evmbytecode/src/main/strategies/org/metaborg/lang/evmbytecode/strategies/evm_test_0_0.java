package org.metaborg.lang.evmbytecode.strategies;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class evm_test_0_0 extends SafeStrategy {
	public static final evm_test_0_0 instance = new evm_test_0_0();
	
	private evm_test_0_0() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm current) throws Exception {
		try {
			EVMMain.init();
		} catch (Exception ex) {
			System.err.println("Error initializing EVMMain: ");
			ex.printStackTrace();
			throw ex;
		}

		try {
			EVMMain.test();
		} catch (Exception ex) {
			System.err.println("EVMMain test failed: ");
			ex.printStackTrace();
			throw ex;
		}
		
		EVMMain.destruct();
		return current;
	}
}
