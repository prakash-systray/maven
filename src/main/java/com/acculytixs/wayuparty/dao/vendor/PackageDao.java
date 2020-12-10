package com.acculytixs.wayuparty.dao.vendor;

import java.util.List;

import com.acculytixs.wayuparty.dto.services.PackageMenuItemsDTO;
import com.acculytixs.wayuparty.dto.services.PackageMenuOfferingDTO;
import com.acculytixs.wayuparty.entity.services.PackageMenuOffering;
import com.acculytixs.wayuparty.entity.services.PackageMenuOfferingItems;

public interface PackageDao {

	List<PackageMenuOfferingDTO> getPackageMenuOfferingList(Long vendorId) throws Exception;
	
	List<PackageMenuOfferingDTO> getMenuOfferingList(Long vendorId) throws Exception;
	
	List<PackageMenuItemsDTO> getPackageMenuItemsList(Long menuId) throws Exception;
	
	Long getMenuOfferingByUUID(String uuid) throws Exception;
	
	PackageMenuOfferingDTO getPackMenuOfferingByUUID(String uuid) throws Exception;
	
	boolean isMenuNameExists(String menuItemName,Long vendorId) throws Exception;
	
	void savePackageOfferingItem(PackageMenuOffering packageMenuOffering) throws Exception;
	
	PackageMenuOffering getPackageMenuOfferingByUUID(String uuid) throws Exception;
	
	void savePackageOfferingMenuItems(PackageMenuOfferingItems packageMenuOfferingItems) throws Exception;
	
	PackageMenuOfferingItems getPackageMenuOfferingItemByUUID(String uuid) throws Exception;
}
