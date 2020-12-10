package com.acculytixs.wayuparty.dao.impl.user;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acculytixs.wayuparty.dao.user.UserCartDao;
import com.acculytixs.wayuparty.dto.services.VendorServicesDTO;
import com.acculytixs.wayuparty.dto.user.CartDTO;
import com.acculytixs.wayuparty.dto.user.UserCartDTO;
import com.acculytixs.wayuparty.entity.services.PlaceOrder;
import com.acculytixs.wayuparty.entity.user.UserCart;

@Repository
@Transactional
public class UserCartDaoImpl implements UserCartDao{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public void addToCart(UserCart userCart) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(userCart);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean getCartServiceExists(UserCartDTO userCartDTO) throws Exception {
		
		boolean flag = false;
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		Query queryObj = null;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date orderDate = new SimpleDateFormat("dd/MM/yyyy").parse(userCartDTO.getServiceOrderDate());  
		String serviceOrderDate = formatter.format(orderDate);
		
		query = "SELECT COUNT(*) " 
				+" FROM "
			    +" user_cart cart where cart.service_ordered_date = STR_TO_DATE('"+serviceOrderDate+"', '%Y-%m-%d') "  
			    +" and cart.service_time_slot = '"+userCartDTO.getTimeslot()+"' and  cart.user_id = :userId and "
			    +" cart.vendor_id = :vendorId and cart.vendor_master_service_id = :serviceId and cart.status = '1'";
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("userId", userCartDTO.getUserId());
		queryObj.setLong("vendorId", userCartDTO.getVendorId());
		queryObj.setLong("serviceId", userCartDTO.getServiceId());
		BigInteger cartCount = (BigInteger) queryObj.uniqueResult();
		if(cartCount.intValue() > 0) {
			flag = true;
		}
		
		return flag;
	}

	@Override
	public List<CartDTO> getUserCartList(Long userId) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		List<CartDTO> cartDTOList = new ArrayList<CartDTO>();
		List<CartDTO> cartList = new CopyOnWriteArrayList<CartDTO>();
		Query queryObj = null;
		
		query = "SELECT " 
				+" cart.vendor_master_service_id AS masterServiceId, "
				+" cart.order_amount AS orderAmount, "
				+" cart.quantity AS quantity, "
				+" cart.total_amount AS totalAmount, "
				+" cart.service_time_slot AS timeSlot, "
				+" cart.currency AS currency, "
				+" IFNULL(DATE_FORMAT(cart.service_ordered_date, '%b %e %Y'),'') AS serviceOrderDate," 
				+" cart.uuid AS cartUUID "
				
			+" FROM "
			     + "user_cart cart where cart.status = '1' and "
			     + "cart.user_id = :userId and DATEDIFF(cart.service_ordered_date,CURDATE()) >= 0 "
			     + "ORDER BY DATE_FORMAT(cart.created_date, '%d') DESC, DATE_FORMAT(cart.created_date, '%T') DESC ";
	
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("userId", userId);
		queryObj.setResultTransformer(Transformers.aliasToBean(CartDTO.class));
		cartDTOList = queryObj.list();
		
		if(cartDTOList != null && !cartDTOList.isEmpty()) {
			for(CartDTO cartDTO : cartDTOList) {
				
				query = "SELECT " 
						+" IFNULL(vendorService.service_image,'/resources/img/glass.jpg') AS serviceImage, "
						+" IFNULL((SELECT subcategory.sub_category_name FROM wayuparty_serivce_sub_category subcategory where subcategory.id = vendorService.sub_category_id), vendorService.service_name) AS subCategory "
						
					+" FROM "
					     + "vendor_master_service vendorService where  vendorService.id = :serviceId ";
			
				
				queryObj = currentSession.createSQLQuery(query);
				queryObj.setLong("serviceId", cartDTO.getMasterServiceId().longValue());
				queryObj.setResultTransformer(Transformers.aliasToBean(VendorServicesDTO.class));
				List<VendorServicesDTO> vendorServicesList = queryObj.list();
				VendorServicesDTO vendorServicesDTO = vendorServicesList.get(0);
				cartDTO.setServiceName(vendorServicesDTO.getSubCategory());
				cartDTO.setServiceImage(vendorServicesDTO.getServiceImage());
				cartList.add(cartDTO);
			}
		}
		
		return cartList;
	}

	@Override
	public Long getUserCartCount(Long userId) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		Query queryObj = null;

		query = "SELECT COUNT(*) " 
			+" FROM "
			     + "user_cart cart where cart.status = '1' and "
			     + "cart.user_id = :userId and DATEDIFF(cart.service_ordered_date,CURDATE()) >= 0 ";
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("userId", userId);
		BigInteger cartCount = (BigInteger) queryObj.uniqueResult();
		
		return cartCount.longValue();
	}

	@Override
	public Long getVendorIdForExistingCartByUserId(Long userId) throws Exception {
		
		Session currentSession = entityManager.unwrap(Session.class);
		String query = null;
		Query queryObj = null;

		query = "SELECT cart.vendor_id " 
			+" FROM "
			     + "user_cart cart where cart.status = '1' and "
			     + "cart.user_id = :userId group by cart.user_id ";
		
		queryObj = currentSession.createSQLQuery(query);
		queryObj.setLong("userId", userId);
		BigInteger vendorId = (BigInteger) queryObj.uniqueResult();
		if(vendorId != null) {
			return vendorId.longValue();
		}else {
			return null;
		}
		
	}

	@Override
	public UserCart getUserCartbyUUID(String cartUUID) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from UserCart cart  where cart.uuid = :cartUUID ";
		List<UserCart> cartList = currentSession.createQuery(query).setString("cartUUID", cartUUID).list();
		UserCart userCart = null;
		if (cartList != null && cartList.size() > 0) {
			userCart = (UserCart) cartList.iterator().next();
		}
		return userCart;
	}

	@Override
	public void deleteCartItem(UserCart userCart) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.delete(userCart);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public UserCart getUserCartOrderDetailsByUUID(String cartUUID) throws Exception {
		Session currentSession = entityManager.unwrap(Session.class);
		String query = "from UserCart cart left join fetch cart.vendorId left join fetch cart.vendorMasterServiceId vendorMaster "
				+ " left join fetch vendorMaster.serviceId  where cart.uuid = :cartUUID ";
		List<UserCart> cartList = currentSession.createQuery(query).setString("cartUUID", cartUUID).list();
		UserCart userCart = null;
		if (cartList != null && cartList.size() > 0) {
			userCart = (UserCart) cartList.iterator().next();
		}
		return userCart;
	}

	@Override
	public void placeOrder(PlaceOrder placeOrder) throws Exception {
		try {
			Session currentSession = entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(placeOrder);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
