package com.acculytixs.wayuparty.services.impl.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acculytixs.wayuparty.dao.user.UserCartDao;
import com.acculytixs.wayuparty.dto.user.CartDTO;
import com.acculytixs.wayuparty.dto.user.PlaceOrderDTO;
import com.acculytixs.wayuparty.dto.user.UserCartDTO;
import com.acculytixs.wayuparty.entity.services.PlaceOrder;
import com.acculytixs.wayuparty.entity.services.VendorMasterService;
import com.acculytixs.wayuparty.entity.user.User;
import com.acculytixs.wayuparty.entity.user.UserCart;
import com.acculytixs.wayuparty.entity.vendor.Vendors;
import com.acculytixs.wayuparty.services.user.UserCartService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.RandomCodeHelper;

@Service
public class UserCartServiceImpl implements UserCartService{
	
	@Autowired
	UserCartDao userCartDao;

	@Override
	public void addToCart(UserCartDTO userCartDTO) throws Exception {
		
		UserCart userCart = new UserCart();
		
		User user = new User();
		user.setId(userCartDTO.getUserId());
		userCart.setUserId(user);
		
		Vendors vendors = new Vendors();
		vendors.setId(userCartDTO.getVendorId());
		userCart.setVendorId(vendors);
		
		VendorMasterService masterService = new VendorMasterService();
		masterService.setId(userCartDTO.getServiceId());
		userCart.setVendorMasterServiceId(masterService);
		
		userCart.setQuantity(userCartDTO.getQuantity());
		userCart.setOrderAmount(userCartDTO.getOrderAmount());
		userCart.setTotalAmount(userCartDTO.getTotalAmount());
		userCart.setCurrency(userCartDTO.getCurrency());
		userCart.setServiceTimeSlot(userCartDTO.getTimeslot());
		
		if(StringUtils.isNotBlank(userCartDTO.getPackageMenuItems())) {
			userCart.setPackageMenuItems(userCartDTO.getPackageMenuItems());
		}
		
		Date serviceOrderDate = new SimpleDateFormat("dd/MM/yyyy").parse(userCartDTO.getServiceOrderDate());  
		userCart.setServiceOrderedDate(serviceOrderDate);
		userCart.setCreatedDate(new Date());
		userCart.setUuid(RandomCodeHelper.generateRandomUUID());
		userCart.setStatus(1);
		userCartDao.addToCart(userCart);
		
	}

	@Override
	public boolean getCartServiceExists(UserCartDTO userCartDTO) throws Exception {
		return userCartDao.getCartServiceExists(userCartDTO);
	}

	@Override
	public List<CartDTO> getUserCartList(Long userId) throws Exception {
		return userCartDao.getUserCartList(userId);
	}

	@Override
	public Long getUserCartCount(Long userId) throws Exception {
		return userCartDao.getUserCartCount(userId);
	}

	@Override
	public Long getVendorIdForExistingCartByUserId(Long userId) throws Exception {
		return userCartDao.getVendorIdForExistingCartByUserId(userId);
	}

	@Override
	public UserCart getUserCartbyUUID(String cartUUID) throws Exception {
		return userCartDao.getUserCartbyUUID(cartUUID);
	}

	@Override
	public void deleteCartItem(UserCart userCart) throws Exception {
		userCartDao.deleteCartItem(userCart);		
	}
	
	@Override
	public UserCart getUserCartOrderDetailsByUUID(String cartUUID) throws Exception {
		return userCartDao.getUserCartOrderDetailsByUUID(cartUUID);
	}

	@Override
	public String placeOrder(PlaceOrderDTO placeOrderDTO) throws Exception {
		
		String queryExecutionStatus = null;
		
		try {
		String[] cartItems = placeOrderDTO.getCartItems().split(",");
		for(int i=0; i<cartItems.length; i++) {
			
			UserCart userCart = userCartDao.getUserCartOrderDetailsByUUID(cartItems[i]);
			if(userCart != null) {
				
				PlaceOrder placeOrder = new PlaceOrder();
				placeOrder.setUserUUID(placeOrderDTO.getUserUUID());
				placeOrder.setVendorUUID(userCart.getVendorMasterServiceId().getVendorId().getUuid());
				placeOrder.setMasterServiceUUID(userCart.getVendorMasterServiceId().getUuid());
				placeOrder.setServiceUUID(userCart.getVendorMasterServiceId().getServiceId().getUuid());
				placeOrder.setQuantity(userCart.getQuantity());
				placeOrder.setOrderAmount(userCart.getOrderAmount());
				placeOrder.setTotalAmount(userCart.getTotalAmount());
				placeOrder.setCurrency(userCart.getCurrency());
				placeOrder.setServiceTimeSlot(userCart.getServiceTimeSlot());
				
				if(StringUtils.isNotBlank(userCart.getPackageMenuItems())) {
					placeOrder.setPackageMenuItems(userCart.getPackageMenuItems());
				}
				
				placeOrder.setServiceOrderedDate(userCart.getServiceOrderedDate());
				placeOrder.setCreatedDate(new Date());
				placeOrder.setOrderStatus(Constants.ORDER_PENDING_FOR_APPROVAL);
				placeOrder.setUuid(RandomCodeHelper.generateRandomUUID());
				placeOrder.setStatus(1);
				userCartDao.placeOrder(placeOrder);
				userCartDao.deleteCartItem(userCart);
			}
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_SUCCESS;
		}
		}catch (Exception e) {
			queryExecutionStatus = Constants.QUERY_EXECUTION_STATUS_ERROR;
		}
		return queryExecutionStatus;
	}

	

}
