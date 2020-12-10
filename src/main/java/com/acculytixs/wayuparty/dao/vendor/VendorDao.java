package com.acculytixs.wayuparty.dao.vendor;

import java.util.List;

import com.acculytixs.wayuparty.dto.services.ServicesDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorBankDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorCustomDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorProfileCategoriesDTO;
import com.acculytixs.wayuparty.entity.vendor.VendorBankAccount;
import com.acculytixs.wayuparty.entity.vendor.Vendors;

public interface VendorDao {

	void saveVendor(Vendors vendors) throws Exception;
	
	List<VendorDTO> getRegisteredVendors() throws Exception;
	
	List<VendorDTO> getRegisteredRestaurantsList(Integer offset, Integer limit) throws Exception;
	
	VendorDTO getVendorDetails(String vendorUUID) throws Exception;
	
	Vendors getVendorByUUID(String vendorUUID) throws Exception;
	
	VendorBankDetailsDTO getVendorBankDetails(Long vendorId) throws Exception;
	
	VendorBankAccount getVendorBankDetailsByVendorUUID(String vendorUUID) throws Exception;
	
	void saveVendorBankAccountDetails(VendorBankAccount vendorBankAccount) throws Exception;
	
	List<VendorProfileCategoriesDTO> getAllcategoriesByType(String categoryType) throws Exception;
	
	VendorProfileCategoriesDTO getVendorProflieCategories(String vendorUUID) throws Exception;
	
	VendorCustomDetailsDTO getVendorCustomDetailsDTO(String vendorUUID) throws Exception;
	
	Long getVendorIdByUUID(String vendorUUID) throws Exception;
	
	boolean isVendorExistedByEmailId(String emailId) throws Exception;
	
	boolean isVendorExistedByPhoneNumber(String mobileNumber) throws Exception;
	
	boolean isVendorExistedByVendorCode(String vendorCode) throws Exception;
	
	List<ServicesDTO> getVendorServices();
	
	Long getServiceIdByUUID(String serviceUUID) throws Exception;
	
	Long getServiceCategoryIdByUUID(String categoryUUID) throws Exception;
	
	String getCategoryNameByCategoryId(Long categoryId) throws Exception;
	
}
