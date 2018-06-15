package org.metaborg.lang.evmbytecode.strategies;

import org.strategoxt.lang.JavaInteropRegisterer;
import org.strategoxt.lang.Strategy;

public class InteropRegisterer extends JavaInteropRegisterer {
    public InteropRegisterer() {
        super(new Strategy[] {
        		//Stack strategies
        		ebc_stack_create_0_0.instance,
        		ebc_stack_copy_0_1.instance,
        		ebc_stack_length_0_1.instance,
        		ebc_stack_get_index_0_1.instance,
        		ebc_stack_push_0_1.instance,
        		ebc_stack_pop_0_1.instance,
        		ebc_stack_remove_0_1.instance,
        		ebc_stack_clear_0_1.instance,
        		
        		//Initialization
        		ebc_initial_instructions_0_1.instance,
        		
        		//Retaining
        		ebc_retain_value_at_0_1.instance,
        		ebc_retain_variable_0_1.instance,
        		
        		//Function hash
        		ebc_generate_function_hash_0_0.instance,
        		
        		//Virtual machine: contract creation and execution
        		evm_create_contract_0_0.instance,
        		evm_test_0_0.instance
        });
    }
}
