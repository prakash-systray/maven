package com.acculytixs.wayuparty.security;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

 
@Configuration
public class WayupartyPasswordEncoder implements PasswordEncoder {

	    public  String hashText(String password) throws Exception {
	        final MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
	        sha512.update(password.getBytes());
	        return convertByteToHex(sha512.digest());
		 }
		 
		 public   String convertByteToHex(byte data[]) {
		     StringBuffer hexData = new StringBuffer();
		     for (int byteIndex = 0; byteIndex < data.length; byteIndex++) {
		
		            hexData.append(Integer.toString((data[byteIndex] & 0xff) + 0x100,16).substring(1));
		     }
		     return hexData.toString();
		}
		 @Override
		public  String encode(CharSequence rawPassword) {
			String encodedString = "";
			try {
				encodedString = encodedHashText(hashText(rawPassword.toString()), 32);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return encodedString;
		}
		@Override
		public  boolean matches(CharSequence rawPassword, String storedPassword) {
			String inputPassword = "";
			try {
				inputPassword = hashText(rawPassword.toString());
				storedPassword = decodedHashText(storedPassword);
				if((inputPassword != null && !"".equals(inputPassword.trim())) && storedPassword.equals(inputPassword)){
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		
		
		public  String encodedHashText(String hashText, int period) {
		  Pattern p = Pattern.compile("(.{" + period + "})", Pattern.DOTALL);
		  Matcher m = p.matcher(hashText);
		  String randomString = RandomStringUtils.randomAlphanumeric(2);
		  return m.replaceAll("$1" + randomString.toLowerCase());
		}
		
		public   String decodedHashText(String storedPassword) {
			int length = storedPassword.length();
			StringBuilder hashTextBuilder = new StringBuilder();
			for(int i=0,j=32;i<=length;){
				hashTextBuilder.append(storedPassword.substring(i,j));
				i = i+34;
				if(length<j+34){
					j = length;
				}
				else{
					j= j+34;
				}
				
			}
			return hashTextBuilder.toString();
		}
}