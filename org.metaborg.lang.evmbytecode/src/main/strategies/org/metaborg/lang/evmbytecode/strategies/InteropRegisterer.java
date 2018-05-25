package org.metaborg.lang.evmbytecode.strategies;

import org.strategoxt.lang.JavaInteropRegisterer;
import org.strategoxt.lang.Strategy;

public class InteropRegisterer extends JavaInteropRegisterer {
    public InteropRegisterer() {
        super(new Strategy[] {
        		evm_generate_function_hash_0_0.instance,
        		evm_create_contract_0_0.instance,
        		evm_test_0_0.instance
        });
    }
}
