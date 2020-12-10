package com.acculytixs.wayuparty.dao.impl.vendor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acculytixs.wayuparty.dao.vendor.VendorServicesDao;
import com.acculytixs.wayuparty.dto.services.ServicesInfoDTO;
import com.acculytixs.wayuparty.dto.services.VendorServicesDTO;
import com.acculytixs.wayuparty.dto.vendor.ServiceSubCategoryDTO;
import com.acculytixs.wayuparty.dto.vendor.ServicesCategoryDTO;
import com.acculytixs.wayuparty.entity.services.VendorMasterService;
import com.acculytixs.wayuparty.entity.services.WayupartyServiceSubCategory;
import com.acculytixs.wayuparty.util.ResponseList;

@Repository
@Transactional
public class VendorServicesDaoImpl implements VendorServicesDao{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<ServicesCategoryDTO> getServiceCategoriesByServiceId(Long serviceId) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<ServicesCategoryDTO> servicesCategoryDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" category.id AS categoryId, "
				+" category.category_name AS categoryName, "
				+" category.uuid AS categoryUUID "
				
			+" FROM "
			     + "wayuparty_serivce_category category where category.status = '1' and category.service_id = :serviceId ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("serviceId", serviceId);
		queryObj.setResultTransformer(Transformers.aliasToBean(ServicesCategoryDTO.class));
		servicesCategoryDTOList = queryObj.list();
		
