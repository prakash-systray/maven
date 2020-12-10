package com.acculytixs.wayuparty.util;
import org.apache.commons.lang.RandomStringUtils;

public class RandomCodeHelper {

	public static String generateRandomUserCode() {
		String userCode = RandomStringUtils.randomAlphanumeric(8);
		return userCode;
	}
	
	public static String generateRandomOrgCode() {
		String orgCode = RandomStringUtils.randomAlphanumeric(4);
		return orgCode;
	}
	
	public static String generateRandomOrgLocationCode() {
		String orgLocationCode = RandomStringUtils.randomAlphanumeric(5);
		return orgLocationCode;
	}
	
	public static String generateRandomVerificationCode() {
		String verificatinCode = RandomStringUtils.randomAlphabetic(5);
		return verificatinCode;
	}
	
	public static String generateRandomPassword() {
		String password = RandomStringUtils.randomAlphabetic(6);
		return password;
	}
	
	public static String generateUUID() {
		String UUID = RandomStringUtils.randomAlphabetic(15);
		return UUID;
	}
	
	public static String generateNotificationUUID() {
		String UUID = RandomStringUtils.randomAlphabetic(32);
		return UUID;
	}
	
	public static String generateRandomUUID() {
		String UUID = RandomStringUtils.randomAlphanumeric(8);
		return UUID;
	}
	
	public static String generateSubmissionXlsRandomCode() {
		String xlsCode = RandomStringUtils.randomNumeric(4);
		return xlsCode;
	}
	
	public static String generateRandomUniquUserName() {
		String orgCode = RandomStringUtils.randomAlphanumeric(6);
		return orgCode;
	}
	
	public static String generateRandomExpensesIdCode() {
		String expensesIdCode = RandomStringUtils.randomAlphanumeric(6);
		return expensesIdCode;
	}
	
	public static String generateRandomTransactionOrderId() {
		String UUID = RandomStringUtils.randomNumeric(8);
		return UUID;
	}
	
	public static String generateRandomTaxId() {
		String UUID = RandomStringUtils.randomAlphanumeric(8);
		return UUID;
	}
	
	public static String generateRandomBulkSubmissionId() {
		String UUID = RandomStringUtils.randomNumeric(5);
		return UUID;
	}
	
	public static String generateRandomCustomToken() {
		String UUID = RandomStringUtils.randomAlphanumeric(15);
		return UUID;
	}
	
	public static String generateRandomDocNumber() {
		String UUID = RandomStringUtils.randomNumeric(10);
		return UUID;
	}
}
