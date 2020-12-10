package com.acculytixs.wayuparty.util;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.acculytixs.wayuparty.entity.email.EmailMessages;

public class EmailTemplateHTMLUtil {

	
	public static String convertBodyOfEmailTemplateToHTML(String url, EmailMessages emailMessages) {	
		 
		StringBuilder tempHTML = new StringBuilder();
		String newUrl = null;
		if(StringUtils.isNotEmpty(url)) {
			newUrl = StringUtils.removeEnd(url, url.substring(url.lastIndexOf("/")));
		}
		tempHTML.append("<table width='100%' cellpadding='0' cellspacing='0' style='background-color:#EEEEEE; font-family:verdana, arial;'>");
				tempHTML.append("<tr>");
						tempHTML.append("<td>");
								tempHTML.append("<table width='650' align='center' cellpadding='0' cellspacing='0' style='padding:5px 0px; background-color:#fff; border:1px solid #E0E0E0;'>");
										tempHTML.append("<tr>");
												tempHTML.append("<td>");
												  tempHTML.append("<table width='600' align='center'>");
												      tempHTML.append("<tr>");
												      
												      tempHTML.append("<td style='text-align: center; height:50px;'><img src='"+url+"/resources/img/logo.png' style='max-height:100%;'></td>");
												           
										             tempHTML.append("</tr>");
								                 tempHTML.append("</table>");
						                      tempHTML.append("</td>");
				                         tempHTML.append("</tr>");
		                          tempHTML.append("</table> ");
		
		tempHTML.append("<table width='650'  bgcolor='#f5f5f5' align='center' style='background-color:#f5f5f5; margin:0px auto; border:solid #E0E0E0; border-width:0 1px 1px 1px;'  cellpadding='0' cellspacing='0'>");
				tempHTML.append("<tr>");
						tempHTML.append("<td>");
					  	      tempHTML.append("<table width='650' style='padding:0 15px 15px; margin:20px auto 0; font-family:verdana, arial;'; align='center' cellpadding='0' cellspacing='0' >");
						           tempHTML.append("<tr>");
						                 tempHTML.append("<td>");
						                         tempHTML.append("<div style='font-size:13px; color:#333333; line-height: 20px; padding:0 15px;'>");
		                                         tempHTML.append("Dear "+emailMessages.getLoginUserName().trim()+",<br/>");
		                                         tempHTML.append("Thanks for registering with us. Please verify you account.<br/>");
		                                         tempHTML.append("User Name: "+emailMessages.getUserName().trim()+"<br/>");
		                                         tempHTML.append("Password: "+emailMessages.getPassword().trim()+"<br/><br/><br/>");
		                                         tempHTML.append("Verification Link: "+emailMessages.getVerificationLink().trim()+"<br/>");
		                                         tempHTML.append("</div>");
		                                         tempHTML.append("</br>");
		                                         
									                    tempHTML.append("<div style='font-size:13px; font-weight:600; color:#333333; line-height:20px; padding:15px 15px 15px 15px; clear:both;'>");
														tempHTML.append("Thanks,<br/>");
														tempHTML.append(emailMessages.getFromName().trim());
													    tempHTML.append(" </div>");		
					                            								
					                     tempHTML.append("</td>");
					          tempHTML.append("</tr>");
					      tempHTML.append("</table>");
					  tempHTML.append(" </td>");
			    tempHTML.append("</tr>");
		tempHTML.append("</table>");
		
			
			tempHTML.append("<table width='650' bgcolor='#ededed' align='center' cellpadding='0' cellspacing='0' style='background-color:#ededed; padding:0px;'>");
			  tempHTML.append("<tr>");
					  tempHTML.append("<td>");
					       tempHTML.append("<table bgcolor='#ededed'  width='650' align='center' style='background-color:#ededed; padding:10px 15px; border:solid #E0E0E0; border-width:0 1px 0px 1px;'>");
					    	   tempHTML.append("<tr>");
				                 tempHTML.append("<td>");
				                   tempHTML.append("<p style='color:#214b90; text-transform: uppercase; padding:5px 0px; margin:0px 0px; font-size:13px; font-weight:600; text-align:center'>get started!</p>");
				                   tempHTML.append("<p style='padding:0px 0px 2px 0px; margin:0px 0px; font-weight:400; font-size:11px; text-align:center'>For more information about WAYUPARTY, go to <a target='blank' href='http://aws.wayuparty.com/' style='text-decoration:none; color:#0D47A1; display:inline-block; padding:0; border-radius:0; margin:0; font-size:14px'>wayuparty.com</a></p>");
				                 tempHTML.append("</td>");
				            tempHTML.append("</tr>");
				         
				            tempHTML.append("<tr>");    
				            tempHTML.append("</tr>");
					       tempHTML.append("</table>");
					 tempHTML.append("</td>");
			    tempHTML.append("</tr>");
     tempHTML.append("</table>"); 
     
       
	tempHTML.append("</td>");
tempHTML.append("</tr>");
tempHTML.append("</table>");


		 return tempHTML.toString();
	}
	
	protected static boolean isNullOrEmpty(String str) {
		return (str == null || "".equals(str.trim()));
	}
}