		return servicesCategoryDTOList;
	}

	@Override
	public WayupartyServiceSubCategory getWayupartyServiceSubCategoryByUUID(String uuid) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from WayupartyServiceSubCategory subCategory left join fetch subCategory.vendorId left join fetch subCategory.categoryId where subCategory.uuid = :uuid ";
		List<WayupartyServiceSubCategory> subCategoryList = currentSession.createQuery(query).setString("uuid", uuid).list();
		WayupartyServiceSubCategory wayupartyServiceSubCategory = null;
		if (subCategoryList != null && subCategoryList.size() > 0) {
			wayupartyServiceSubCategory = (WayupartyServiceSubCategory) subCategoryList.iterator().next();
		}
		return wayupartyServiceSubCategory;
	}

	@Override
	public void saveServiceSubCategory(WayupartyServiceSubCategory wayupartyServiceSubCategory) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(wayupartyServiceSubCategory);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<ServiceSubCategoryDTO> getVendorServiceSubCategories(Long categoryId, Long vendorId) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<ServiceSubCategoryDTO> serviceSubCategoryDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" subcategory.sub_category_name AS subCategoryName, "
				+" subcategory.id AS subCategoryId, "
				+" subcategory.uuid AS subCategoryUUID "
				
			+" FROM "
			     + "wayuparty_serivce_sub_category subcategory where subcategory.status = '1' and "
			     + "subcategory.category_id = :categoryId and subcategory.vendor_id = :vendorId ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("categoryId", categoryId);
		queryObj.setLong("vendorId", vendorId);
		queryObj.setResultTransformer(Transformers.aliasToBean(ServiceSubCategoryDTO.class));
		serviceSubCategoryDTOList = queryObj.list();
		
		return serviceSubCategoryDTOList;
	}

	@Override
	public void deleteServiceSubCategory(WayupartyServiceSubCategory wayupartyServiceSubCategory) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.delete(wayupartyServiceSubCategory);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void saveVendorMasterService(VendorMasterService vendorMasterService) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(vendorMasterService);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ResponseList<VendorServicesDTO> getVendorServicesList(Long serviceId, Long vendorId) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		ResponseList<VendorServicesDTO> response = new ResponseList<VendorServicesDTO>();
		Query queryObj = null;
		
		query = "SELECT " 
				+" (SELECT services.service_name FROM wayuparty_services services WHERE services.id = vendorService.service_id) AS service, "
				+" (SELECT category.category_name FROM wayuparty_serivce_category category WHERE category.id = vendorService.category_id) AS category, "
				+" IFNULL((SELECT subcategory.sub_category_name FROM wayuparty_serivce_sub_category subcategory where subcategory.id = vendorService.sub_category_id), vendorService.service_name) AS subCategory, "
				+" IFNULL(DATE_FORMAT(vendorService.service_start_date, '%d/%m/%Y'),'') AS serviceStartDate, "
				+" IFNULL(DATE_FORMAT(vendorService.service_end_date, '%d/%m/%Y'),'') AS serviceEndDate, "
				+" IFNULL(vendorService.service_image,'/resources/img/glass.jpg') AS serviceImage, "
				+" vendorService.uuid AS masterServiceUUID "
				
			+" FROM "
			     + "vendor_master_service vendorService where vendorService.status = '1' and "
			     + "vendorService.vendor_id = :vendorId and vendorService.service_id = :serviceId and DATEDIFF(vendorService.service_end_date,CURDATE()) >= 0 "
			     + "ORDER BY DATE_FORMAT(vendorService.created_date, '%d') DESC, DATE_FORMAT(vendorService.created_date, '%T') DESC ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("vendorId", vendorId);
		queryObj.setLong("serviceId", serviceId);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorServicesDTO.class));
		List<VendorServicesDTO> vendorServicesList = queryObj.list();
		response.setData(vendorServicesList);
		
		return response;
	}

	@Override
	public VendorServicesDTO getVendorServiceDetails(String serviceUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorServicesDTO> vendorServicesDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" (SELECT services.service_name FROM wayuparty_services services WHERE services.id = vendorService.service_id) AS service, "
				+" (SELECT category.category_name FROM wayuparty_serivce_category category WHERE category.id = vendorService.category_id) AS category, "
				+" IFNULL((SELECT subcategory.sub_category_name FROM wayuparty_serivce_sub_category subcategory where subcategory.id = vendorService.sub_category_id),vendorService.service_name) AS subCategory, "
				+" IFNULL(DATE_FORMAT(vendorService.service_start_date, '%d/%m/%Y'),'') AS serviceStartDate, "
				+" IFNULL(DATE_FORMAT(vendorService.service_end_date, '%d/%m/%Y'),'') AS serviceEndDate, "
				+" IFNULL(vendorService.service_image,'/resources/img/glass.jpg') AS serviceImage, "
				+" (SELECT vendors.currency FROM vendors vendors WHERE vendors.id = vendorService.vendor_id) AS currency, "
				+" vendorService.id AS serviceId, "
				+" vendorService.vendor_id AS vendorId, "
				+" vendorService.actual_price AS actualPrice, "
				+" vendorService.offer_price AS offerPrice, "
				+" vendorService.allowed AS allowed, "
				+" vendorService.terms_and_conditions AS termsAndConditions, "
				+" IFNULL(DATE_FORMAT(vendorService.service_start_date, '%b %e %Y'),'') AS startDate, "
				+" IFNULL(DATE_FORMAT(vendorService.service_end_date, '%b %e %Y'),'') AS endDate, "
				+" vendorService.service_description AS description, "
				+" vendorService.service_time_slots AS serviceTimeSlots, "
				+" IFNULL(vendorService.event_location,'') AS eventLocation, "
				+" IFNULL(vendorService.guest_entry_time,'') AS guestEntryTime, "
				+" IFNULL(vendorService.package_menu,'') AS packageMenu, "
				+" IFNULL(vendorService.artist,'') AS artist, "
				+" IFNULL(vendorService.music_genre,'') AS musicGenre, "
				+" vendorService.uuid AS masterServiceUUID "
				
				+" FROM "
			    + "vendor_master_service vendorService where vendorService.status = '1' and "
			    + "vendorService.uuid = :serviceUUID  ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("serviceUUID", serviceUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorServicesDTO.class));
		vendorServicesDTOList = queryObj.list();
		

		if(vendorServicesDTOList != null && !vendorServicesDTOList.isEmpty()) {
			return vendorServicesDTOList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public boolean isServiceExistsBySubCategoryIdAndVendorId(String serviceStartDate, String serviceEndDate, 
			Long subCategoryId, Long vendorId)
			throws Exception {
		
		boolean flag = false;
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		Query queryObj = null;
		
		query = "SELECT COUNT(*) " 
				+" FROM "
			    +" vendor_master_service vendorService where ((STR_TO_DATE('"+serviceStartDate+"', '%Y-%m-%d')  BETWEEN vendorService.service_start_date" + 
			    " and  vendorService.service_end_date) OR (STR_TO_DATE('"+serviceEndDate+"', '%Y-%m-%d')  BETWEEN vendorService.service_start_date" + 
			    " and  vendorService.service_end_date)) and vendorService.sub_category_id = :subCategoryId and "
			    +" vendorService.vendor_id = :vendorId and vendorService.status = '1'";
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("subCategoryId", subCategoryId);
		queryObj.setLong("vendorId", vendorId);
		BigInteger servicesCount = (BigInteger) queryObj.uniqueResult();
		if(servicesCount.intValue() > 0) {
			flag = true;
		}
		
		return flag;
	}
	
	@Override
	public boolean isSavedServiceExistsBySubCategoryIdAndVendorId(String serviceStartDate, String serviceEndDate, 
			Long subCategoryId, Long vendorId)
			throws Exception {
		
		boolean flag = false;
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		Query queryObj = null;
		
		query = "SELECT COUNT(*) " 
				+" FROM "
			    +" vendor_master_service vendorService where ((STR_TO_DATE('"+serviceStartDate+"', '%Y-%m-%d')  BETWEEN vendorService.service_start_date" + 
			    " and  vendorService.service_end_date) OR (STR_TO_DATE('"+serviceEndDate+"', '%Y-%m-%d')  BETWEEN vendorService.service_start_date" + 
			    " and  vendorService.service_end_date)) and vendorService.sub_category_id = :subCategoryId and "
			    +" vendorService.vendor_id = :vendorId and vendorService.status = '1'";
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("subCategoryId", subCategoryId);
		queryObj.setLong("vendorId", vendorId);
		BigInteger servicesCount = (BigInteger) queryObj.uniqueResult();
		if(servicesCount.intValue() > 1) {
			flag = true;
		}
		
		return flag;
	}

	@Override
	public ServicesInfoDTO getServicesList(String vendorUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<ServicesInfoDTO> VendorDetailsDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" vendors.vendor_name AS vendorName, "
				+" IFNULL(vendors.currency,'') AS currency, "
				+" IFNULL(vendors.vendor_description,'') AS description, "
				+" IFNULL(vendors.location,'') AS location, "
				+" IFNULL(vendors.longitude,'') AS longitude, "
				+" IFNULL(vendors.latitude,'') AS latitude, "
				+" IFNULL(vendors.profile_image,'') AS profileImage "
				
			+" FROM "
			     + "vendors vendors where vendors.uuid = :vendorUUID ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("vendorUUID", vendorUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(ServicesInfoDTO.class));
		VendorDetailsDTOList = queryObj.list();
		

		if(VendorDetailsDTOList != null && !VendorDetailsDTOList.isEmpty()) {
			return VendorDetailsDTOList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<VendorServicesDTO> getVendorCategoryServicesList(Long categoryId, Long vendorId)
			throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		List<VendorServicesDTO> vendorServicesDTOList = new ArrayList<VendorServicesDTO>();
		Query queryObj = null;
		
		query = "SELECT " 
				+" (SELECT services.service_name FROM wayuparty_services services WHERE services.id = vendorService.service_id) AS service, "
				+" (SELECT category.category_name FROM wayuparty_serivce_category category WHERE category.id = vendorService.category_id) AS category, "
				+" IFNULL((SELECT subcategory.sub_category_name FROM wayuparty_serivce_sub_category subcategory where subcategory.id = vendorService.sub_category_id),vendorService.service_name) AS subCategory, "
				+" (SELECT vendors.currency FROM vendors vendors WHERE vendors.id = vendorService.vendor_id) AS currency, "
				+" IFNULL(DATE_FORMAT(vendorService.service_start_date, '%Y-%m-%d'),'') AS serviceStartDate, "
				+" IFNULL(DATE_FORMAT(vendorService.service_end_date, '%Y-%m-%d'),'') AS serviceEndDate, "
				+" IFNULL(DATE_FORMAT(vendorService.service_start_date, '%b %e %Y'),'') AS startDate, "
				+" IFNULL(DATE_FORMAT(vendorService.service_end_date, '%b %e %Y'),'') AS endDate, "
				+" IFNULL(vendorService.service_image,'/resources/img/glass.jpg') AS serviceImage, "
				+" vendorService.id AS serviceId, "
				+" vendorService.vendor_id AS vendorId, "
				+" vendorService.actual_price AS actualPrice, "
				+" vendorService.offer_price AS offerPrice, "
				+" vendorService.allowed AS allowed, "
				+" vendorService.terms_and_conditions AS termsAndConditions, "
				+" vendorService.service_time_slots AS serviceTimeSlots, "
				+" IFNULL(vendorService.guest_entry_time,'') AS guestEntryTime, "
				+" IFNULL(vendorService.package_menu,'') AS packageMenu, "
				+" vendorService.uuid AS masterServiceUUID "
				
			+" FROM "
			     + "vendor_master_service vendorService where vendorService.status = '1' and "
			     + "vendorService.vendor_id = :vendorId and vendorService.category_id = :categoryId and DATEDIFF(vendorService.service_end_date,CURDATE()) >= 0 "
			     + "ORDER BY DATE_FORMAT(vendorService.created_date, '%d') DESC, DATE_FORMAT(vendorService.created_date, '%T') DESC ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("vendorId", vendorId);
		queryObj.setLong("categoryId", categoryId);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorServicesDTO.class));
		vendorServicesDTOList = queryObj.list();
		
		return vendorServicesDTOList;
	}

	@Override
	public boolean isServiceExistsByCategoryIdAndVendorId(String serviceStartDate, String serviceEndDate,
			Long categoryId, Long vendorId) throws Exception {
		
		boolean flag = false;
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		Query queryObj = null;
		
		query = "SELECT COUNT(*) " 
				+" FROM "
			    +" vendor_master_service vendorService where ((STR_TO_DATE('"+serviceStartDate+"', '%Y-%m-%d')  BETWEEN vendorService.service_start_date" + 
			    " and  vendorService.service_end_date) OR (STR_TO_DATE('"+serviceEndDate+"', '%Y-%m-%d')  BETWEEN vendorService.service_start_date" + 
			    " and  vendorService.service_end_date)) and vendorService.category_id = :categoryId and "
			    +" vendorService.vendor_id = :vendorId and vendorService.status = '1'";
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("categoryId", categoryId);
		queryObj.setLong("vendorId", vendorId);
		BigInteger servicesCount = (BigInteger) queryObj.uniqueResult();
		if(servicesCount.intValue() > 0) {
			flag = true;
		}
		
		return flag;
	}

	@Override
	public VendorServicesDTO getVendorServiceDetailsByMasterServiceUUID(String serviceUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		List<VendorServicesDTO> vendorServicesDTOList = new ArrayList<VendorServicesDTO>();
		Query queryObj = null;
		VendorServicesDTO vendorServicesDTO = null;
		
		query = "SELECT " 
				+" (SELECT services.service_name FROM wayuparty_services services WHERE services.id = vendorService.service_id) AS service, "
				+" (SELECT category.category_name FROM wayuparty_serivce_category category WHERE category.id = vendorService.category_id) AS category, "
				+" IFNULL((SELECT subcategory.sub_category_name FROM wayuparty_serivce_sub_category subcategory where subcategory.id = vendorService.sub_category_id),vendorService.service_name) AS subCategory, "
				+" IFNULL(DATE_FORMAT(vendorService.service_start_date, '%d/%m/%Y'),'') AS serviceStartDate, "
				+" IFNULL(DATE_FORMAT(vendorService.service_end_date, '%d/%m/%Y'),'') AS serviceEndDate, "
				+" IFNULL(vendorService.service_image,'') AS serviceImage, "
				+" vendorService.category_id AS categoryId, "
				+" vendorService.sub_category_id AS subCategoryId, "
				+" vendorService.actual_price AS actualPrice, "
				+" vendorService.offer_price AS offerPrice, "
				+" vendorService.allowed AS allowed, "
				+" vendorService.service_description AS description, "
				+" IFNULL(vendorService.service_offer,'') AS serviceOffer, "
				+" vendorService.terms_and_conditions AS termsAndConditions, "
				+" vendorService.service_time_slots AS serviceTimeSlots, "
				+" IFNULL(vendorService.guest_entry_time,'') AS guestEntryTime, "
				+" IFNULL(vendorService.event_location,'') AS eventLocation, "
				+" IFNULL(vendorService.artist,'') AS artist, "
				+" IFNULL(vendorService.music_genre,'') AS musicGenre, "
				+" vendorService.uuid AS masterServiceUUID "
				
			+" FROM "
			     + "vendor_master_service vendorService where vendorService.uuid = :serviceUUID  ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("serviceUUID", serviceUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorServicesDTO.class));
		vendorServicesDTOList = queryObj.list();
		
		if(vendorServicesDTOList != null && !vendorServicesDTOList.isEmpty()) {
			vendorServicesDTO = vendorServicesDTOList.get(0);
		}
		
		return vendorServicesDTO;
	}

	@Override
	public Long getVendorMasterServiceIdByUUID(String serviceUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		Query queryObj = null;

		query = "SELECT masterService.id " 
			+" FROM "
			     + "vendor_master_service masterService where  "
			     + "masterService.uuid = :serviceUUID  ";
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("serviceUUID", serviceUUID);
		BigInteger masterServiceId = (BigInteger) queryObj.uniqueResult();
		if(masterServiceId != null) {
			return masterServiceId.longValue();
		}else {
			return null;
		}
		
		
	}

	@Override
	public String getServiceImageByUUID(String serviceUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		String locationQuery =  "SELECT "
				+" vendorService.service_image "           
				+" FROM vendor_master_service vendorService "
				+" WHERE vendorService.uuid = :serviceUUID ";
		
		Query queryObj = currentSession.createSQLQuery(locationQuery);
		queryObj.setString("serviceUUID", serviceUUID);
		String serviceImage = (String) queryObj.uniqueResult();
		return serviceImage;
	}

	@Override
	public boolean isSavedServiceExistsByCategoryIdAndVendorId(String serviceStartDate, String serviceEndDate,
			Long categoryId, Long vendorId) throws Exception {
		
		boolean flag = false;
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		Query queryObj = null;
		
		query = "SELECT COUNT(*) " 
				+" FROM "
			    +" vendor_master_service vendorService where ((STR_TO_DATE('"+serviceStartDate+"', '%Y-%m-%d')  BETWEEN vendorService.service_start_date" + 
			    " and  vendorService.service_end_date) OR (STR_TO_DATE('"+serviceEndDate+"', '%Y-%m-%d')  BETWEEN vendorService.service_start_date" + 
			    " and  vendorService.service_end_date)) and vendorService.category_id = :categoryId and "
			    +" vendorService.vendor_id = :vendorId and vendorService.status = '1'";
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("categoryId", categoryId);
		queryObj.setLong("vendorId", vendorId);
		BigInteger servicesCount = (BigInteger) queryObj.uniqueResult();
		if(servicesCount.intValue() > 1) {
			flag = true;
		}
		
		return flag;
	}

	@Override
	public String getPackageMenuDetailsByUUID(String serviceUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		String locationQuery =  "SELECT "
				+" vendorService.package_menu "           
				+" FROM vendor_master_service vendorService "
				+" WHERE vendorService.uuid = :serviceUUID ";
		
		Query queryObj = currentSession.createSQLQuery(locationQuery);
		queryObj.setString("serviceUUID", serviceUUID);
		String serviceImage = (String) queryObj.uniqueResult();
		return serviceImage;
	}

	@Override
	public VendorMasterService geVendorMasterServiceByUUID(String uuid) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from VendorMasterService masterService where masterService.uuid = :uuid ";
		List<VendorMasterService> masterServiceList = currentSession.createQuery(query).setString("uuid", uuid).list();
		VendorMasterService masterService = null;
		if (masterServiceList != null && masterServiceList.size() > 0) {
			masterService = (VendorMasterService) masterServiceList.iterator().next();
		}
		return masterService;
	}

}
