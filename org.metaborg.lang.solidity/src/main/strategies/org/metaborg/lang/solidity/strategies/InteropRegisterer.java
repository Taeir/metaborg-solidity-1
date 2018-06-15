package org.metaborg.lang.solidity.strategies;

import org.strategoxt.lang.JavaInteropRegisterer;
import org.strategoxt.lang.Strategy;

public class InteropRegisterer extends JavaInteropRegisterer {
	public InteropRegisterer() {
		super(new Strategy[] {
				sol_nearest_int_multiple_0_0.instance,
				sol_nearest_uint_multiple_0_0.instance,

				//BigDecimal
				//Parse
				sol_parse_bigdec_0_0.instance,

				//Numeric operations
				sol_bigdec_uminus_0_0.instance,
				sol_bigdec_add_0_0.instance,
				sol_bigdec_sub_0_0.instance,
				sol_bigdec_mult_0_0.instance,
				sol_bigdec_div_0_0.instance,
				sol_bigdec_mod_0_0.instance,
				sol_bigdec_pow_0_0.instance,

				//Shifting operations
				sol_bigdec_lshift_0_0.instance,
				sol_bigdec_rshift_0_0.instance,

				//Bitwise operations
				sol_bigdec_bitor_0_0.instance,
				sol_bigdec_bitand_0_0.instance,
				sol_bigdec_bitxor_0_0.instance,
				sol_bigdec_bitnot_0_0.instance,

				//Convert to bigint
				sol_bigdec_to_bigint_0_0.instance,

				//BigInt
				sol_bigint_mult_0_0.instance,
				sol_bigint_to_hex_0_0.instance,
				sol_hexadecimal_int_to_bigint_0_0.instance,
				
				//EBC Fallback
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
				ebc_generate_function_hash_0_0.instance
		});
	}
}
