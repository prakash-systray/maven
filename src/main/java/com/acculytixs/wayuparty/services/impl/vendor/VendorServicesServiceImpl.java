package com.acculytixs.wayuparty.services.impl.vendor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.acculytixs.wayuparty.dao.vendor.PackageDao;
import com.acculytixs.wayuparty.dao.vendor.VendorDao;
import com.acculytixs.wayuparty.dao.vendor.VendorServicesDao;
import com.acculytixs.wayuparty.dto.services.MenuItemsDTO;
import com.acculytixs.wayuparty.dto.services.MenuOfferingItemsDTO;
import com.acculytixs.wayuparty.dto.services.PackageMenuOfferingDTO;
import com.acculytixs.wayuparty.dto.services.ServiceDateDTO;
import com.acculytixs.wayuparty.dto.services.ServicesDTO;
import com.acculytixs.wayuparty.dto.services.ServicesInfoDTO;
import com.acculytixs.wayuparty.dto.services.TimeSlotSchedulerInfo;
import com.acculytixs.wayuparty.dto.services.VendorBottleServiceDTO;
import com.acculytixs.wayuparty.dto.services.VendorEntryServiceDTO;
import com.acculytixs.wayuparty.dto.services.VendorPackageDTO;
import com.acculytixs.wayuparty.dto.services.VendorServicesDTO;
import com.acculytixs.wayuparty.dto.services.VendorTableServiceDTO;
import com.acculytixs.wayuparty.dto.vendor.BottleCategoryInfo;
import com.acculytixs.wayuparty.dto.vendor.BottleDTO;
import com.acculytixs.wayuparty.dto.vendor.ServiceSubCategoryDTO;
import com.acculytixs.wayuparty.dto.vendor.ServicesCategoryDTO;
import com.acculytixs.wayuparty.entity.services.PackageMenuOfferingItems;
import com.acculytixs.wayuparty.entity.services.VendorMasterService;
import com.acculytixs.wayuparty.entity.services.WayupartySerivces;
import com.acculytixs.wayuparty.entity.services.WayupartyServiceCategory;
import com.acculytixs.wayuparty.entity.services.WayupartyServiceSubCategory;
import com.acculytixs.wayuparty.entity.user.User;
import com.acculytixs.wayuparty.entity.vendor.Vendors;
import com.acculytixs.wayuparty.services.vendor.VendorServicesService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.FileInfo;
import com.acculytixs.wayuparty.util.FileUploadUtil;
import com.acculytixs.wayuparty.util.RandomCodeHelper;
import com.acculytixs.wayuparty.util.ResponseList;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class VendorServicesServiceImpl implements VendorServicesService{

	@Autowired
	VendorServicesDao vendorServicesDao;
	
	@Autowired
	VendorDao vendorDao;
	
	@Autowired
	PackageDao packageDao;


	@Value("${static.path}")
	private String staticPath;
	
	@Override
	public List<ServicesCategoryDTO> getServiceCategoriesByServiceId(Long serviceId) throws Exception {
		return vendorServicesDao.getServiceCategoriesByServiceId(serviceId);
	}

	@Override
	public String saveBottleCategories(BottleCategoryInfo bottleCategoryInfo) throws Exception {
		
		String queryExecutionStatus = null;
		try {
		if(bottleCategoryInfo.getBottleInfo() != null && !bottleCategoryInfo.getBottleInfo().isEmpty()) {
			for(BottleDTO bottleDTO : bottleCategoryInfo.getBottleInfo()) {
				if(bottleDTO != null && StringUtils.isNotBlank(bottleDTO.getBottleName())) {
					WayupartyServiceSubCategory wayupartyServiceSubCategory = null;
					if(StringUtils.isNotBlank(bottleDTO.getBottleUUID())) {
						wayupartyServiceSubCategory = vendorServicesDao.getWayupartyServiceSubCategoryByUUID(bottleDTO.getBottleUUID());
					}else {
						wayupartyServiceSubCategory = new WayupartyServiceSubCategory();
						wayupartyServiceSubCategory.setUuid(RandomCodeHelper.generateRandomUUID());
						
						WayupartyServiceCategory category = new WayupartyServiceCategory();
						category.setId(Long.valueOf(bottleCategoryInfo.getCategoryId()));
						wayupartyServiceSubCategory.setCategoryId(category);
						
						Vendors vendors = vendorDao.getVendorByUUID(bottleCategoryInfo.getVendorUUID());
						wayupartyServiceSubCategory.setVendorId(vendors);
					}
					
					wayupartyServiceSubCategory.setSubCategoryName(bottleDTO.getBottleName());
					wayupartyServiceSubCategory.setStatus(1);
					wayupartyServiceSubCategory.setCreatedDate(new Date());
					vendorServicesDao.saveServiceSubCategory(wayupartyServiceSubCategory);
					
				}
			}
			
			
			if(StringUtils.isNotBlank(bottleCategoryInfo.getRemovedSubCategoriesUUID())){
				String removedSubCategories[] = bottleCategoryInfo.getRemovedSubCategoriesUUID().split(",");
				for(int i=0 ; i<removedSubCategories.length; i++) {
					WayupartyServiceSubCategory wayupartyServiceSubCategory = vendorServicesDao.getWayupartyServiceSubCategoryByUUID(removedSubCategories[i]);
					wayupartyServiceSubCategory.setStatus(0);
					vendorServicesDao.saveServiceSubCategory(wayupartyServiceSubCategory);
				}
				
			}
			
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
		}
		}catch (Exception e) {
			e.printStackTrace();
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
		}
		return queryExecutionStatus;
	}

	@Override
	public List<ServiceSubCategoryDTO> getVendorServiceSubCategories(Long categoryId, Long vendorId) throws Exception {
		return vendorServicesDao.getVendorServiceSubCategories(categoryId, vendorId);
	}

	@Override
	public String saveBottleService(VendorBottleServiceDTO vendorBottleServiceDTO, Long loginUserId) throws Exception {
		
		String queryExecutionStatus = null;
		try {
			
			VendorMasterService vendorMasterService = new VendorMasterService();
			
			if(StringUtils.isNotBlank(vendorBottleServiceDTO.getBottleUUID())) {
				vendorMasterService = vendorServicesDao.geVendorMasterServiceByUUID(vendorBottleServiceDTO.getBottleUUID());
			}else {
				vendorMasterService.setCreatedDate(new Date());
				vendorMasterService.setUuid(RandomCodeHelper.generateRandomUUID());
			}
			
			Long vendorId = vendorDao.getVendorIdByUUID(vendorBottleServiceDTO.getVendorUUID());
			Vendors vendors = new Vendors();
			vendors.setId(vendorId);
			vendorMasterService.setVendorId(vendors);
			
			WayupartySerivces services = new WayupartySerivces();
			Long serviceId = vendorDao.getServiceIdByUUID(vendorBottleServiceDTO.getServiceUUID());
			services.setId(serviceId);
			vendorMasterService.setServiceId(services);
			
			WayupartyServiceCategory serviceCategory = new WayupartyServiceCategory();
			serviceCategory.setId(Long.valueOf(vendorBottleServiceDTO.getBottleType()));
			vendorMasterService.setCategoryId(serviceCategory);
			
			WayupartyServiceSubCategory subCategory = new WayupartyServiceSubCategory();
			subCategory.setId(Long.valueOf(vendorBottleServiceDTO.getBottleName()));
			vendorMasterService.setSubCategoryId(subCategory);
			
			vendorMasterService.setActualPrice(vendorBottleServiceDTO.getActualPrice());
			vendorMasterService.setOfferPrice(vendorBottleServiceDTO.getOfferPrice());
			vendorMasterService.setAllowed(vendorBottleServiceDTO.getAllowed());
			
			Date serviceStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(vendorBottleServiceDTO.getStartDate());  
			vendorMasterService.setServiceStartDate(serviceStartDate);
			
			Date serviceEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(vendorBottleServiceDTO.getEndDate());  
			vendorMasterService.setServiceEndDate(serviceEndDate);
			
			vendorMasterService.setServiceDescription(vendorBottleServiceDTO.getDescription());
			vendorMasterService.setServiceOffer(vendorBottleServiceDTO.getServiceOffer());
			vendorMasterService.setTermsAndConditions(vendorBottleServiceDTO.getTermsAndConditions());
			
			if (vendorBottleServiceDTO.getFileInfo() != null && !vendorBottleServiceDTO.getFileInfo().isEmpty()) {
				
				FileInfo fileInfo = vendorBottleServiceDTO.getFileInfo().get(0);
				vendorMasterService.setServiceImage(fileInfo.getFileURL().replaceAll(" ", "_"));
				try {
					FileUploadUtil.moveFile(staticPath, fileInfo, "vendor_services_images");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}else {
				String serviceImage = vendorServicesDao.getServiceImageByUUID(vendorBottleServiceDTO.getBottleUUID());
				vendorMasterService.setServiceImage(serviceImage);
			}
			
			
			if (vendorBottleServiceDTO.getTimeSchedulerInfo() != null && !vendorBottleServiceDTO.getTimeSchedulerInfo().isEmpty()) {
				
				JSONArray array = new JSONArray();
				for(TimeSlotSchedulerInfo timeSchedulerInfo : vendorBottleServiceDTO.getTimeSchedulerInfo()) {
					JSONObject documentItem = new JSONObject();
					documentItem.put("startTime", timeSchedulerInfo.getStartTime());
					documentItem.put("endTime", timeSchedulerInfo.getEndTime());
					array.put(documentItem);
				}
				String timeSlotsSchedulerJsonArry = array.toString();
				vendorMasterService.setServiceTimeSlots(timeSlotsSchedulerJsonArry);
			}
			
			
			vendorMasterService.setStatus(1);
			User loginUser = new User();
			loginUser.setId(loginUserId);
			vendorMasterService.setCreatedBy(loginUser);
			vendorServicesDao.saveVendorMasterService(vendorMasterService);
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
			
		}catch (Exception e) {
			e.printStackTrace();
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
		}
		return queryExecutionStatus;
	}

	@Override
	public ResponseList<VendorServicesDTO> getVendorServicesList(Long serviceId,
			Long vendorId) throws Exception {
		return vendorServicesDao.getVendorServicesList(serviceId, vendorId);
	}

	@Override
	public VendorServicesDTO getVendorServiceDetails(String serviceUUID) throws Exception {
		VendorServicesDTO vendorServicesDTO =  vendorServicesDao.getVendorServiceDetails(serviceUUID);
		
		List<TimeSlotSchedulerInfo> timeslotsList = Collections.<TimeSlotSchedulerInfo>emptyList();
		
		if(StringUtils.isNotBlank(vendorServicesDTO.getServiceTimeSlots())) {
			timeslotsList = new CopyOnWriteArrayList<TimeSlotSchedulerInfo>();
			JSONParser parser = new JSONParser();
				org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(vendorServicesDTO.getServiceTimeSlots());
				  for (Iterator it = jsonArray.iterator(); it.hasNext();) {
					    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
					   
					    String startTime = ((String) jsonObject.get("startTime")).trim();
					    String endTime = ((String) jsonObject.get("endTime")).trim();
					    TimeSlotSchedulerInfo timeSlotSchedulerInfo = new TimeSlotSchedulerInfo();
					    timeSlotSchedulerInfo.setStartTime(startTime);
					    timeSlotSchedulerInfo.setEndTime(endTime);
					    timeslotsList.add(timeSlotSchedulerInfo);
				 }
		}
		
		if(StringUtils.isNotBlank(vendorServicesDTO.getPackageMenu())) {
			
			List<MenuOfferingItemsDTO> packageMenuOfferingDTOList = new ArrayList<MenuOfferingItemsDTO>();
			JSONParser parser = new JSONParser();
			org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(vendorServicesDTO.getPackageMenu());
			 for (Iterator it = jsonArray.iterator(); it.hasNext();) {
				 MenuOfferingItemsDTO menuOfferingItemsDTO = new MenuOfferingItemsDTO();
				    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
				    String offeringItem = ((String) jsonObject.get("offeringItem")).trim();
				    String itemsOffered = ((String) jsonObject.get("itemsOffered")).trim();
				    
				    PackageMenuOfferingDTO offeringItems = packageDao.getPackMenuOfferingByUUID(offeringItem);
				    menuOfferingItemsDTO.setItemsOffered(itemsOffered);
				    menuOfferingItemsDTO.setMenuItem(offeringItems.getMenuItem());
				    menuOfferingItemsDTO.setMenuItemUUID(offeringItems.getMenuItemUUID());
				    
				    ObjectMapper objectMapper = new ObjectMapper();
				    String[] items = objectMapper.readValue(jsonObject.get("items").toString(), String[].class);
				    List<String> itemsList = new ArrayList(Arrays.asList(items));
				    
				    List<MenuItemsDTO> menuItemsDTOList = new ArrayList<MenuItemsDTO>();
				    
				    for(String itemUUID : itemsList) {
				    	PackageMenuOfferingItems menuItems = packageDao.getPackageMenuOfferingItemByUUID(itemUUID);
				    	MenuItemsDTO menuItemsDTO = new MenuItemsDTO();
				    	menuItemsDTO.setItemName(menuItems.getMenuOfferingItem());
				    	menuItemsDTO.setItemUUID(menuItems.getUuid());
				    	menuItemsDTOList.add(menuItemsDTO);
				    }
				    
				    menuOfferingItemsDTO.setMenuItemsList(menuItemsDTOList);
				    packageMenuOfferingDTOList.add(menuOfferingItemsDTO);
			 }
			 vendorServicesDTO.setPackageMenuItems(packageMenuOfferingDTOList);
		}else {
			List<MenuOfferingItemsDTO> emptyList = Collections.<MenuOfferingItemsDTO>emptyList();  
			vendorServicesDTO.setPackageMenuItems(emptyList); 
		}
		
		vendorServicesDTO.setTimeSlotList(timeslotsList);
		return vendorServicesDTO;
	}

	@Override
	public boolean isServiceExistsBySubCategoryIdAndVendorId(String serviceStartDate, String serviceEndDate, Long subCategoryId, Long vendorId)
			throws Exception {
		return vendorServicesDao.isServiceExistsBySubCategoryIdAndVendorId(serviceStartDate, serviceEndDate, subCategoryId, vendorId);
	}
	
	@Override
	public boolean isSavedServiceExistsBySubCategoryIdAndVendorId(String serviceStartDate, String serviceEndDate, Long subCategoryId, Long vendorId)
			throws Exception {
		return vendorServicesDao.isSavedServiceExistsBySubCategoryIdAndVendorId(serviceStartDate, serviceEndDate, subCategoryId, vendorId);
	}

	@Override
	public ServicesInfoDTO getServicesList(String vendorUUID) throws Exception {
		ServicesInfoDTO servicesInfoDTO = vendorServicesDao.getServicesList(vendorUUID);
		List<ServicesDTO> servicesList = vendorDao.getVendorServices();
		servicesInfoDTO.setServicesList(servicesList);
		return servicesInfoDTO;
	}

	@Override
	public List<VendorServicesDTO> getVendorCategoryServicesList(Long categoryId, Long vendorId)
			throws Exception {
		List<VendorServicesDTO> vendorServicesDTOList = vendorServicesDao.getVendorCategoryServicesList(categoryId, vendorId);
		
		if(vendorServicesDTOList != null && !vendorServicesDTOList.isEmpty()) {
			for(VendorServicesDTO vendorServicesDTO : vendorServicesDTOList) {
				List<TimeSlotSchedulerInfo> timeslotsList = Collections.<TimeSlotSchedulerInfo>emptyList();
				if(StringUtils.isNotBlank(vendorServicesDTO.getServiceTimeSlots())) {
					timeslotsList = new CopyOnWriteArrayList<TimeSlotSchedulerInfo>();
					JSONParser parser = new JSONParser();
						org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(vendorServicesDTO.getServiceTimeSlots());
						  for (Iterator it = jsonArray.iterator(); it.hasNext();) {
							    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
							   
							    String startTime = ((String) jsonObject.get("startTime")).trim();
							    String endTime = ((String) jsonObject.get("endTime")).trim();
							    TimeSlotSchedulerInfo timeSlotSchedulerInfo = new TimeSlotSchedulerInfo();
							    timeSlotSchedulerInfo.setStartTime(startTime);
							    timeSlotSchedulerInfo.setEndTime(endTime);
							    timeslotsList.add(timeSlotSchedulerInfo);
						 }
						  vendorServicesDTO.setTimeSlotList(timeslotsList);
				}
				
				
				if(StringUtils.isNotBlank(vendorServicesDTO.getPackageMenu())) {
					List<MenuOfferingItemsDTO> packageMenuOfferingDTOList = new ArrayList<MenuOfferingItemsDTO>();
					JSONParser parser = new JSONParser();
					org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(vendorServicesDTO.getPackageMenu());
					 for (Iterator it = jsonArray.iterator(); it.hasNext();) {
						 MenuOfferingItemsDTO menuOfferingItemsDTO = new MenuOfferingItemsDTO();
						    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
						    String offeringItem = ((String) jsonObject.get("offeringItem")).trim();
						    String itemsOffered = ((String) jsonObject.get("itemsOffered")).trim();
						    
						    PackageMenuOfferingDTO offeringItems = packageDao.getPackMenuOfferingByUUID(offeringItem);
						    menuOfferingItemsDTO.setItemsOffered(itemsOffered);
						    menuOfferingItemsDTO.setMenuItem(offeringItems.getMenuItem());
						    menuOfferingItemsDTO.setMenuItemUUID(offeringItems.getMenuItemUUID());
						    
						    ObjectMapper objectMapper = new ObjectMapper();
						    String[] items = objectMapper.readValue(jsonObject.get("items").toString(), String[].class);
						    List<String> itemsList = new ArrayList(Arrays.asList(items));
						    
						    List<MenuItemsDTO> menuItemsDTOList = new ArrayList<MenuItemsDTO>();
						    
						    for(String itemUUID : itemsList) {
						    	PackageMenuOfferingItems menuItems = packageDao.getPackageMenuOfferingItemByUUID(itemUUID);
						    	MenuItemsDTO menuItemsDTO = new MenuItemsDTO();
						    	menuItemsDTO.setItemName(menuItems.getMenuOfferingItem());
						    	menuItemsDTO.setItemUUID(menuItems.getUuid());
						    	menuItemsDTOList.add(menuItemsDTO);
						    }
						    
						    menuOfferingItemsDTO.setMenuItemsList(menuItemsDTOList);
						    packageMenuOfferingDTOList.add(menuOfferingItemsDTO);
					 }
					 vendorServicesDTO.setPackageMenuItems(packageMenuOfferingDTOList);
				}else {
					List<MenuOfferingItemsDTO> emptyList = Collections.<MenuOfferingItemsDTO>emptyList();  
					vendorServicesDTO.setPackageMenuItems(emptyList); 
				}
				
				
				String startDate = vendorServicesDTO.getServiceStartDate();
				String endDate = vendorServicesDTO.getServiceEndDate();
				SimpleDateFormat  dateformat = new SimpleDateFormat("yyyy-MM-dd"); 
				Date start = dateformat.parse(startDate);
				Date end = dateformat.parse(endDate);
				SimpleDateFormat  formatter = new SimpleDateFormat("dd MMM yyyy"); 
				List<ServiceDateDTO> serviceDateDTOList = new ArrayList<ServiceDateDTO>();
			    
				Date in = new Date();
				LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
				Date currentDate = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
				ServiceDateDTO serviceDateDTO = null;
				String[] passableDate = null;
				
				while (!start.after(end)) {
					serviceDateDTO = new ServiceDateDTO();
				    Calendar c = Calendar.getInstance(); 
				    c.setTime(start); 
				    start = c.getTime();
				    
				    String currentDateStr = dateformat.format(currentDate);
				    currentDate = dateformat.parse(currentDateStr);
				    
				    if(start.compareTo(currentDate) == 0 || start.after(currentDate)) {
				    	passableDate = dateformat.format(start).split("-");
					    serviceDateDTO.setPassableDate(passableDate[2]+"/"+passableDate[1]+"/"+passableDate[0]);
					    serviceDateDTO.setServiceDate(formatter.format(start));
					    serviceDateDTOList.add(serviceDateDTO);
				    }
				    
				    c.add(Calendar.DATE, 1);
				    start = c.getTime();
				}
				vendorServicesDTO.setServiceDates(serviceDateDTOList);
			}
		}
		return vendorServicesDTOList;
	}

	@Override
	public boolean isServiceExistsByCategoryIdAndVendorId(String serviceStartDate, String serviceEndDate,
			Long categoryId, Long vendorId) throws Exception {
		return vendorServicesDao.isServiceExistsByCategoryIdAndVendorId(serviceStartDate, serviceEndDate, categoryId, vendorId);
	}

	@Override
	public String saveTableService(VendorTableServiceDTO vendorTableServiceDTO, Long loginUserId) throws Exception {
		
		String queryExecutionStatus = null;
		try {
			
			VendorMasterService vendorMasterService = new VendorMasterService();
			
			if(StringUtils.isNotBlank(vendorTableServiceDTO.getTableUUID())) {
				vendorMasterService = vendorServicesDao.geVendorMasterServiceByUUID(vendorTableServiceDTO.getTableUUID());
			}else {
				vendorMasterService.setCreatedDate(new Date());
				vendorMasterService.setUuid(RandomCodeHelper.generateRandomUUID());
			}
			
			Long vendorId = vendorDao.getVendorIdByUUID(vendorTableServiceDTO.getVendorUUID());
			Vendors vendors = new Vendors();
			vendors.setId(vendorId);
			vendorMasterService.setVendorId(vendors);
			
			WayupartySerivces services = new WayupartySerivces();
			Long serviceId = vendorDao.getServiceIdByUUID(vendorTableServiceDTO.getServiceUUID());
			services.setId(serviceId);
			vendorMasterService.setServiceId(services);
			
			WayupartyServiceCategory serviceCategory = new WayupartyServiceCategory();
			serviceCategory.setId(Long.valueOf(vendorTableServiceDTO.getTableType()));
			vendorMasterService.setCategoryId(serviceCategory);
			
			vendorMasterService.setServiceName(vendorTableServiceDTO.getTableName());
			
			vendorMasterService.setActualPrice(vendorTableServiceDTO.getActualPrice());
			vendorMasterService.setOfferPrice(vendorTableServiceDTO.getOfferPrice());
			String categoryName = vendorDao.getCategoryNameByCategoryId(Long.valueOf(vendorTableServiceDTO.getTableType()));
			vendorMasterService.setAllowed(Integer.valueOf(categoryName));
			
			Date serviceStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(vendorTableServiceDTO.getStartDate());  
			vendorMasterService.setServiceStartDate(serviceStartDate);
			
			Date serviceEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(vendorTableServiceDTO.getEndDate());  
			vendorMasterService.setServiceEndDate(serviceEndDate);
			
			vendorMasterService.setServiceDescription(vendorTableServiceDTO.getDescription());
			vendorMasterService.setServiceOffer(vendorTableServiceDTO.getServiceOffer());
			vendorMasterService.setTermsAndConditions(vendorTableServiceDTO.getTermsAndConditions());
			
			if (vendorTableServiceDTO.getFileInfo() != null && !vendorTableServiceDTO.getFileInfo().isEmpty()) {
				
				FileInfo fileInfo = vendorTableServiceDTO.getFileInfo().get(0);
				vendorMasterService.setServiceImage(fileInfo.getFileURL().replaceAll(" ", "_"));
				try {
					FileUploadUtil.moveFile(staticPath, fileInfo, "vendor_services_images");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}else {
				String serviceImage = vendorServicesDao.getServiceImageByUUID(vendorTableServiceDTO.getTableUUID());
				vendorMasterService.setServiceImage(serviceImage);
			}
			
			
			if (vendorTableServiceDTO.getTimeSchedulerInfo() != null && !vendorTableServiceDTO.getTimeSchedulerInfo().isEmpty()) {
				
				JSONArray array = new JSONArray();
				for(TimeSlotSchedulerInfo timeSchedulerInfo : vendorTableServiceDTO.getTimeSchedulerInfo()) {
					JSONObject documentItem = new JSONObject();
					documentItem.put("startTime", timeSchedulerInfo.getStartTime());
					documentItem.put("endTime", timeSchedulerInfo.getEndTime());
					array.put(documentItem);
				}
				String timeSlotsSchedulerJsonArry = array.toString();
				vendorMasterService.setServiceTimeSlots(timeSlotsSchedulerJsonArry);
			}
			
			
			vendorMasterService.setStatus(1);
			User loginUser = new User();
			loginUser.setId(loginUserId);
			vendorMasterService.setCreatedBy(loginUser);
			vendorMasterService.setCreatedDate(new Date());
			vendorServicesDao.saveVendorMasterService(vendorMasterService);
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
			
		}catch (Exception e) {
			e.printStackTrace();
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
		}
		return queryExecutionStatus;
	}

	@Override
	public VendorServicesDTO getVendorServiceDetailsByMasterServiceUUID(String serviceUUID) throws Exception {
		VendorServicesDTO vendorServicesDTO = vendorServicesDao.getVendorServiceDetailsByMasterServiceUUID(serviceUUID);
		List<TimeSlotSchedulerInfo> timeslotsList = Collections.<TimeSlotSchedulerInfo>emptyList();
		
		if(StringUtils.isNotBlank(vendorServicesDTO.getServiceTimeSlots())) {
			timeslotsList = new CopyOnWriteArrayList<TimeSlotSchedulerInfo>();
			JSONParser parser = new JSONParser();
				org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(vendorServicesDTO.getServiceTimeSlots());
				  for (Iterator it = jsonArray.iterator(); it.hasNext();) {
					    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
					   
					    String startTime = ((String) jsonObject.get("startTime")).trim();
					    String endTime = ((String) jsonObject.get("endTime")).trim();
					    TimeSlotSchedulerInfo timeSlotSchedulerInfo = new TimeSlotSchedulerInfo();
					    timeSlotSchedulerInfo.setStartTime(startTime);
					    timeSlotSchedulerInfo.setEndTime(endTime);
					    timeslotsList.add(timeSlotSchedulerInfo);
				 }
		}
		
		vendorServicesDTO.setTimeSlotList(timeslotsList);
		return vendorServicesDTO;
	}

	@Override
	public Long getVendorMasterServiceIdByUUID(String serviceUUID) throws Exception {
		return vendorServicesDao.getVendorMasterServiceIdByUUID(serviceUUID);
	}

	@Override
	public boolean isSavedServiceExistsByCategoryIdAndVendorId(String serviceStartDate, String serviceEndDate,
			Long categoryId, Long vendorId) throws Exception {
		return vendorServicesDao.isSavedServiceExistsByCategoryIdAndVendorId(serviceStartDate, serviceEndDate, categoryId, vendorId);
	}

	@Override
	public String saveEntryService(VendorEntryServiceDTO vendorEntryServiceDTO, Long loginUserId) throws Exception {
		

		
		String queryExecutionStatus = null;
		try {
			
			VendorMasterService vendorMasterService = new VendorMasterService();
			
			if(StringUtils.isNotBlank(vendorEntryServiceDTO.getEntryUUID())) {
				vendorMasterService = vendorServicesDao.geVendorMasterServiceByUUID(vendorEntryServiceDTO.getEntryUUID());
			}else if(StringUtils.isNotBlank(vendorEntryServiceDTO.getGuestUUID())) {
				vendorMasterService = vendorServicesDao.geVendorMasterServiceByUUID(vendorEntryServiceDTO.getGuestUUID());
			}else {
				vendorMasterService.setCreatedDate(new Date());
				vendorMasterService.setUuid(RandomCodeHelper.generateRandomUUID());
			}
			
			Long vendorId = vendorDao.getVendorIdByUUID(vendorEntryServiceDTO.getVendorUUID());
			Vendors vendors = new Vendors();
			vendors.setId(vendorId);
			vendorMasterService.setVendorId(vendors);
			
			WayupartySerivces services = new WayupartySerivces();
			Long serviceId = vendorDao.getServiceIdByUUID(vendorEntryServiceDTO.getServiceUUID());
			services.setId(serviceId);
			vendorMasterService.setServiceId(services);
			
			WayupartyServiceCategory serviceCategory = new WayupartyServiceCategory();
			serviceCategory.setId(Long.valueOf(vendorEntryServiceDTO.getEntryType()));
			vendorMasterService.setCategoryId(serviceCategory);
			
			vendorMasterService.setServiceName(vendorEntryServiceDTO.getEventName());
			
			vendorMasterService.setActualPrice(vendorEntryServiceDTO.getActualPrice());
			vendorMasterService.setOfferPrice(vendorEntryServiceDTO.getOfferPrice());
			String categoryName = vendorDao.getCategoryNameByCategoryId(Long.valueOf(vendorEntryServiceDTO.getEntryType()));
			if(categoryName.equalsIgnoreCase("Stag") || categoryName.equalsIgnoreCase("Single Lady")) {
				vendorMasterService.setAllowed(1);
			}else if(categoryName.equalsIgnoreCase("Couple")) {
				vendorMasterService.setAllowed(2);
			}
			
			Date serviceStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(vendorEntryServiceDTO.getStartDate());  
			vendorMasterService.setServiceStartDate(serviceStartDate);
			
			Date serviceEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(vendorEntryServiceDTO.getEndDate());  
			vendorMasterService.setServiceEndDate(serviceEndDate);
			
			vendorMasterService.setServiceDescription(vendorEntryServiceDTO.getDescription());
			vendorMasterService.setMusicGenre(vendorEntryServiceDTO.getMusicGenre());
			vendorMasterService.setEventLocation(vendorEntryServiceDTO.getEventLocation());
			vendorMasterService.setTermsAndConditions(vendorEntryServiceDTO.getTermsAndConditions());
			vendorMasterService.setArtist(vendorEntryServiceDTO.getArtist());
			
			if (vendorEntryServiceDTO.getFileInfo() != null && !vendorEntryServiceDTO.getFileInfo().isEmpty()) {
				
				FileInfo fileInfo = vendorEntryServiceDTO.getFileInfo().get(0);
				vendorMasterService.setServiceImage(fileInfo.getFileURL().replaceAll(" ", "_"));
				try {
					FileUploadUtil.moveFile(staticPath, fileInfo, "vendor_services_images");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}else {
				String serviceImage = vendorServicesDao.getServiceImageByUUID(vendorEntryServiceDTO.getEntryUUID());
				vendorMasterService.setServiceImage(serviceImage);
			}
			
			
			if (StringUtils.isNotBlank(vendorEntryServiceDTO.getStartTime()) && StringUtils.isNotBlank(vendorEntryServiceDTO.getEndTime())) {
				
				JSONArray array = new JSONArray();
					JSONObject documentItem = new JSONObject();
					documentItem.put("startTime", vendorEntryServiceDTO.getStartTime());
					documentItem.put("endTime", vendorEntryServiceDTO.getEndTime());
					array.put(documentItem);
				String timeSlotsSchedulerJsonArry = array.toString();
				vendorMasterService.setServiceTimeSlots(timeSlotsSchedulerJsonArry);
			}
			
			if(StringUtils.isNotBlank(vendorEntryServiceDTO.getGuestEntryTime())) {
				vendorMasterService.setGuestEntryTime(vendorEntryServiceDTO.getGuestEntryTime());
			}
			
			vendorMasterService.setStatus(1);
			User loginUser = new User();
			loginUser.setId(loginUserId);
			vendorMasterService.setCreatedBy(loginUser);
			vendorMasterService.setCreatedDate(new Date());
			vendorServicesDao.saveVendorMasterService(vendorMasterService);
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
			
		}catch (Exception e) {
			e.printStackTrace();
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
		}
		return queryExecutionStatus;
	
		
		
	}

	@Override
	public String savePackageService(VendorPackageDTO vendorPackageDTO, String packageOfferingMenu, Long loginUserId)
			throws Exception {
		
		String queryExecutionStatus = null;
		try {
			
			VendorMasterService vendorMasterService = new VendorMasterService();
			
			if(StringUtils.isNotBlank(vendorPackageDTO.getPackageUUID())) {
				vendorMasterService = vendorServicesDao.geVendorMasterServiceByUUID(vendorPackageDTO.getPackageUUID());
			}else {
				vendorMasterService.setCreatedDate(new Date());
				vendorMasterService.setUuid(RandomCodeHelper.generateRandomUUID());
			}
			
			Long vendorId = vendorDao.getVendorIdByUUID(vendorPackageDTO.getVendorUUID());
			Vendors vendors = new Vendors();
			vendors.setId(vendorId);
			vendorMasterService.setVendorId(vendors);
			
			WayupartySerivces services = new WayupartySerivces();
			Long serviceId = vendorDao.getServiceIdByUUID(vendorPackageDTO.getServiceUUID());
			services.setId(serviceId);
			vendorMasterService.setServiceId(services);
			
			WayupartyServiceCategory serviceCategory = new WayupartyServiceCategory();
			serviceCategory.setId(Long.valueOf(vendorPackageDTO.getPackageType()));
			vendorMasterService.setCategoryId(serviceCategory);
			
			vendorMasterService.setServiceName(vendorPackageDTO.getPackageName());
			
			vendorMasterService.setActualPrice(vendorPackageDTO.getActualPrice());
			vendorMasterService.setOfferPrice(vendorPackageDTO.getOfferPrice());
			vendorMasterService.setAllowed(vendorPackageDTO.getAllowed());
			
			Date serviceStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(vendorPackageDTO.getStartDate());  
			vendorMasterService.setServiceStartDate(serviceStartDate);
			
			Date serviceEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(vendorPackageDTO.getEndDate());  
			vendorMasterService.setServiceEndDate(serviceEndDate);
			
			vendorMasterService.setServiceDescription(vendorPackageDTO.getDescription());
			vendorMasterService.setTermsAndConditions(vendorPackageDTO.getTermsAndConditions());
			
			if (vendorPackageDTO.getFileInfo() != null && !vendorPackageDTO.getFileInfo().isEmpty()) {
				
				FileInfo fileInfo = vendorPackageDTO.getFileInfo().get(0);
				vendorMasterService.setServiceImage(fileInfo.getFileURL().replaceAll(" ", "_"));
				try {
					FileUploadUtil.moveFile(staticPath, fileInfo, "vendor_services_images");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}else {
				String serviceImage = vendorServicesDao.getServiceImageByUUID(vendorPackageDTO.getPackageUUID());
				vendorMasterService.setServiceImage(serviceImage);
			}
			
			
			if (vendorPackageDTO.getTimeSchedulerInfo() != null && !vendorPackageDTO.getTimeSchedulerInfo().isEmpty()) {
				
				JSONArray array = new JSONArray();
				for(TimeSlotSchedulerInfo timeSchedulerInfo : vendorPackageDTO.getTimeSchedulerInfo()) {
					JSONObject documentItem = new JSONObject();
					documentItem.put("startTime", timeSchedulerInfo.getStartTime());
					documentItem.put("endTime", timeSchedulerInfo.getEndTime());
					array.put(documentItem);
				}
				String timeSlotsSchedulerJsonArry = array.toString();
				vendorMasterService.setServiceTimeSlots(timeSlotsSchedulerJsonArry);
			}
			
			vendorMasterService.setPackageMenu(packageOfferingMenu);
			vendorMasterService.setStatus(1);
			User loginUser = new User();
			loginUser.setId(loginUserId);
			vendorMasterService.setCreatedBy(loginUser);
			vendorServicesDao.saveVendorMasterService(vendorMasterService);
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
			
		}catch (Exception e) {
			e.printStackTrace();
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
		}
		return queryExecutionStatus;
	}
}
