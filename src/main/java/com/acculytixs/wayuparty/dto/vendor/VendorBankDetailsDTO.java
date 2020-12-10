package com.acculytixs.wayuparty.dto.vendor;

import java.io.Serializable;

import com.acculytixs.wayuparty.annotations.Required;

public class VendorBankDetailsDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Required(desc="beneficiaryName")
	private String beneficiaryName;
	
	@Required(desc="bankName")
	private String bankName;
	
	@Required(desc="bankBranch")
	private String bankBranch;
	
	@Required(desc="accountNumber")
	private String accountNumber;
	
	@Required(desc="bankCode")
	private String bankCode;
	
	@Required(desc="accountType")
	private String accountType;
	
	private String vendorUUID;
	
	
	
	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getVendorUUID() {
		return vendorUUID;
	}

	public void setVendorUUID(String vendorUUID) {
		this.vendorUUID = vendorUUID;
	}
	

}
