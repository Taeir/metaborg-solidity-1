package org.metaborg.lang.evmbytecode.strategies;

import java.math.BigInteger;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class evm_create_contract_0_0 extends MixedStrategy {
	public static final evm_create_contract_0_0 instance = new evm_create_contract_0_0();
	
	private evm_create_contract_0_0() {}
	
//	private void initAndTest() {
//		try {
//			if (!EVMMain.init()) return;
//		} catch (Exception ex) {
//			System.err.println("Error initializing EVMMain: ");
//			ex.printStackTrace();
//			return;
//		}
//
//		try {
//			EVMMain.test();
//		} catch (Exception ex) {
//			System.err.println("EVMMain test failed: ");
//			ex.printStackTrace();
//		}
//	}
//	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm[] terms) throws Exception {
		if (terms.length > 2) throw new InvalidArgumentsException("Expected 1 or 2 arguments");
		
//		//Initialize and test
//		initAndTest();
//		
//		String byteCode = Tools.asJavaString(terms[0]);
//		BigInteger initialGas = BigInteger.ZERO;
//		if (terms.length == 2) {
//			initialGas = BigIntHelper.toBigInt(terms[1]);
//		}
//		
//		String contractAddress = CreateContract.createContract(byteCode, initialGas);
//		IStrategoString str = context.getFactory().makeString(contractAddress);
//		
//		//TODO Don't destruct every time.
//		EVMMain.destruct();
//		return str;
		return context.getFactory().makeString("WIP");
	}
}
