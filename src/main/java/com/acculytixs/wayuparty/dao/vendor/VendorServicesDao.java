package com.acculytixs.wayuparty.dao.vendor;

import java.util.List;

import com.acculytixs.wayuparty.dto.services.ServicesInfoDTO;
import com.acculytixs.wayuparty.dto.services.VendorServicesDTO;
import com.acculytixs.wayuparty.dto.vendor.ServiceSubCategoryDTO;
import com.acculytixs.wayuparty.dto.vendor.ServicesCategoryDTO;
import com.acculytixs.wayuparty.entity.services.VendorMasterService;
import com.acculytixs.wayuparty.entity.services.WayupartyServiceSubCategory;
import com.acculytixs.wayuparty.util.ResponseList;

public interface VendorServicesDao {

	List<ServicesCategoryDTO> getServiceCategoriesByServiceId(Long serviceId) throws Exception;
	
	WayupartyServiceSubCategory getWayupartyServiceSubCategoryByUUID(String uuid) throws Exception;
	
	void saveServiceSubCategory(WayupartyServiceSubCategory wayupartyServiceSubCategory) throws Exception;
	
	List<ServiceSubCategoryDTO> getVendorServiceSubCategories(Long categoryId, Long vendorId) throws Exception;
	
	void deleteServiceSubCategory(WayupartyServiceSubCategory wayupartyServiceSubCategory) throws Exception;
	
	void saveVendorMasterService(VendorMasterService vendorMasterService) throws Exception;
	
	ResponseList<VendorServicesDTO> getVendorServicesList(Long serviceId, Long vendorId) throws Exception;
	
	VendorServicesDTO getVendorServiceDetails(String serviceUUID) throws Exception;
	
	boolean isServiceExistsBySubCategoryIdAndVendorId(String serviceStartDate, String serviceEndDate, Long subCategoryId, Long vendorId) throws Exception;
	
	boolean isSavedServiceExistsBySubCategoryIdAndVendorId(String serviceStartDate, String serviceEndDate, Long subCategoryId, Long vendorId) throws Exception;
	
	ServicesInfoDTO getServicesList(String vendorUUID) throws Exception;
	
	List<VendorServicesDTO> getVendorCategoryServicesList(Long categoryId, Long vendorId) throws Exception;
	
	boolean isServiceExistsByCategoryIdAndVendorId(String serviceStartDate, String serviceEndDate, Long categoryId, Long vendorId) throws Exception;
	
	VendorServicesDTO getVendorServiceDetailsByMasterServiceUUID(String serviceUUID) throws Exception;
	
	Long getVendorMasterServiceIdByUUID(String serviceUUID) throws Exception;
	
	String getServiceImageByUUID(String serviceUUID) throws Exception;
	
	boolean isSavedServiceExistsByCategoryIdAndVendorId(String serviceStartDate, String serviceEndDate, Long categoryId, Long vendorId) throws Exception;
	
	String getPackageMenuDetailsByUUID(String serviceUUID) throws Exception;
	
	VendorMasterService geVendorMasterServiceByUUID(String uuid) throws Exception;
}
