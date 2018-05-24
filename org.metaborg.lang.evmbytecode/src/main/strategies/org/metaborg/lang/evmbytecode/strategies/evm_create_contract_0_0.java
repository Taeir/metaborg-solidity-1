package org.metaborg.lang.evmbytecode.strategies;

import java.math.BigInteger;

import org.metaborg.lang.evmbytecode.strategies.evm.CreateContract;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class evm_create_contract_0_0 extends MixedStrategy {
	public static final evm_create_contract_0_0 instance = new evm_create_contract_0_0();
	
	private evm_create_contract_0_0() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm[] terms) throws Exception {
		if (terms.length > 2) throw new InvalidArgumentsException("Expected 1 or 2 arguments");
		
		String byteCode = Tools.asJavaString(terms[0]);
		BigInteger initialGas = BigInteger.ZERO;
		if (terms.length == 2) {
			initialGas = BigIntHelper.toBigInt(terms[1]);
		}
		
		String contractAddress = CreateContract.createContract(byteCode, initialGas);
		return context.getFactory().makeString(contractAddress);
	}
}
