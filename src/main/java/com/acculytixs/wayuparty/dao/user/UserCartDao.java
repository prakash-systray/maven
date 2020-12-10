package com.acculytixs.wayuparty.dao.user;

import java.util.List;

import com.acculytixs.wayuparty.dto.user.CartDTO;
import com.acculytixs.wayuparty.dto.user.UserCartDTO;
import com.acculytixs.wayuparty.entity.services.PlaceOrder;
import com.acculytixs.wayuparty.entity.user.UserCart;

public interface UserCartDao {

	void addToCart(UserCart userCart) throws Exception;
	
	boolean getCartServiceExists(UserCartDTO userCartDTO) throws Exception;
	
	List<CartDTO> getUserCartList(Long userId) throws Exception;
	
	Long getUserCartCount(Long userId) throws Exception;
	
	Long getVendorIdForExistingCartByUserId(Long userId) throws Exception;
	
	UserCart getUserCartbyUUID(String cartUUID) throws Exception;
	
	void deleteCartItem(UserCart userCart) throws Exception;
	
	UserCart getUserCartOrderDetailsByUUID(String cartUUID) throws Exception;
	
	void placeOrder(PlaceOrder placeOrder) throws Exception;
}
