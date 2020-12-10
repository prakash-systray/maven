package com.acculytixs.wayuparty.dao.impl.vendor;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acculytixs.wayuparty.dao.vendor.VendorDao;
import com.acculytixs.wayuparty.dto.services.ServicesDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorBankDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorCustomDetailsDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorProfileCategoriesDTO;
import com.acculytixs.wayuparty.entity.vendor.VendorBankAccount;
import com.acculytixs.wayuparty.entity.vendor.Vendors;

@Repository
@Transactional
public class VendorDaoImpl implements VendorDao{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public void saveVendor(Vendors vendors) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(vendors);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<VendorDTO> getRegisteredVendors() throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorDTO> vendorDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" vendors.vendor_name AS vendorName, "
				+" vendors.vendor_code AS vendorCode, "
				+" vendors.vendor_email AS vendorEmail, "
				+" vendors.vendor_mobile AS vendorMobile, "
				+" IFNULL(vendors.profile_image,'') AS vendorProfileImg, "
				+" vendors.established_year AS establishedYear, "
				+" vendors.vendor_capacity AS vendorCapacity, "
				+" vendors.cost_for_two_people AS costForTwoPeople, "
				+" IFNULL(vendors.location,'') AS location, "
				+" IFNULL(vendors.best_selling_items,'') AS bestSellingItems, "
				+" vendors.uuid AS vendorUUID "
				
			+" FROM "
			     + "vendors vendors where vendors.status = '1'  ORDER BY DATE_FORMAT(vendors.created_date, '%d') DESC, DATE_FORMAT(vendors.created_date, '%T') DESC ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorDTO.class));
		vendorDTOList = queryObj.list();
		
