package com.acculytixs.wayuparty.services.vendor;

import java.util.List;

import com.acculytixs.wayuparty.dto.services.ServicesInfoDTO;
import com.acculytixs.wayuparty.dto.services.VendorBottleServiceDTO;
import com.acculytixs.wayuparty.dto.services.VendorEntryServiceDTO;
import com.acculytixs.wayuparty.dto.services.VendorPackageDTO;
import com.acculytixs.wayuparty.dto.services.VendorServicesDTO;
import com.acculytixs.wayuparty.dto.services.VendorTableServiceDTO;
import com.acculytixs.wayuparty.dto.vendor.BottleCategoryInfo;
import com.acculytixs.wayuparty.dto.vendor.ServiceSubCategoryDTO;
import com.acculytixs.wayuparty.dto.vendor.ServicesCategoryDTO;
import com.acculytixs.wayuparty.util.ResponseList;

public interface VendorServicesService {

	List<ServicesCategoryDTO> getServiceCategoriesByServiceId(Long serviceId) throws Exception;
	
	String saveBottleCategories(BottleCategoryInfo bottleCategoryInfo) throws Exception;
	
	List<ServiceSubCategoryDTO> getVendorServiceSubCategories(Long categoryId, Long vendorId) throws Exception;
	
	String saveBottleService(VendorBottleServiceDTO vendorBottleServiceDTO, Long loginUserId) throws Exception;
	
	ResponseList<VendorServicesDTO> getVendorServicesList(Long serviceId, Long vendorId) throws Exception;
	
	VendorServicesDTO getVendorServiceDetails(String serviceUUID) throws Exception;
	
	boolean isServiceExistsBySubCategoryIdAndVendorId(String serviceStartDate, String serviceEndDate, Long subCategoryId, Long vendorId) throws Exception;
	
	boolean isSavedServiceExistsBySubCategoryIdAndVendorId(String serviceStartDate, String serviceEndDate, Long subCategoryId, Long vendorId) throws Exception;
	
	ServicesInfoDTO getServicesList(String vendorUUID) throws Exception;
	
	List<VendorServicesDTO> getVendorCategoryServicesList(Long categoryId, Long vendorId) throws Exception;
	
	boolean isServiceExistsByCategoryIdAndVendorId(String serviceStartDate, String serviceEndDate, Long categoryId, Long vendorId) throws Exception;
	
	String saveTableService(VendorTableServiceDTO vendorTableServiceDTO, Long loginUserId) throws Exception;
	
	VendorServicesDTO getVendorServiceDetailsByMasterServiceUUID(String serviceUUID) throws Exception;
	
	Long getVendorMasterServiceIdByUUID(String serviceUUID) throws Exception;
	
	boolean isSavedServiceExistsByCategoryIdAndVendorId(String serviceStartDate, String serviceEndDate, Long categoryId, Long vendorId) throws Exception;
	
	String saveEntryService(VendorEntryServiceDTO vendorEntryServiceDTO, Long loginUserId) throws Exception;
	
	String savePackageService(VendorPackageDTO vendorPackageDTO, String packageOfferingMenu, Long loginUserId) throws Exception;
	
}
