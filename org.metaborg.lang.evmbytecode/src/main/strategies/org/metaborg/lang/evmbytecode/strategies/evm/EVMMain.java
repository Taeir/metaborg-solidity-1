package org.metaborg.lang.evmbytecode.strategies.evm;

import java.math.BigInteger;
import java.util.Optional;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

public class EVMMain {
	public static Web3j web3;
	public static Credentials credentials;

	/**
	 * Initializes the connection with the ethereum blockchain.
	 * 
	 * @throws Exception
	 *     if no connection can be established, or if the keystore cannot be loaded.
	 */
	public static void init() throws Exception {
		web3 = Web3j.build(new HttpService());
		credentials = WalletUtils.loadCredentials("password", "D:\\ethereum\\keystore2");
	}
	
	public static void destruct() {
		if (web3 != null) {
			web3.shutdown();
			web3 = null;
			credentials = null;
		}
	}

	public static void test() throws Exception {
		Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
		String clientVersion = web3ClientVersion.getWeb3ClientVersion();
		System.out.println("Client version: " + clientVersion);
		
		
		BigInteger gasPrice = web3.ethGasPrice().send().getGasPrice();
		System.out.println("Gas price: " + gasPrice);
	}
	
	/**
	 * @return
	 *     the address of our wallet
	 */
	public static String getAddress() {
		return credentials.getAddress();
	}

	/**
	 * Gets the nonce for the given address.
	 * 
	 * @param address
	 *     the address
	 * 
	 * @return
	 *     the nonce of the given address
	 * 
	 * @throws Exception
	 *     if something went wrong.
	 */
	public static BigInteger getNonce(String address) throws Exception {
		EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(
				address, DefaultBlockParameterName.LATEST).sendAsync().get();

		BigInteger nonce = ethGetTransactionCount.getTransactionCount();
		return nonce;
	}

	/**
	 * Sends the given raw transaction.
	 * 
	 * Use {@link #waitForTransactionReceipt(String, int, int)} to get the transaction receipt.
	 * 
	 * @param rawTransaction
	 *     the transaction to send
	 * 
	 * @return
	 *     the hash of the given transaction
	 * 
	 * @throws Exception
	 */
	public static String sendRaw(RawTransaction rawTransaction) throws Exception {
		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
		String hexValue = Numeric.toHexString(signedMessage);
		EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).sendAsync().get();
		String transactionHash = ethSendTransaction.getTransactionHash();
		return transactionHash;
	}

	/**
	 * Waits for the transaction receipt of the given transaction hash.
	 * 
	 * If there is still no receipt after the given number of attempts, this method throws an
	 * exception. 
	 * 
	 * @param transactionHash
	 *     the hash of the transaction to get the receipt of
	 * @param sleepDuration
	 *     the amount of milliseconds to sleep between attempts
	 * @param attempts
	 *     the maximum amount of times to try
	 * 
	 * @return
	 *     the receipt of the given transaction (hash)
	 * 
	 * @throws Exception
	 *     if something goes wrong, or if the maximum number of attempts is reached.
	 */
	public static TransactionReceipt waitForTransactionReceipt(
			String transactionHash, int sleepDuration, int attempts) throws Exception {
		Optional<TransactionReceipt> transactionReceiptOptional =
				getTransactionReceipt(transactionHash, sleepDuration, attempts);

		if (!transactionReceiptOptional.isPresent()) {
			throw new RuntimeException("Transaction receipt not generated after " + attempts + " attempts");
		}

		return transactionReceiptOptional.get();
	}

	private static Optional<TransactionReceipt> getTransactionReceipt(
			String transactionHash, int sleepDuration, int attempts) throws Exception {

		Optional<TransactionReceipt> receiptOptional = sendTransactionReceiptRequest(transactionHash);
		for (int i = 0; i < attempts; i++) {
			if (!receiptOptional.isPresent()) {
				Thread.sleep(sleepDuration);
				receiptOptional = sendTransactionReceiptRequest(transactionHash);
			} else {
				break;
			}
		}

		return receiptOptional;
	}

	public static Optional<TransactionReceipt> sendTransactionReceiptRequest(String transactionHash) throws Exception {
		EthGetTransactionReceipt transactionReceipt = web3.ethGetTransactionReceipt(transactionHash).sendAsync().get();

		return transactionReceipt.getTransactionReceipt();
	}
}
