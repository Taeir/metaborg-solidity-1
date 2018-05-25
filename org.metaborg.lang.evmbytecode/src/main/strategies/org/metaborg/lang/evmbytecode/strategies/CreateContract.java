package org.metaborg.lang.evmbytecode.strategies;

import static org.metaborg.lang.evmbytecode.strategies.EVMMain.*;

import java.math.BigInteger;

import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;

/**
 * Helper class for creating contracts.
 */
public class CreateContract {
	private static final int SLEEP_DURATION = 1000;
	private static final int ATTEMPTS = 10;
	
	/**
	 * Creates a contract with the given bytecode, without sending any money to it.
	 * 
	 * @param byteCode
	 *     the bytecode of the contract
	 * 
	 * @return
	 *     the address of the created contract
	 * 
	 * @throws Exception
	 *     if something goes wrong with the creation.
	 */
	public static String createContract(String byteCode) throws Exception {
		return createContract(byteCode, BigInteger.ZERO);
	}
	
	/**
	 * Creates a contract with the given bytecode, also sending the given amount of gas to it.
	 * 
	 * @param byteCode
	 *     the bytecode of the contract
	 * @param contractGas
	 *     the amount of gas to send to the contract
	 * 
	 * @return
	 *     the address of the created contract
	 * 
	 * @throws Exception
	 *     if something goes wrong with the creation.
	 */
	public static String createContract(String byteCode, BigInteger contractGas) throws Exception {
		return createContract(byteCode, contractGas, SLEEP_DURATION, ATTEMPTS);
	}
	
	/**
	 * Creates a contract with the given bytecode, also sending the given amount of gas to it.
	 * 
	 * @param byteCode
	 *     the bytecode of the contract
	 * @param contractGas
	 *     the amount of gas to send to the contract
	 * @param sleepDuration
	 *     the amount of time to wait between attempts
	 * @param attempts
	 *     the number of attempts waiting for the transaction receipt
	 * 
	 * @return
	 *     the address of the created contract
	 * 
	 * @throws Exception
	 *     if something goes wrong with the creation.
	 */
	public static String createContract(String byteCode, BigInteger contractGas, int sleepDuration, int attempts) throws Exception {
		BigInteger gasPrice = web3.ethGasPrice().sendAsync().get().getGasPrice();

		//Using a raw transaction
		RawTransaction rawTransaction = RawTransaction.createContractTransaction(
				getNonce(getAddress()),
				gasPrice,
				DefaultGasProvider.GAS_LIMIT,
				contractGas,
				"0x" + byteCode);

		//Transaction.createContractTransaction(from, nonce, gasPrice, init)
		//web3.ethSendTransaction(transaction)

		//Send transaction
		String transactionHash = sendRaw(rawTransaction);

		//Get contract address
		TransactionReceipt transactionReceipt = waitForTransactionReceipt(transactionHash, sleepDuration, attempts);
		String contractAddress = transactionReceipt.getContractAddress();
		
		return contractAddress;
	}
}
