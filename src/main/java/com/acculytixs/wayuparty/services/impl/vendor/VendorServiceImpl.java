package com.acculytixs.wayuparty.services.impl.vendor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.acculytixs.wayuparty.dao.vendor.VendorDao;
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
import com.acculytixs.wayuparty.entity.WayupartyRoles;
import com.acculytixs.wayuparty.entity.user.User;
import com.acculytixs.wayuparty.entity.vendor.VendorBankAccount;
import com.acculytixs.wayuparty.entity.vendor.Vendors;
import com.acculytixs.wayuparty.security.WayupartyPasswordEncoder;
import com.acculytixs.wayuparty.services.vendor.VendorService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.FileInfo;
import com.acculytixs.wayuparty.util.FileUploadUtil;
import com.acculytixs.wayuparty.util.RandomCodeHelper;

@Service
public class VendorServiceImpl implements VendorService{
	
	@Autowired
	VendorDao vendorDao;
	
	@Value("${static.path}")
	private String staticPath;
	
	@Autowired
	WayupartyPasswordEncoder wayupartyPasswordEncoder;

	@Override
	public void saveVendor(AddVendorDTO addVendorDTO) throws Exception {
		
		try {
		Vendors vendors = new Vendors();
		vendors.setVendorName(addVendorDTO.getVendorName());
		vendors.setVendorCode(addVendorDTO.getVendorCode());
		vendors.setVendorMobile(addVendorDTO.getVendorMobile());
		vendors.setVendorEmail(addVendorDTO.getVendorEmail());
		vendors.setVendorCapacity(Integer.valueOf(addVendorDTO.getVendorCapacity()));
		vendors.setEstabilshedYear(Integer.valueOf(addVendorDTO.getEstablishedYear()));
		vendors.setCostForTwoPeople(Integer.valueOf(addVendorDTO.getCostForTwoPeople()));
		vendors.setCurrency(addVendorDTO.getCurrency());
		vendors.setVendorDescription(addVendorDTO.getVendorDescription());
		vendors.setBestSellingItems(addVendorDTO.getBestSellingItems());
		
		if (addVendorDTO.getFileInfo() != null && !addVendorDTO.getFileInfo().isEmpty()) {
			
			FileInfo fileInfo = addVendorDTO.getFileInfo().get(0);
			vendors.setProfileImage(fileInfo.getFileURL().replaceAll(" ", "_"));
			try {
				FileUploadUtil.moveFile(staticPath, fileInfo, "vendor_profile_images");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		vendors.setUuid(RandomCodeHelper.generateRandomUUID());
		vendors.setStatus(1);
		vendors.setCreatedDate(new Date());
		
		
		HashSet<User> adminUserList = new HashSet<User>();
		User user = new User();
		user.setVendorId(vendors);
		user.setFirstName(addVendorDTO.getVendorAdministratorName());
		user.setMobileNumber(addVendorDTO.getVendorMobile());
		user.setEmail(addVendorDTO.getVendorEmail());
		user.setUserName(addVendorDTO.getVendorEmail());
		user.setPassword(wayupartyPasswordEncoder.encode(Constants.WAYUPARTY_DEFAULT_PWD));
		WayupartyRoles roles = new WayupartyRoles();
		roles.setId(Constants.ROLE_ADMIN);
		user.setRoleId(roles);
		user.setUuid(RandomCodeHelper.generateRandomUUID());
		user.setStatus(1);
		user.setCreatedDate(new Date());
		adminUserList.add(user);
		vendors.setUser(adminUserList);
		
		HashSet<VendorBankAccount> bankAccountList = new HashSet<VendorBankAccount>();
		VendorBankAccount bankAccount = new VendorBankAccount();
		bankAccount.setVendorId(vendors);
		bankAccount.setBeneficiaryName(addVendorDTO.getBeneficiaryName());
		bankAccount.setBankName(addVendorDTO.getBankName());
		bankAccount.setBankBranch(addVendorDTO.getBankBranch());
		bankAccount.setAccountNumber(addVendorDTO.getAccountNumber());
		bankAccount.setBankCode(addVendorDTO.getBankCode());
		bankAccount.setAccountType(addVendorDTO.getAccountType());
		bankAccount.setUuid(RandomCodeHelper.generateRandomUUID());
		bankAccount.setStatus(1);
		bankAccount.setCreatedDate(new Date());
		bankAccountList.add(bankAccount);
		vendors.setVendorBankAccount(bankAccountList);
		
		vendorDao.saveVendor(vendors);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<VendorDTO> getRegisteredVendors() throws Exception {
		return vendorDao.getRegisteredVendors();
	}
	
	@Override
	public List<VendorDTO> getRegisteredRestaurantsList(Integer offset, Integer limit) throws Exception {
		return vendorDao.getRegisteredRestaurantsList(offset, limit);
	}

	@Override
	public VendorDTO getVendorDetails(String vendorUUID) throws Exception {
		return vendorDao.getVendorDetails(vendorUUID);
	}

	@Override
	public void saveVendorBasicDetails(VendorDetailsDTO vendorDetailsDTO) throws Exception {
		
		try {
		Vendors vendors = vendorDao.getVendorByUUID(vendorDetailsDTO.getVendorUUID());
		if(vendors != null) {
			
			vendors.setVendorName(vendorDetailsDTO.getVendorName());
			vendors.setVendorCode(vendorDetailsDTO.getVendorCode());
			vendors.setVendorMobile(vendorDetailsDTO.getVendorMobile());
			vendors.setVendorEmail(vendorDetailsDTO.getVendorEmail());
			vendors.setVendorCapacity(Integer.valueOf(vendorDetailsDTO.getVendorCapacity()));
			vendors.setEstabilshedYear(Integer.valueOf(vendorDetailsDTO.getEstablishedYear()));
			vendors.setCostForTwoPeople(Integer.valueOf(vendorDetailsDTO.getCostForTwoPeople()));
			vendors.setCurrency(vendorDetailsDTO.getCurrency());
			vendors.setVendorDescription(vendorDetailsDTO.getVendorDescription());
			vendors.setBestSellingItems(vendorDetailsDTO.getBestSellingItems());
			
			if (vendorDetailsDTO.getFileInfo() != null && !vendorDetailsDTO.getFileInfo().isEmpty()) {
				
				FileInfo fileInfo = vendorDetailsDTO.getFileInfo().get(0);
				vendors.setProfileImage(fileInfo.getFileURL().replaceAll(" ", "_"));
				try {
					FileUploadUtil.moveFile(staticPath, fileInfo, "vendor_profile_images");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			vendorDao.saveVendor(vendors);
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
		
	}

	@Override
	public VendorBankDetailsDTO getVendorBankDetails(String vendorUUID) throws Exception {
		VendorDTO vendorDTO = vendorDao.getVendorDetails(vendorUUID);
		return vendorDao.getVendorBankDetails(vendorDTO.getVendorId().longValue());
	}

	@Override
	public String saveVendorBankAccountDetails(VendorBankDetailsDTO vendorBankDetailsDTO) throws Exception {
		
		String queryExecutionStatus = null;
		try {
			VendorBankAccount bankAccount = vendorDao.getVendorBankDetailsByVendorUUID(vendorBankDetailsDTO.getVendorUUID());
			if(bankAccount != null) {
				bankAccount.setBeneficiaryName(vendorBankDetailsDTO.getBeneficiaryName());
				bankAccount.setBankName(vendorBankDetailsDTO.getBankName());
				bankAccount.setBankBranch(vendorBankDetailsDTO.getBankBranch());
				bankAccount.setBankCode(vendorBankDetailsDTO.getBankCode());
				bankAccount.setAccountNumber(vendorBankDetailsDTO.getAccountNumber());
				bankAccount.setAccountType(vendorBankDetailsDTO.getAccountType());
				vendorDao.saveVendorBankAccountDetails(bankAccount);
				queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
			}
		}catch (Exception e) {
			e.printStackTrace();
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
		}
		
		return queryExecutionStatus;
	}

	@Override
	public String saveVendorAddressDetails(VendorAddressDTO vendorAddressDTO) throws Exception {
		String queryExecutionStatus = null;
		try {
		Vendors vendors = vendorDao.getVendorByUUID(vendorAddressDTO.getVendorUUID());
		if(vendors != null) {
			
			vendors.setLocation(vendorAddressDTO.getVendorLocation());
			vendors.setCountry(vendorAddressDTO.getCountry());
			vendors.setState(vendorAddressDTO.getState());
			vendors.setCity(vendorAddressDTO.getCity());
			vendors.setPincode(vendorAddressDTO.getPincode());
			vendors.setLatitude(vendorAddressDTO.getLatitude());
			vendors.setLongitude(vendorAddressDTO.getLongitude());
			vendors.setPhoneNumber(vendorAddressDTO.getPhoneNumber());
			vendors.setAddress(vendorAddressDTO.getVendorAddress());
			vendorDao.saveVendor(vendors);
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
		}
	}catch (Exception e) {
		e.printStackTrace();
		queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
	}
		return queryExecutionStatus;	
	}

	@Override
	public List<VendorProfileCategoriesDTO> getAllcategoriesByType(String categoryType) throws Exception {
		return vendorDao.getAllcategoriesByType(categoryType);
	}

	@Override
	public String saveVendorProfileCategoriesDetails(VendorProfileCategoriesDTO vendorProfileCategoriesDTO)
			throws Exception {

		String queryExecutionStatus = null;
		try {
		Vendors vendors = vendorDao.getVendorByUUID(vendorProfileCategoriesDTO.getVendorUUID());
		if(vendors != null) {
			
			if(vendorProfileCategoriesDTO.getCategoryType().equalsIgnoreCase(Constants.VENDOR_PROFILE_CATEGORIES_TYPE)) {
				vendors.setVendorCategories(vendorProfileCategoriesDTO.getCategoriesIds());
			}else if(vendorProfileCategoriesDTO.getCategoryType().equalsIgnoreCase(Constants.VENDOR_PROFILE_FACILITIES_TYPE)) {
				vendors.setVendorFacilities(vendorProfileCategoriesDTO.getFacilitiesIds());
			}else if(vendorProfileCategoriesDTO.getCategoryType().equalsIgnoreCase(Constants.VENDOR_PROFILE_MUSIC_GENRE_TYPE)) {
				vendors.setVendorMusicGenre(vendorProfileCategoriesDTO.getMusicIds());
			}else if(vendorProfileCategoriesDTO.getCategoryType().equalsIgnoreCase(Constants.VENDOR_PROFILE_CUISINE_TYPE)) {
				vendors.setVendorCuisine(vendorProfileCategoriesDTO.getCuisineIds());
			}
			vendorDao.saveVendor(vendors);
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
		}
	}catch (Exception e) {
		e.printStackTrace();
		queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
	}
		return queryExecutionStatus;	
	
	}

	@Override
	public Vendors getVendorByUUID(String vendorUUID) throws Exception {
		return vendorDao.getVendorByUUID(vendorUUID);
	}

	@Override
	public VendorProfileCategoriesDTO getVendorProflieCategories(String vendorUUID) throws Exception {
		return vendorDao.getVendorProflieCategories(vendorUUID);
	}

	@Override
	public String saveVendorWorkingHoursDetails(VendorWorkingHoursDTO vendorsWorkingHoursDTO, String vendorUUID) throws Exception {
		
	String queryExecutionStatus = null;
	if (vendorsWorkingHoursDTO.getTimeSchedulerInfo() != null && !vendorsWorkingHoursDTO.getTimeSchedulerInfo().isEmpty()) {
		try {
		   JSONArray array = new JSONArray();
			for(TimeSchedulerInfo timeSchedulerInfo : vendorsWorkingHoursDTO.getTimeSchedulerInfo()) {
				JSONObject documentItem = new JSONObject();
				documentItem.put("workingDay", timeSchedulerInfo.getWorkingDay());
				documentItem.put("startTime", timeSchedulerInfo.getStartTime());
				documentItem.put("endTime", timeSchedulerInfo.getEndTime());
				array.put(documentItem);
			}
			String timeSchedulerJsonArry = array.toString();
			
				Vendors vendors = vendorDao.getVendorByUUID(vendorUUID);
				if(vendors != null) {
					
					vendors.setWorkingHours(timeSchedulerJsonArry);
					vendorDao.saveVendor(vendors);
					queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
				}
			}catch (Exception e) {
				e.printStackTrace();
				queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
			}
		}
		
		return queryExecutionStatus;
	}

	@Override
	public List<TimeSchedulerInfo> getVendorWorkingHoursDetails(String vendorUUID) throws Exception {
		
		VendorCustomDetailsDTO vendorCustomDetailsDTO = vendorDao.getVendorCustomDetailsDTO(vendorUUID);
		
		List<TimeSchedulerInfo> timeSchedulerInfoList = null;
		if(StringUtils.isNotBlank(vendorCustomDetailsDTO.getWorkingHours())) {
			timeSchedulerInfoList = new ArrayList<TimeSchedulerInfo>();
			JSONParser parser = new JSONParser();
			try {
				org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(vendorCustomDetailsDTO.getWorkingHours());
				  for (Iterator it = jsonArray.iterator(); it.hasNext();) {
					    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
					   
					    String startTime = ((String) jsonObject.get("startTime")).trim();
					    String endTime = ((String) jsonObject.get("endTime")).trim();
					    String workingDay = ((String) jsonObject.get("workingDay")).trim();
					    TimeSchedulerInfo timeSchedulerInfo = new TimeSchedulerInfo();
					    timeSchedulerInfo.setStartTime(startTime);
					    timeSchedulerInfo.setEndTime(endTime);
					    timeSchedulerInfo.setWorkingDay(workingDay);
					    timeSchedulerInfoList.add(timeSchedulerInfo);
				 }
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return timeSchedulerInfoList;
	}

	@Override
	public VendorCustomDetailsDTO getVendorTermsAndCondtionsDetails(String vendorUUID) throws Exception {
		VendorCustomDetailsDTO vendorCustomDetailsDTO = vendorDao.getVendorCustomDetailsDTO(vendorUUID);
		return vendorCustomDetailsDTO;
	}

	@Override
	public String saveVendorTermsAndConditions(VendorCustomDetailsDTO vendorCustomDetailsDTO, String vendorUUID)
			throws Exception {

		String queryExecutionStatus = null;
		try {
		Vendors vendors = vendorDao.getVendorByUUID(vendorUUID);
		if(vendors != null) {
			vendors.setTermsAndConditions(vendorCustomDetailsDTO.getTermsAndConditions());
			vendorDao.saveVendor(vendors);
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
		}
	}catch (Exception e) {
		e.printStackTrace();
		queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
	}
		return queryExecutionStatus;	
	
	}

	@Override
	public Long getVendorIdByUUID(String vendorUUID) throws Exception {
		return vendorDao.getVendorIdByUUID(vendorUUID);
	}

	@Override
	public String saveVendorImages(VendorImagesDTO vendorImagesDTO) throws Exception {

		String queryExecutionStatus = null;
		try {
				Vendors vendors = vendorDao.getVendorByUUID(vendorImagesDTO.getVendorUUID());
				if(vendors != null) {
					StringBuilder fileUrl = new StringBuilder();
					String imagesUrl = null;
					if (vendorImagesDTO.getFileInfo() != null && !vendorImagesDTO.getFileInfo().isEmpty()) {
						for(FileInfo fileInfo : vendorImagesDTO.getFileInfo()) {
							if(fileInfo != null && StringUtils.isNotBlank(fileInfo.getFileURL())){
								fileUrl.append(fileInfo.getFileURL()).append(",");
								if(StringUtils.isBlank(fileInfo.getIsSavedImg())) {
									 FileUploadUtil.moveFile(staticPath, fileInfo, "vendor_images");
								}
							}
						}
						imagesUrl = fileUrl.substring(0, fileUrl.length() - 1).toString();
					}
					
					if(vendorImagesDTO.getImageType().equalsIgnoreCase("Slider")) {
						vendors.setSliderImages(imagesUrl);
					}else if(vendorImagesDTO.getImageType().equalsIgnoreCase("Gallery")) {
						vendors.setGalleryImages(imagesUrl);
					}else if(vendorImagesDTO.getImageType().equalsIgnoreCase("Menu")) {
						vendors.setMenuImages(imagesUrl);
					}else if(vendorImagesDTO.getImageType().equalsIgnoreCase("Videos")) {
						vendors.setVendorVideos(imagesUrl);
					}
					
					vendorDao.saveVendor(vendors);
					queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
				}
			}catch (Exception e) {
				e.printStackTrace();
				queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
			}
		return queryExecutionStatus;	
	
	}

	@Override
	public boolean isVendorExistedByEmailId(String emailId) throws Exception{
		return vendorDao.isVendorExistedByEmailId(emailId);
	}

	@Override
	public boolean isVendorExistedByPhoneNumber(String mobileNumber) throws Exception{
		return vendorDao.isVendorExistedByPhoneNumber(mobileNumber);
	}

	@Override
	public boolean isVendorExistedByVendorCode(String vendorCode) throws Exception {
		return vendorDao.isVendorExistedByVendorCode(vendorCode);
	}

	@Override
	public List<ServicesDTO> getVendorServices() {
		return vendorDao.getVendorServices();
	}

	@Override
	public Long getServiceIdByUUID(String serviceUUID) throws Exception {
		return vendorDao.getServiceIdByUUID(serviceUUID);
	}

	@Override
	public Long getServiceCategoryIdByUUID(String categoryUUID) throws Exception {
		return vendorDao.getServiceCategoryIdByUUID(categoryUUID);
	}


}
