package com.acculytixs.wayuparty.rest.vendor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acculytixs.wayuparty.annotations.Validator;
import com.acculytixs.wayuparty.dto.user.PlaceOrderDTO;
import com.acculytixs.wayuparty.enums.Result;
import com.acculytixs.wayuparty.services.user.UserCartService;
import com.acculytixs.wayuparty.util.Constants;
import com.acculytixs.wayuparty.util.Response;

@RestController
public class PlaceOrdersRestController {

	@Autowired
	UserCartService userCartService;
	
	
	@RequestMapping(value = { "/placeOrder", "/rest/placeOrder" }, method = RequestMethod.POST)
	public Response<String> saveToCart(PlaceOrderDTO placeOrderDTO, Authentication authentication,
			HttpServletRequest request, HttpServletResponse httpResponse) throws Exception {
		
		Response<String> response = new Response<>();
		try {
			
			if (Validator.validate(placeOrderDTO)) {
				String result = userCartService.placeOrder(placeOrderDTO);
				if(result.equalsIgnoreCase(Constants.QUERY_EXECUTION_STATUS_SUCCESS)) {
					response.setResponse(Result.SUCCESS.name());
				}else {
					response.setResponse(Result.AWKWARD.name());
				}
				
			} else {
				response.setResponse(Result.INVALID_DATA.name());
				response.setResponseMessage(Result.INVALID_DATA.getValue());
			}
		
		} catch (Exception e) {
			response.setResponse(Result.AWKWARD.name());
			response.setResponseMessage(Result.AWKWARD.getValue());
			e.printStackTrace();
		}

		return response;
	}
	
	
}
