package com.acculytixs.wayuparty.dao.impl;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acculytixs.wayuparty.dao.WayupartyLoginDao;
import com.acculytixs.wayuparty.dto.user.UserDTO;
import com.acculytixs.wayuparty.dto.user.UserSignUpDTO;

@Repository
@Transactional
public class WayupartyLoginDaoImpl implements WayupartyLoginDao{

	@Autowired
	private EntityManager entityManager;

	@Override
	public UserDTO getUserPassWordByUsername(String userName) {
		Session currentSession = entityManager.unwrap(Session.class);
		UserDTO userDTO = null;
		Query userPasswordQuery = null;
		 String passwordQuery =  "SELECT "
				 +" U.id AS userId, "
				 +" U.password AS password, "
				 +" U.uuid AS userUUID, "
				 + "(SELECT roles.role_name FROM wayuparty_roles roles WHERE roles.id = U.role_id) AS userRole "
				 +" FROM user U "
				 +" WHERE (U.user_name = :userName OR U.mobile_number = :userName)";

				 userPasswordQuery = currentSession.createSQLQuery(passwordQuery).setParameter("userName", userName); 
				 userPasswordQuery.setResultTransformer(Transformers.aliasToBean(UserDTO.class));
				 if(userPasswordQuery.list() != null && !userPasswordQuery.list().isEmpty()) {
					 userDTO = (UserDTO) userPasswordQuery.list().get(0);
				 }
				 
				 
		return userDTO;
	}

	
	@Override
	public UserDTO getLoggedInUserDetailsByUserName(String userName) {
		Session currentSession = entityManager.unwrap(Session.class);
		UserDTO userDTO = null;
		Query userPasswordQuery = null;
		 String passwordQuery =  "SELECT "
				 +" U.id AS userId, "
				 +" IFNULL((SELECT v.vendor_name FROM vendors v where v.id = U.vendor_id),'') AS vendorName, "
				 +" IFNULL((SELECT v.uuid FROM vendors v where v.id = U.vendor_id),'') AS vendorUUID, "
				 + "CONCAT (U.first_name,' ',IFNULL(U.last_name,'')) AS loginUserName,"
				 +" U.uuid AS userUUID, "
				 +" U.user_image AS userImage, "
				 +" U.user_status AS userStatus, "
				 + "(SELECT roles.role_name FROM wayuparty_roles roles WHERE roles.id = U.role_id) AS userRole, "
				 + "(SELECT roles.display_name FROM wayuparty_roles roles WHERE roles.id = U.role_id) AS userRoleDisplayName "
				 +" FROM user U "
				 +" WHERE (U.user_name = :userName OR U.mobile_number = :userName)";

				 userPasswordQuery = currentSession.createSQLQuery(passwordQuery).setParameter("userName", userName); 
				 userPasswordQuery.setResultTransformer(Transformers.aliasToBean(UserDTO.class));
				 if(userPasswordQuery.list() != null && !userPasswordQuery.list().isEmpty()) {
					 userDTO = (UserDTO) userPasswordQuery.list().get(0);
				 }
				 
				 
		return userDTO;
	}


	@Override
	public UserSignUpDTO isValidUserName(String userName) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		UserSignUpDTO userSignUpDTO = new UserSignUpDTO();
		Query userPasswordQuery = null;
		 String passwordQuery =  "SELECT "
				 +" U.id AS userId "
				 +" FROM user U "
				 +" WHERE U.user_name = :userName";

				 userPasswordQuery = currentSession.createSQLQuery(passwordQuery).setParameter("userName", userName.toLowerCase().trim()); 
				 if(userPasswordQuery.list() != null && !userPasswordQuery.list().isEmpty()) {
					 userSignUpDTO.setIsValid("false");
				 }else {
					 userSignUpDTO.setIsValid("true");
				 }
				 
				 
		return userSignUpDTO;
	
		
	}


	@Override
	public UserSignUpDTO isValidUserMobile(String mobileNumber) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		UserSignUpDTO userSignUpDTO = new UserSignUpDTO();
		Query userPasswordQuery = null;
		 String passwordQuery =  "SELECT "
				 +" U.id AS userId "
				 +" FROM user U "
				 +" WHERE U.mobile_number = :mobileNumber";

				 userPasswordQuery = currentSession.createSQLQuery(passwordQuery).setParameter("mobileNumber", mobileNumber); 
				 if(userPasswordQuery.list() != null && !userPasswordQuery.list().isEmpty()) {
					 userSignUpDTO.setIsValid("false");
				 }else {
					 userSignUpDTO.setIsValid("true");
				 }
				 
				 
		return userSignUpDTO;
	
		
	}
}
