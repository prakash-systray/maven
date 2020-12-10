package com.acculytixs.wayuparty.dao.impl.user;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acculytixs.wayuparty.dao.user.UserDao;
import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.dto.user.UserLoginDTO;
import com.acculytixs.wayuparty.dto.vendor.VendorInfoDTO;
import com.acculytixs.wayuparty.entity.user.User;
import com.acculytixs.wayuparty.entity.vendor.Vendors;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public boolean isUserExistedByEmailId(String emailId) throws Exception {
		boolean flag = false;
		Session currentSession = entityManager.unwrap(Session.class);
		try {
			  String query = "select id from User user where user.email = :emailId and user.status = '1' ";
			  Query queryObj =  currentSession.createQuery(query).setString("emailId", emailId);
			  Long userId = (Long) queryObj.uniqueResult();
			  if(userId != null) {
				  flag = true;
			  }
		}catch (Exception e) {
			 e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean isUserExistedByMobileNumber(String mobileNumber) throws Exception {
		boolean flag = false;
		Session currentSession = entityManager.unwrap(Session.class);
		try {
			  String query = "select id from User user where user.mobileNumber = :mobileNumber and user.status = '1' ";
			  Query queryObj =  currentSession.createQuery(query).setString("mobileNumber", mobileNumber);
			  Long userId = (Long) queryObj.uniqueResult();
			  if(userId != null) {
				  flag = true;
			  }
		}catch (Exception e) {
			 e.printStackTrace();
		}
		return flag;
	}

	@Override
	public UserDTO getUserDetailsByUUID(String userUUID) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		UserDTO userDTO = null;
		Query userPasswordQuery = null;
		 String passwordQuery =  "SELECT "
				 +" U.id AS userId, "
				 +" U.email AS userEmail, "
				 +" U.mobile_number AS userMobile, "
				 +" U.password AS password, "
				 +" U.first_name AS firstName, "
				 +" IFNULL(U.last_name,'') AS lastName, "
				 +" IFNULL(DATE_FORMAT(dob, '%d/%m/%Y'),'') AS dob, "
				 +" IFNULL(U.user_image,'') AS userImage "
				 +" FROM user U "
				 +" WHERE U.uuid = :userUUID";

				 userPasswordQuery = currentSession.createSQLQuery(passwordQuery).setParameter("userUUID", userUUID); 
				 userPasswordQuery.setResultTransformer(Transformers.aliasToBean(UserDTO.class));
				 if(userPasswordQuery.list() != null && !userPasswordQuery.list().isEmpty()) {
					 userDTO = (UserDTO) userPasswordQuery.list().get(0);
				 }
				 
				 
		return userDTO;
	}

	@Override
	public void saveUser(User user) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(user);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public VendorInfoDTO getVendorInfoDetails(String vendorUUID) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<VendorInfoDTO> vendorInfoDTOList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" IFNULL(vendors.vendor_name,'') AS vendorName, "
				+" IFNULL(vendors.vendor_code,'') AS vendorCode, "
				+" IFNULL(vendors.vendor_email,'') AS vendorEmail, "
				+" IFNULL(vendors.vendor_mobile,'') AS vendorMobile, "
				+" IFNULL(vendors.profile_image,'') AS vendorProfileImg, "
				+" vendors.established_year AS establishedYear, "
				+" vendors.vendor_capacity AS vendorCapacity, "
				+" vendors.cost_for_two_people AS costForTwoPeople, "
				+" IFNULL(vendors.vendor_description,'') AS vendorDescription, "
				+" IFNULL(vendors.best_selling_items,'') AS bestSellingItems, "
				+" IFNULL(vendors.location,'') AS location, "
				+" IFNULL(vendors.country,'') AS country, "
				+" IFNULL(vendors.state,'') AS state, "
				+" IFNULL(vendors.city,'') AS city, "
				+" IFNULL(vendors.pincode,'') AS pincode, "
				+" IFNULL(vendors.latitude,'') AS latitude, "
				+" IFNULL(vendors.longitude,'') AS longitude, "
				+" IFNULL(vendors.phone_number,'') AS phoneNumber, "
				+" IFNULL(vendors.address,'') AS vendorAddress, "
				+" IFNULL(vendors.working_hours,'') AS workingHours, "
				+" IFNULL(vendors.terms_conditions,'') AS termsAndConditions, "
				+" IFNULL(vendors.slider_images,'') AS sliders, "
				+" IFNULL(vendors.gallery_images,'') AS gallery, "
				+" IFNULL(vendors.menu_images,'') AS menus, "
				+" IFNULL(vendors.vendor_videos,'') AS videos, "
				+" IFNULL(vendors.vendor_categories,'') AS categories, "
				+" IFNULL(vendors.vendor_facilities,'') AS facilities, "
				+" IFNULL(vendors.vendor_music_genre,'') AS music, "
				+" IFNULL(vendors.vendor_cuisine,'') AS cuisine, "
				+" vendors.uuid AS vendorUUID "
				
			+" FROM "
			     + "vendors vendors where vendors.uuid = :vendorUUID ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setString("vendorUUID", vendorUUID);
		queryObj.setResultTransformer(Transformers.aliasToBean(VendorInfoDTO.class));
		vendorInfoDTOList = queryObj.list();
		

		if(vendorInfoDTOList != null && !vendorInfoDTOList.isEmpty()) {
			VendorInfoDTO vendorInfoDTO = vendorInfoDTOList.get(0);
			return vendorInfoDTO;
		}else {
			return null;
		}
	}

	@Override
	public List<String> getVendorCustomCategories(List<Long> categoriesIds) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		List<String> vendorCategoriesList = null;
		String query = null;
		Query queryObj = null;
		
		query = "SELECT " 
				+" categories.category_name AS categoryName "
				
			+" FROM "
			     + "wayuparty_categories categories where categories.status = '1'  and categories.id IN (:categoriesIds) ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setParameterList("categoriesIds", categoriesIds);
		vendorCategoriesList = queryObj.list();
		
		return vendorCategoriesList;
	}

	@Override
	public User getUserByUUID(String uuid) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from User user left join fetch user.vendorId left join fetch user.roleId where user.uuid = :uuid ";
		List<User> userList = currentSession.createQuery(query).setString("uuid", uuid).list();
		User user = null;
		if (userList != null && userList.size() > 0) {
			user = (User) userList.iterator().next();
		}
		return user;
	}

	@Override
	public UserLoginDTO getLoginUserDetails(Long userId) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		UserDTO userDTO = null;
		Query queryObj = null;
		 String query =  "SELECT "
				 +" U.id AS userId, "
				 +" U.email AS userEmail, "
				 +" U.mobile_number AS userMobile, "
				 + "CONCAT (U.first_name,' ',IFNULL(U.last_name,'')) AS loginUserName,"
				 +" IFNULL(U.user_image,'') AS userImage, "
				 +" U.uuid AS userUUID "
				 +" FROM user U "
				 +" WHERE U.id = :userId";

		    queryObj = currentSession.createSQLQuery(query);
			queryObj.setLong("userId", userId);
			queryObj.setResultTransformer(Transformers.aliasToBean(UserLoginDTO.class));
			List<UserLoginDTO> userList = queryObj.list();
			
			if(userList != null && !userList.isEmpty()) {
				UserLoginDTO userLoginDTO = userList.get(0);
				return userLoginDTO;
			}else {
				return null;
			}
	}

}
