package com.acculytixs.wayuparty.services.vendor;

import java.util.List;

import com.acculytixs.wayuparty.dto.services.PackageMenuItemsDTO;
import com.acculytixs.wayuparty.dto.services.PackageMenuOfferingDTO;
import com.acculytixs.wayuparty.entity.services.PackageMenuOffering;
import com.acculytixs.wayuparty.entity.services.PackageMenuOfferingItems;

public interface PackageService {

	
	List<PackageMenuOfferingDTO> getPackageMenuOfferingList(Long vendorId, String packageUUID) throws Exception;
	
	List<PackageMenuOfferingDTO> getMenuOfferingList(Long vendorId) throws Exception;
	
	List<PackageMenuItemsDTO> getPackageMenuItemsList(Long menuId) throws Exception;
	
	Long getMenuOfferingByUUID(String uuid) throws Exception;
	
	PackageMenuOfferingDTO getPackMenuOfferingByUUID(String uuid) throws Exception;
	
	boolean isMenuNameExists(String menuItemName, Long vendorId) throws Exception;
	
	String savePackageOfferingItem(PackageMenuOfferingDTO packageMenuOfferingDTO, Long vendorId, Long loginUserId);
	
	PackageMenuOffering getPackageMenuOfferingByUUID(String uuid) throws Exception;
	
	String savePackageOfferingMenuItems(PackageMenuOfferingDTO packageMenuOfferingDTO, Long vendorId, Long loginUserId);
	
	PackageMenuOfferingItems getPackageMenuOfferingItemByUUID(String uuid) throws Exception;
}