		return vendorDTOList;
	}

	@Override
	public VendorDTO getVendorDetails(String vendorUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorDTO> vendorDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" vendors.id AS vendorId, "
				+" vendors.vendor_name AS vendorName, "
				+" vendors.vendor_code AS vendorCode, "
				+" vendors.vendor_email AS vendorEmail, "
				+" vendors.vendor_mobile AS vendorMobile, "
				+" IFNULL(vendors.profile_image,'') AS vendorProfileImg, "
				+" vendors.established_year AS establishedYear, "
				+" vendors.vendor_capacity AS vendorCapacity, "
				+" vendors.cost_for_two_people AS costForTwoPeople, "
				+" vendors.currency AS currency, "
				+" vendors.vendor_description AS vendorDescription, "
				+" vendors.best_selling_items AS bestSellingItems, "
				+" vendors.location AS location, "
				+" vendors.country AS country, "
				+" vendors.state AS state, "
				+" vendors.city AS city, "
				+" vendors.pincode AS pincode, "
				+" vendors.latitude AS latitude, "
				+" vendors.longitude AS longitude, "
				+" vendors.phone_number AS phoneNumber, "
				+" vendors.address AS vendorAddress, "
				+" vendors.uuid AS vendorUUID "
				
			+" FROM "
			     + "vendors vendors where vendors.uuid = :vendorUUID ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("vendorUUID", vendorUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorDTO.class));
		vendorDTOList = queryObj.list();
		

		if(vendorDTOList != null && !vendorDTOList.isEmpty()) {
			return vendorDTOList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Vendors getVendorByUUID(String vendorUUID) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from Vendors vendors where vendors.uuid = :vendorUUID ";
		List<Vendors> vendorsList = currentSession.createQuery(query).setString("vendorUUID", vendorUUID).list();
		Vendors vendors = null;
		if (vendorsList != null && vendorsList.size() > 0) {
			vendors = (Vendors) vendorsList.iterator().next();
		}
		return vendors;
	}

	@Override
	public VendorBankDetailsDTO getVendorBankDetails(Long vendorId) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorBankDetailsDTO> vendorBankDetailsDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" bank.beneficiary_name AS beneficiaryName, "
				+" bank.account_number AS accountNumber, "
				+" bank.bank_name AS bankName, "
				+" bank.bank_branch AS bankBranch, "
				+" bank.bank_code AS bankCode, "
				+" bank.account_type AS accountType "
				
			+" FROM "
			     + "vendor_bank_account bank where bank.vendor_id = :vendorId ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("vendorId", vendorId);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorBankDetailsDTO.class));
		vendorBankDetailsDTOList = queryObj.list();
		

		if(vendorBankDetailsDTOList != null && !vendorBankDetailsDTOList.isEmpty()) {
			return vendorBankDetailsDTOList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public VendorBankAccount getVendorBankDetailsByVendorUUID(String vendorUUID) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from VendorBankAccount bank left join fetch bank.vendorId vendors where vendors.uuid = :vendorUUID ";
		List<VendorBankAccount> vendorsAccountDetailsList = currentSession.createQuery(query).setString("vendorUUID", vendorUUID).list();
		VendorBankAccount vendorBankAccount = null;
		if (vendorsAccountDetailsList != null && vendorsAccountDetailsList.size() > 0) {
			vendorBankAccount = (VendorBankAccount) vendorsAccountDetailsList.iterator().next();
		}
		return vendorBankAccount;
	}

	@Override
	public void saveVendorBankAccountDetails(VendorBankAccount vendorBankAccount) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(vendorBankAccount);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<VendorProfileCategoriesDTO> getAllcategoriesByType(String categoryType) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorProfileCategoriesDTO> profileCategoriesDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" categories.id AS categoryId, "
				+" categories.category_name AS categoryName "
				
			+" FROM "
			     + "wayuparty_categories categories where categories.status = '1'  and categories.category_type = :categoryType ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("categoryType", categoryType);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorProfileCategoriesDTO.class));
		profileCategoriesDTOList = queryObj.list();
		
		return profileCategoriesDTOList;
	}

	@Override
	public VendorProfileCategoriesDTO getVendorProflieCategories(String vendorUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorProfileCategoriesDTO> vendorProfileCategoriesDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" vendors.vendor_categories AS categoriesIds, "
				+" vendors.vendor_facilities AS facilitiesIds, "
				+" vendors.vendor_music_genre AS musicIds, "
				+" vendors.vendor_cuisine AS cuisineIds "
				
			+" FROM "
			     + "vendors vendors where vendors.uuid = :vendorUUID ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("vendorUUID", vendorUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorProfileCategoriesDTO.class));
		vendorProfileCategoriesDTOList = queryObj.list();
		

		if(vendorProfileCategoriesDTOList != null && !vendorProfileCategoriesDTOList.isEmpty()) {
			return vendorProfileCategoriesDTOList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public VendorCustomDetailsDTO getVendorCustomDetailsDTO(String vendorUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorCustomDetailsDTO> VendorCustomDetailsDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" vendors.working_hours AS workingHours, "
				+" vendors.terms_conditions AS termsAndConditions, "
				+" vendors.slider_images AS sliderImages, "
				+" vendors.gallery_images AS galleryImages, "
				+" vendors.menu_images AS menuImages, "
				+" vendors.vendor_videos AS vendorVideos "
				
			+" FROM "
			     + "vendors vendors where vendors.uuid = :vendorUUID ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("vendorUUID", vendorUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorCustomDetailsDTO.class));
		VendorCustomDetailsDTOList = queryObj.list();
		

		if(VendorCustomDetailsDTOList != null && !VendorCustomDetailsDTOList.isEmpty()) {
			return VendorCustomDetailsDTOList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Long getVendorIdByUUID(String vendorUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		String locationQuery =  "SELECT "
				+" vendors.id "           
				+" FROM vendors vendors "
				+" WHERE vendors.uuid = :vendorUUID ";
		
		Query queryObj = currentSession.createSQLQuery(locationQuery);
		queryObj.setString("vendorUUID", vendorUUID);
		BigInteger vendorId = (BigInteger) queryObj.uniqueResult();
		if(vendorId != null) {
			return vendorId.longValue();
		}else {
			return null;
		}
		
	}

	@Override
	public boolean isVendorExistedByEmailId(String emailId) throws Exception{
		boolean flag = false;
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		try {
			  String query = "select id from Vendors vendors where vendors.vendorEmail = :emailId and vendors.status = '1' ";
			  Query queryObj =  currentSession.createQuery(query).setString("emailId", emailId);
			  Long vendorId = (Long) queryObj.uniqueResult();
			  if(vendorId != null) {
				  flag = true;
			  }
		}catch (Exception e) {
			 e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public boolean isVendorExistedByPhoneNumber(String mobileNumber) throws Exception{
		boolean flag = false;
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		try {
			  String query = "select id from Vendors vendors where vendors.vendorMobile = :mobileNumber and vendors.status = '1' ";
			  Query queryObj =  currentSession.createQuery(query).setString("mobileNumber", mobileNumber);
			  Long vendorId = (Long) queryObj.uniqueResult();
			  if(vendorId != null) {
				  flag = true;
			  }
		}catch (Exception e) {
			 e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public boolean isVendorExistedByVendorCode(String vendorCode) throws Exception {
		boolean flag = false;
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		try {
			  String query = "select id from Vendors vendors where vendors.vendorCode = :vendorCode and vendors.status = '1' ";
			  Query queryObj =  currentSession.createQuery(query).setString("vendorCode", vendorCode);
			  Long vendorId = (Long) queryObj.uniqueResult();
			  if(vendorId != null) {
				  flag = true;
			  }
		}catch (Exception e) {
			 e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public List<VendorDTO> getRegisteredRestaurantsList(Integer offset, Integer limit) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorDTO> vendorDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" vendors.vendor_name AS vendorName, "
				+" vendors.vendor_code AS vendorCode, "
				+" vendors.vendor_email AS vendorEmail, "
				+" vendors.vendor_mobile AS vendorMobile, "
				+" IFNULL(vendors.profile_image,'') AS vendorProfileImg, "
				+" vendors.established_year AS establishedYear, "
				+" vendors.vendor_capacity AS vendorCapacity, "
				+" vendors.cost_for_two_people AS costForTwoPeople, "
				+" vendors.currency AS currency, "
				+" IFNULL(vendors.location,'') AS location, "
				+" IFNULL(vendors.best_selling_items,'') AS bestSellingItems, "
				+" vendors.uuid AS vendorUUID "
				
			+" FROM "
			     + "vendors vendors where vendors.status = '1'  ORDER BY DATE_FORMAT(vendors.created_date, '%d') DESC, DATE_FORMAT(vendors.created_date, '%T') DESC ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorDTO.class));
		if(offset != null & limit != null) {
			queryObj.setFirstResult(offset);
			queryObj.setMaxResults(limit);
		}
		vendorDTOList = queryObj.list();
		
		return vendorDTOList;
	}

	@Override
	public List<ServicesDTO> getVendorServices() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<ServicesDTO> servicesDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" services.id AS serviceId, "
				+" services.service_name AS serviceName, "
				+" services.service_image AS serviceImage, "
				+" services.uuid AS serviceUUID "
				
			+" FROM "
			     + "wayuparty_services services where services.status = '1' ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setResultTransformer(Transformers.aliasToBean(ServicesDTO.class));
		servicesDTOList = queryObj.list();
		
		return servicesDTOList;
	}

	@Override
	public Long getServiceIdByUUID(String serviceUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		String locationQuery =  "SELECT "
				+" services.id "           
				+" FROM wayuparty_services services "
				+" WHERE services.uuid = :serviceUUID ";
		
		Query queryObj = currentSession.createSQLQuery(locationQuery);
		queryObj.setString("serviceUUID", serviceUUID);
		BigInteger vendorId = (BigInteger) queryObj.uniqueResult();
		return vendorId.longValue();
	}

	@Override
	public Long getServiceCategoryIdByUUID(String categoryUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		String locationQuery =  "SELECT "
				+" category.id "           
				+" FROM wayuparty_serivce_category category "
				+" WHERE category.uuid = :categoryUUID ";
		
		Query queryObj = currentSession.createSQLQuery(locationQuery);
		queryObj.setString("categoryUUID", categoryUUID);
		BigInteger vendorId = (BigInteger) queryObj.uniqueResult();
		return vendorId.longValue();
	}

	@Override
	public String getCategoryNameByCategoryId(Long categoryId) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		String locationQuery =  "SELECT "
				+" category.category_name "           
				+" FROM wayuparty_serivce_category category "
				+" WHERE category.id = :categoryId ";
		
		Query queryObj = currentSession.createSQLQuery(locationQuery);
		queryObj.setLong("categoryId", categoryId);
		String categoryName = (String) queryObj.uniqueResult();
		return categoryName;
	}

}
