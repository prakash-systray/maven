package com.acculytixs.wayuparty.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class SessionManager {

	public static void setSessionAttribute(HttpServletRequest request, String attribute, Object obj) {
		request.getSession().setAttribute("Wayuparty_"+attribute, obj);
	}
	
	public static Object getSessionAttribute(HttpServletRequest request, String attribute) {
		return request.getSession().getAttribute("Wayuparty_"+attribute);
	}

	public static void removeSessionAttribute(HttpServletRequest request, String attribute) {
		request.getSession().removeAttribute("Wayuparty_"+attribute);
	}
}
