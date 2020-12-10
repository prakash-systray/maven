package com.acculytixs.wayuparty.services.impl.vendor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang.StringUtils;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acculytixs.wayuparty.dao.vendor.PackageDao;
import com.acculytixs.wayuparty.dao.vendor.VendorServicesDao;
import com.acculytixs.wayuparty.dto.services.PackageMenuItemsDTO;
import com.acculytixs.wayuparty.dto.services.PackageMenuOfferingDTO;
import com.acculytixs.wayuparty.entity.services.PackageMenuOffering;
import com.acculytixs.wayuparty.entity.services.PackageMenuOfferingItems;
import com.acculytixs.wayuparty.entity.user.User;
import com.acculytixs.wayuparty.entity.vendor.Vendors;
import com.acculytixs.wayuparty.services.vendor.PackageService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.RandomCodeHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PackageServiceImpl implements PackageService{
	
	
	@Autowired
	PackageDao packageDao;
	
	@Autowired
	VendorServicesDao vendorServicesDao;

	@Override
	public List<PackageMenuOfferingDTO> getPackageMenuOfferingList(Long vendorId, String packageUUID) throws Exception {
		
		List<PackageMenuOfferingDTO> packageMenuOfferingDTOList = new CopyOnWriteArrayList<PackageMenuOfferingDTO>();
		packageMenuOfferingDTOList = packageDao.getPackageMenuOfferingList(vendorId);
		if(StringUtils.isNotBlank(packageUUID)) {
			String packageOfferingMenu = vendorServicesDao.getPackageMenuDetailsByUUID(packageUUID);
			JSONParser parser = new JSONParser();
			org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(packageOfferingMenu);
			 for (Iterator it = jsonArray.iterator(); it.hasNext();) {
				    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
				    String offeringItem = ((String) jsonObject.get("offeringItem")).trim();
				    String itemsOffered = ((String) jsonObject.get("itemsOffered")).trim();
				    
				    ObjectMapper objectMapper = new ObjectMapper();
				    String[] items = objectMapper.readValue(jsonObject.get("items").toString(), String[].class);
				    List<String> itemsList = new ArrayList(Arrays.asList(items));
				    
				    for(PackageMenuOfferingDTO menuDTO : packageMenuOfferingDTOList) {
				    	if(menuDTO.getMenuItemUUID().equalsIgnoreCase(offeringItem)) {
				    		menuDTO.setItemsOffered(itemsOffered);
				    	}
				    	
				    	for(PackageMenuItemsDTO itemsDTO : menuDTO.getMenuItemsList()) {
				    		if(itemsList.contains(itemsDTO.getItemUUID())) {
				    			itemsDTO.setIsItemSelected(Constants.STRING_Y);
				    		}
				    	}
				    }
			 }
		}
		return packageMenuOfferingDTOList;
	}

	@Override
	public List<PackageMenuOfferingDTO> getMenuOfferingList(Long vendorId) throws Exception {
		return packageDao.getMenuOfferingList(vendorId);
	}

	@Override
	public List<PackageMenuItemsDTO> getPackageMenuItemsList(Long menuId) throws Exception {
		return packageDao.getPackageMenuItemsList(menuId);
	}

	@Override
	public Long getMenuOfferingByUUID(String uuid) throws Exception {
		return packageDao.getMenuOfferingByUUID(uuid);
	}

	@Override
	public PackageMenuOfferingDTO getPackMenuOfferingByUUID(String uuid) throws Exception {
		return packageDao.getPackMenuOfferingByUUID(uuid);
	}

	@Override
	public boolean isMenuNameExists(String menuItemName,Long vendorId) throws Exception {
		return packageDao.isMenuNameExists(menuItemName, vendorId);
	}

	@Override
	public String savePackageOfferingItem(PackageMenuOfferingDTO packageMenuOfferingDTO, Long vendorId, Long loginUserId) {
		String queryExecutionStatus = null;
		try {
			
			PackageMenuOffering packageMenuOffering = null;
			
			if(StringUtils.isNotBlank(packageMenuOfferingDTO.getMenuItemUUID())) {
				 packageMenuOffering = packageDao.getPackageMenuOfferingByUUID(packageMenuOfferingDTO.getMenuItemUUID());
			}else {
				packageMenuOffering = new PackageMenuOffering();
				
				Vendors vendors = new Vendors();
				vendors.setId(vendorId);
				packageMenuOffering.setVendorId(vendors);
				
				packageMenuOffering.setStatus(1);
				packageMenuOffering.setCreatedDate(new Date());
				User loginUser = new User();
				loginUser.setId(loginUserId);
				packageMenuOffering.setCreatedBy(loginUser);
				packageMenuOffering.setUuid(RandomCodeHelper.generateRandomUUID());
			}
			packageMenuOffering.setOfferingItem(packageMenuOfferingDTO.getMenuItem().trim());
			packageDao.savePackageOfferingItem(packageMenuOffering);
			
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
	}catch (Exception e) {
		e.printStackTrace();
		queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
	}
		return queryExecutionStatus;	
	}

	@Override
	public PackageMenuOffering getPackageMenuOfferingByUUID(String uuid) throws Exception {
		return packageDao.getPackageMenuOfferingByUUID(uuid);
	}

	@Override
	public String savePackageOfferingMenuItems(PackageMenuOfferingDTO packageMenuOfferingDTO, Long vendorId,
			Long loginUserId) {
		
		String queryExecutionStatus = null;
		try {
		if(packageMenuOfferingDTO.getMenuItemsList() != null && !packageMenuOfferingDTO.getMenuItemsList().isEmpty()) {
			Vendors vendors = new Vendors();
			vendors.setId(vendorId);
			
			User loginUser = new User();
			loginUser.setId(loginUserId);
			
			PackageMenuOffering packageMenuOffering = packageDao.getPackageMenuOfferingByUUID(packageMenuOfferingDTO.getMenuItemUUID());
			
			for(PackageMenuItemsDTO packageMenuItemsDTO : packageMenuOfferingDTO.getMenuItemsList()) {
				if(StringUtils.isNotBlank(packageMenuItemsDTO.getItemName())) {
					PackageMenuOfferingItems items = null;
					if(StringUtils.isNotBlank(packageMenuItemsDTO.getItemUUID())) {
						items = packageDao.getPackageMenuOfferingItemByUUID(packageMenuItemsDTO.getItemUUID());
					}else {
						items = new PackageMenuOfferingItems();
						items.setVendorId(vendors);
						items.setMenuOfferingId(packageMenuOffering);
						items.setStatus(1);
						items.setCreatedDate(new Date());
						items.setCreatedBy(loginUser);
						items.setUuid(RandomCodeHelper.generateRandomUUID());
					}
					items.setMenuOfferingItem(packageMenuItemsDTO.getItemName());
					packageDao.savePackageOfferingMenuItems(items);
				}
			}
			
			if(StringUtils.isNotBlank(packageMenuOfferingDTO.getRemovedMenuItems())){
				String removedMenuItems[] = packageMenuOfferingDTO.getRemovedMenuItems().split(",");
				for(int i=0 ; i<removedMenuItems.length; i++) {
					PackageMenuOfferingItems items = packageDao.getPackageMenuOfferingItemByUUID(removedMenuItems[i]);
					items.setStatus(0);
					packageDao.savePackageOfferingMenuItems(items);
				}
				
			}
			
		}
		}catch (Exception e) {
			e.printStackTrace();
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
		}
		
		
		return queryExecutionStatus;
	}

	@Override
	public PackageMenuOfferingItems getPackageMenuOfferingItemByUUID(String uuid) throws Exception {
		return packageDao.getPackageMenuOfferingItemByUUID(uuid);
	}

}
