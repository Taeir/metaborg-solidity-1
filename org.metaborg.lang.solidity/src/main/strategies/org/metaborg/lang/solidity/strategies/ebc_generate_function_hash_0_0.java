package org.metaborg.lang.solidity.strategies;

import java.security.MessageDigest;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class ebc_generate_function_hash_0_0 extends MixedStrategy {
	public static final ebc_generate_function_hash_0_0 instance = new ebc_generate_function_hash_0_0();
	
	private ebc_generate_function_hash_0_0() {}
	
	@Override
	public IStrategoTerm call(Context context, IStrategoTerm[] terms) throws Exception {
		if (terms.length != 2) {
			log("evm_generate_function_hash_0_0: EXPECTED 2 ARGUMENTS!");
			throw new InvalidArgumentsException("Expected 2 arguments");
		}
		
		String name = Tools.asJavaString(terms[0]);
		
		IStrategoList list = ((IStrategoList) terms[1]);
		String argumentTypes = StreamSupport
				.stream(list.spliterator(), false)
				.map(Tools::asJavaString)
				.map(s -> s.replace(" ", ""))
				.collect(Collectors.joining(",", "(", ")"));
		
		//functionName(int8,int256)
		String signatureForHash = name + argumentTypes;
		log("evm_generate_function_hash_0_0: Going to hash signature: \"" + signatureForHash + "\"");

		String hash;
		MessageDigest md = new Keccak256();
		try {
			md.update(signatureForHash.getBytes());
			byte[] digest = md.digest();
			
			hash = HexUtils.getHex(digest);
		} catch (Exception ex) {
			log("evm_generate_function_hash_0_0: exception: " + ex);
			hash = "00000000";
		}
		
		log("evm_generate_function_hash_0_0: Hash: " + hash.substring(0, 8));
		
		return context.getFactory().makeString(hash.substring(0, 8));
	}
}
