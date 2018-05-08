package org.metaborg.lang.solidity.strategies;

import org.strategoxt.lang.JavaInteropRegisterer;
import org.strategoxt.lang.Strategy;

public class InteropRegisterer extends JavaInteropRegisterer {
    public InteropRegisterer() {
        super(new Strategy[] {
        		sol_nearest_int_multiple_0_0.instance,
        		sol_nearest_uint_multiple_0_0.instance,
        		sol_bigint_mult_0_1.instance,
        		sol_bigint2_mult_0_2.instance,
        		sol_bigdec_mult_0_1.instance,
        		sol_bigdec2_mult_0_2.instance,
        		
        		sol_bigdec_to_bigint_0_0.instance,
        		sol_scientific_int_to_bigint_0_0.instance,
        		sol_scientific_decimal_to_bigdec_0_0.instance,
        		sol_hexadecimal_int_to_bigint_0_0.instance
        });
    }
}
