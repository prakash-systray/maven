package com.acculytixs.wayuparty.services.impl.user;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang.StringUtils;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.acculytixs.wayuparty.dao.user.UserDao;
import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.dto.user.UserLoginDTO;
import com.acculytixs.wayuparty.dto.user.UserProfileDTO;
import com.acculytixs.wayuparty.dto.user.UserRegistrationDTO;
import com.acculytixs.wayuparty.dto.vendor.TimeSchedulerInfo;
import com.acculytixs.wayuparty.dto.vendor.VendorInfoDTO;
import com.acculytixs.wayuparty.entity.WayupartyRoles;
import com.acculytixs.wayuparty.entity.email.EmailMessages;
import com.acculytixs.wayuparty.entity.user.User;
import com.acculytixs.wayuparty.security.WayupartyPasswordEncoder;
import com.acculytixs.wayuparty.services.user.UserService;
import com.acculytixs.wayuparty.services.vendor.VendorService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.EmailUtil;
import com.acculytixs.wayuparty.util.FileInfo;
import com.acculytixs.wayuparty.util.FileUploadUtil;
import com.acculytixs.wayuparty.util.RandomCodeHelper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	WayupartyPasswordEncoder wayupartyPasswordEncoder;
	
	@Autowired
	VendorService vendorService;
	
	@Value("${fromEmailAddress}")
	private String fromEmailAddress;
	
	@Value("${fromEmailPassword}")
	private String fromEmailPassword;
	
	@Value("${smtphost}")
	private String smtphost;
	
	@Value("${port}")
	private Integer port;
	
	@Value("${appUrl}")
	private String appUrl;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Value("${static.path}")
	private String staticPath;
	

	@Override
	public boolean isUserExistedByEmailId(String emailId) throws Exception {
		return userDao.isUserExistedByEmailId(emailId);
	}

	@Override
	public boolean isUserExistedByMobileNumber(String mobileNumber) throws Exception {
		return userDao.isUserExistedByMobileNumber(mobileNumber);
	}

	@Override
	public UserDTO getUserDetailsByUUID(String userUUID) throws Exception {
		return userDao.getUserDetailsByUUID(userUUID);
	}

	@Override
	public String saveNewUserRegistration(UserRegistrationDTO userRegistrationDTO) throws Exception {
		
		String queryExecutionStatus = null;
		try {
				User user = new User();
				user.setFirstName(userRegistrationDTO.getLoginUserName());
				user.setMobileNumber(userRegistrationDTO.getMobile());
				user.setEmail(userRegistrationDTO.getEmail());
				user.setUserName(userRegistrationDTO.getEmail());
				user.setPassword(wayupartyPasswordEncoder.encode(userRegistrationDTO.getPassword()));
				WayupartyRoles roles = new WayupartyRoles();
				roles.setId(Constants.ROLE_USER);
				user.setRoleId(roles);
				user.setUserStatus(Constants.USER_NOT_LOGGED_IN);
				user.setIsEmailVerified(Constants.STRING_N);
				user.setUuid(RandomCodeHelper.generateRandomUUID());
				user.setStatus(1);
				user.setCreatedDate(new Date());
				userDao.saveUser(user);
				
				sendActivationEmail(userRegistrationDTO, user);
				
				queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
		}catch (Exception e) {
			e.printStackTrace();
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
		}
		
		return queryExecutionStatus;
	}
	
	public void sendActivationEmail(UserRegistrationDTO userRegistrationDTO,User user) {
		 EmailMessages emailMessages = new EmailMessages();
		 emailMessages.setLoginUserName(userRegistrationDTO.getLoginUserName());
		 emailMessages.setToEmail(userRegistrationDTO.getEmail());
		 emailMessages.setUserName(userRegistrationDTO.getEmail());
		 emailMessages.setPassword(userRegistrationDTO.getPassword());
		 emailMessages.setFromEmail(fromEmailAddress);
		 emailMessages.setFromPassword(fromEmailPassword);
		 emailMessages.setSmtphost(smtphost);
		 emailMessages.setPort(port);
		 
		 emailMessages.setFromName(Constants.FROM_EMAIL_NAME);
		 emailMessages.setSubject("Verification Link :: WAYUPARTY");
		 
		 Map<String, String> globalVarsMap = new HashMap<String, String>();
		 globalVarsMap.put("registerName", userRegistrationDTO.getLoginUserName());
		 StringBuilder verifylink = new StringBuilder();
		 verifylink.append("<a href='").append(appUrl).append("/ws/activateEmail?&tocken=").append(user.getUuid()).append("'>").append(appUrl).append("/ws/activateEmail?&tocken=").append(user.getUuid()).append("</a>").toString();
	     
		 emailMessages.setVerificationLink(verifylink.toString());
		 emailUtil.sendTemplateEmail(emailMessages);
	}

	@Override
	public VendorInfoDTO getVendorInfoDetails(String vendorUUID) throws Exception {
		
		VendorInfoDTO vendorInfoDTO =  userDao.getVendorInfoDetails(vendorUUID);
		List<String> categoriesList = Collections.<String>emptyList();
		List<String> facilitiesList = Collections.<String>emptyList();
		List<String> musicList = Collections.<String>emptyList();
		List<String> cuisineList = Collections.<String>emptyList();
		List<TimeSchedulerInfo> workingHoursList = Collections.<TimeSchedulerInfo>emptyList();
		List<String> menusList = Collections.<String>emptyList();
		List<String> slidersList = Collections.<String>emptyList();
		List<String> galleryList = Collections.<String>emptyList();
		List<String> videosList = Collections.<String>emptyList();
		
		if(StringUtils.isNotBlank(vendorInfoDTO.getCategories())) {
			List<Long> categoriesIdsList = new ArrayList<Long>();
			for (String s : Arrays.stream(vendorInfoDTO.getCategories().split(",")).filter(value -> value != null && value.length() > 0).toArray(size -> new String[size])){
				categoriesIdsList.add(Long.parseLong(s));
        	 }
			categoriesList = userDao.getVendorCustomCategories(categoriesIdsList);
		}
		
		if(StringUtils.isNotBlank(vendorInfoDTO.getFacilities())) {
			List<Long> facilitiesIdsList = new ArrayList<Long>();
			for (String s : Arrays.stream(vendorInfoDTO.getFacilities().split(",")).filter(value -> value != null && value.length() > 0).toArray(size -> new String[size])){
				facilitiesIdsList.add(Long.parseLong(s));
        	 }
			facilitiesList = userDao.getVendorCustomCategories(facilitiesIdsList);
		}
		
		if(StringUtils.isNotBlank(vendorInfoDTO.getMusic())) {
			List<Long> musicIdsList = new ArrayList<Long>();
			for (String s : Arrays.stream(vendorInfoDTO.getMusic().split(",")).filter(value -> value != null && value.length() > 0).toArray(size -> new String[size])){
				musicIdsList.add(Long.parseLong(s));
        	 }
			musicList = userDao.getVendorCustomCategories(musicIdsList);
		}
		
		if(StringUtils.isNotBlank(vendorInfoDTO.getCuisine())) {
			List<Long> cuisineIdsList = new ArrayList<Long>();
			for (String s : Arrays.stream(vendorInfoDTO.getCuisine().split(",")).filter(value -> value != null && value.length() > 0).toArray(size -> new String[size])){
				cuisineIdsList.add(Long.parseLong(s));
        	 }
			cuisineList = userDao.getVendorCustomCategories(cuisineIdsList);
		}
		
		if(StringUtils.isNotBlank(vendorInfoDTO.getWorkingHours())) {
			workingHoursList = new CopyOnWriteArrayList<TimeSchedulerInfo>();
			JSONParser parser = new JSONParser();
				org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray)parser.parse(vendorInfoDTO.getWorkingHours());
				  for (Iterator it = jsonArray.iterator(); it.hasNext();) {
					    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) it.next();
					   
					    String startTime = ((String) jsonObject.get("startTime")).trim();
					    String endTime = ((String) jsonObject.get("endTime")).trim();
					    String workingDay = ((String) jsonObject.get("workingDay")).trim();
					    TimeSchedulerInfo timeSchedulerInfo = new TimeSchedulerInfo();
					    timeSchedulerInfo.setStartTime(startTime);
					    timeSchedulerInfo.setEndTime(endTime);
					    timeSchedulerInfo.setWorkingDay(workingDay);
					    workingHoursList.add(timeSchedulerInfo);
				 }
		}
		
		

		if(StringUtils.isNotBlank(vendorInfoDTO.getSliders())) {
			slidersList = new CopyOnWriteArrayList<String>();
			for (String sliders : Arrays.stream(vendorInfoDTO.getSliders().split(",")).filter(value -> value != null && value.length() > 0).toArray(size -> new String[size])){
				slidersList.add(sliders);
        	 }
		}
		
		if(StringUtils.isNotBlank(vendorInfoDTO.getMenus())) {
			menusList = new CopyOnWriteArrayList<String>();
			for (String menus : Arrays.stream(vendorInfoDTO.getMenus().split(",")).filter(value -> value != null && value.length() > 0).toArray(size -> new String[size])){
				menusList.add(menus);
        	 }
		}
		
		if(StringUtils.isNotBlank(vendorInfoDTO.getGallery())) {
			galleryList = new CopyOnWriteArrayList<String>();
			for (String gallery : Arrays.stream(vendorInfoDTO.getGallery().split(",")).filter(value -> value != null && value.length() > 0).toArray(size -> new String[size])){
				galleryList.add(gallery);
        	 }
		}
		
		if(StringUtils.isNotBlank(vendorInfoDTO.getVideos())) {
			videosList = new CopyOnWriteArrayList<String>();
			for (String videos : Arrays.stream(vendorInfoDTO.getVideos().split(",")).filter(value -> value != null && value.length() > 0).toArray(size -> new String[size])){
				videosList.add(videos);
        	 }
		}
		
		vendorInfoDTO.setCategoriesList(categoriesList);
		vendorInfoDTO.setFacilitiesList(facilitiesList);
		vendorInfoDTO.setMusicList(musicList);
		vendorInfoDTO.setCuisineList(cuisineList);
		vendorInfoDTO.setWorkingHoursList(workingHoursList);
		vendorInfoDTO.setMenuList(menusList);
		vendorInfoDTO.setSliderList(slidersList);
		vendorInfoDTO.setGalleryList(galleryList);
		vendorInfoDTO.setVideoList(videosList);
		
		return vendorInfoDTO;
	}

	@Override
	public List<String> getVendorCustomCategories(List<Long> categoriesIds) throws Exception {
		return userDao.getVendorCustomCategories(categoriesIds);
	}

	@Override
	public String saveUserProfile(UserProfileDTO userProfileDTO) throws Exception {
		String queryExecutionStatus = null;
		if(StringUtils.isNotBlank(userProfileDTO.getUserUUID())) {
			try {
					User user = userDao.getUserByUUID(userProfileDTO.getUserUUID());
					user.setFirstName(userProfileDTO.getFirstName());
					user.setLastName(userProfileDTO.getLastName());
					user.setMobileNumber(userProfileDTO.getMobile());
					user.setEmail(userProfileDTO.getEmail());
					Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(userProfileDTO.getDob());  
					user.setDob(dob);
					if (userProfileDTO.getFileInfo() != null && !userProfileDTO.getFileInfo().isEmpty()) {
						
						FileInfo fileInfo = userProfileDTO.getFileInfo().get(0);
						user.setUserImage(fileInfo.getFileURL().replaceAll(" ", "_"));
						try {
							FileUploadUtil.moveFile(staticPath, fileInfo, "user_profile_images");
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
					
					userDao.saveUser(user);
					
					queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
			}catch (Exception e) {
				e.printStackTrace();
				queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
			}
			
		}
		return queryExecutionStatus;
	}

	@Override
	public User getUserByUUID(String uuid) throws Exception {
		return userDao.getUserByUUID(uuid);
	}

	@Override
	public String resetUserPassword(String password, String userUUID) throws Exception {
		String queryExecutionStatus = null;
		if(StringUtils.isNotBlank(password)) {
			try {
					User user = userDao.getUserByUUID(userUUID);
					user.setPassword(wayupartyPasswordEncoder.encode(password));
					userDao.saveUser(user);
					
					queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
			}catch (Exception e) {
				e.printStackTrace();
				queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
			}
			
		}
		return queryExecutionStatus;
	}

	@Override
	public UserLoginDTO getLoginUserDetails(Long userId) throws Exception {
		return userDao.getLoginUserDetails(userId);
	}

	@Override
	public void saveUser(User user) throws Exception {
		userDao.saveUser(user);		
	}

}
