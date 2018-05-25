package org.metaborg.lang.evmbytecode.strategies;

import org.strategoxt.lang.JavaInteropRegisterer;
import org.strategoxt.lang.Strategy;

public class InteropRegisterer extends JavaInteropRegisterer {
    public InteropRegisterer() {
        super(new Strategy[] {
        		evm_create_contract_0_0.instance
        });
        
//        initEVM();
//        destructEVM();
    }
    
//    private void initEVM() {
//		try {
//			EVMMain.init();
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
//	private void destructEVM() {
//		EVMMain.destruct();
//	}
}
