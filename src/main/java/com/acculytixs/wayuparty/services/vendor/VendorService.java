package com.acculytixs.wayuparty.services.vendor;

import java.util.List;

import com.acculytixs.wayuparty.dto.services.ServicesDTO;
import com.acculytixs.wayuparty.dto.vendor.AddVendorDTO;
import com.acculytixs.wayuparty.dto.vendor.TimeSchedulerInfo;
import com.acculytixs.wayuparty.dto.vendor.VendorAddressDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorBankDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorCustomDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorImagesDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorProfileCategoriesDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorWorkingHoursDTO;
import com.acculytixs.wayuparty.entity.vendor.Vendors;

public interface VendorService {

	void saveVendor(AddVendorDTO addVendorDTO) throws Exception;
	
	List<VendorDTO> getRegisteredVendors() throws Exception;
	
	List<VendorDTO> getRegisteredRestaurantsList(Integer offset, Integer limit) throws Exception;
	
	VendorDTO getVendorDetails(String vendorUUID) throws Exception;
	
	void saveVendorBasicDetails(VendorDetailsDTO vendorDetailsDTO) throws Exception;
	
	VendorBankDetailsDTO getVendorBankDetails(String vendorUUID) throws Exception;
	
	String saveVendorBankAccountDetails(VendorBankDetailsDTO vendorBankDetailsDTO) throws Exception;
	
	String saveVendorAddressDetails(VendorAddressDTO vendorAddressDTO) throws Exception;
	
	List<VendorProfileCategoriesDTO> getAllcategoriesByType(String categoryType) throws Exception;
	
	String saveVendorProfileCategoriesDetails(VendorProfileCategoriesDTO vendorProfileCategoriesDTO) throws Exception;
	
	Vendors getVendorByUUID(String vendorUUID) throws Exception;
	
	VendorProfileCategoriesDTO getVendorProflieCategories(String vendorUUID) throws Exception;
	
	String saveVendorWorkingHoursDetails(VendorWorkingHoursDTO vendorsWorkingHoursDTO, String vendorUUID) throws Exception;
	
	List<TimeSchedulerInfo> getVendorWorkingHoursDetails(String vendorUUID) throws Exception;
	
	VendorCustomDetailsDTO getVendorTermsAndCondtionsDetails(String vendorUUID) throws Exception;
	
	String saveVendorTermsAndConditions(VendorCustomDetailsDTO vendorCustomDetailsDTO, String vendorUUID) throws Exception;
	
	Long getVendorIdByUUID(String vendorUUID) throws Exception;
	
	String saveVendorImages(VendorImagesDTO vendorImagesDTO) throws Exception;
	
	boolean isVendorExistedByEmailId(String emailId) throws Exception;
	
	boolean isVendorExistedByPhoneNumber(String mobileNumber) throws Exception;
	
	boolean isVendorExistedByVendorCode(String vendorCode) throws Exception;
	
	List<ServicesDTO> getVendorServices();
	
	Long getServiceIdByUUID(String serviceUUID) throws Exception;
	
	Long getServiceCategoryIdByUUID(String categoryUUID) throws Exception;
}
