package com.acculytixs.wayuparty.annotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.acculytixs.wayuparty.util.CommonUtil;

public class Validator {

	public static boolean validate(Object object) {
		try {
			Field[] fields = object.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(CleanHtml.class)){
					field.setAccessible(true);
					if(field.get(object) != null && !field.get(object).toString().isEmpty() && !"undefined".equalsIgnoreCase(field.get(object).toString())){
					  if(!field.getAnnotation(CleanHtml.class).escape()) {
						 String value = field.get(object).toString();
						 field.set(object,CommonUtil.cleanContent(value));
					  }
					}
				}
				if (field.isAnnotationPresent(Required.class)) {
					try {
						field.setAccessible(true);
						boolean flag = verifyParentInfo(object, fields, field);
						boolean flag1 = verifyEitherInfo(object, fields, field);
						if (flag && !flag1 && (field.get(object) == null || field.get(object).toString().isEmpty() || "undefined".equalsIgnoreCase(field.get(object).toString())) && !field.getAnnotation(Required.class).timesheetHours()) {
							object.getClass().getMethod("setErrorKey", String.class).invoke(object, "error.required");
							object.getClass().getMethod("setParams", new Class[] { String[].class }).invoke(object,
									new Object[] { new String[] { field.getAnnotation(Required.class).desc() } });
							return false;
						} else if (field.getAnnotation(Required.class).isPositiveNumber()) {
							String value = field.get(object).toString();
							if(value !=null && !value.isEmpty() && Integer.parseInt(value)>0) {
								return true;
							}
							object.getClass().getMethod("setErrorKey", String.class).invoke(object, "error.required");
							object.getClass().getMethod("setParams", new Class[] { String[].class }).invoke(object,
									new Object[] { new String[] { field.getAnnotation(Required.class).desc() } });
							return false;
						} else if (field.getAnnotation(Required.class).alphaNumericOnly()) {
							String value = field.get(object).toString();
							String pattern = "^[a-zA-Z0-9]*$";
							if (value.matches(pattern)) {
								return true;
							}
							object.getClass().getMethod("setErrorKey", String.class).invoke(object, "error.required");
							object.getClass().getMethod("setParams", new Class[] { String[].class }).invoke(object,
									new Object[] { new String[] { field.getAnnotation(Required.class).desc() } });
							return false;
						} 
						
					} catch (IllegalArgumentException | IllegalAccessException ex) {
						return false;
					} catch (NoSuchMethodException | SecurityException | InvocationTargetException ex) {
						return false;
					}
				}
				if (field.isAnnotationPresent(Email.class)) {
					try {
						field.setAccessible(true);
						if (field.get(object) != null && !field.get(object).toString().trim().isEmpty()) {
							try {
								new InternetAddress(field.get(object).toString().trim()).validate();
							} catch (IllegalArgumentException | IllegalAccessException | AddressException ex) {
								object.getClass().getMethod("setErrorKey", String.class).invoke(object, "error.email");
								object.getClass().getMethod("setParams", new Class[] { String[].class }).invoke(object,
										new Object[] { new String[] {} });
								return false;
							}
							// return true;
						}
					} catch (IllegalArgumentException | IllegalAccessException ex) {
						return false;
					} catch (NoSuchMethodException | SecurityException | InvocationTargetException ex) {
						return false;
					}
				}
				if (field.isAnnotationPresent(Type.class)) {
					try {
						field.setAccessible(true);
						Object obj = field.get(object);
						if (!field.isAnnotationPresent(Required.class)) {
							if (obj == null) {
								continue;
							}
						}

						Type type = field.getAnnotation(Type.class);
						if (type.minLength() > 0 && obj.toString().length() < type.minLength()) {
							object.getClass().getMethod("setErrorKey", String.class).invoke(object, "error.type2");
							object.getClass().getMethod("setParams", new Class[] { String[].class }).invoke(object,
									new Object[] { new String[] { type.desc(), String.valueOf(type.minLength())
											+ (type.name().equals("String") ? " character " : "digit") } });

							return false;
						}
						if (type.maxLength() > 0 && obj.toString().length() > type.maxLength()) {
							object.getClass().getMethod("setErrorKey", String.class).invoke(object, "error.type3");
							object.getClass().getMethod("setParams", new Class[] { String[].class }).invoke(object,
									new Object[] { new String[] { type.desc(), String.valueOf(type.maxLength())
											+ (type.name().equals("String") ? " character " : "digit") } });

							return false;
						}
					} catch (IllegalArgumentException | IllegalAccessException ex) {
						return false;
					} catch (InvocationTargetException | NoSuchMethodException | SecurityException ex) {
						return false;
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private static boolean verifyEitherInfo(Object object, Field[] fields, Field field) throws Exception
	{
		boolean flag = false;
		field.setAccessible(true);
		Required r = field.getAnnotation(Required.class);
		if (r.either() != null && r.either().length > 0) {
			List<Object> value = new ArrayList<>()	;
			for (Field field1 : fields) {
				field1.setAccessible(true);
				for(String e : r.either()) {
					if(e.equals(field1.getName())) {
						value.add(field1.get(object));
					}
				}
			}
			if (value.isEmpty()) {
				return false;
			}
			for (Object v1 : value) {
				if (v1 != null && !v1.toString().isEmpty()) {
					flag = true;
					break;
				}
			}
		} else {
			flag = false;
		}
		return flag;
	}

	private static boolean verifyParentInfo(Object object, Field[] fields, Field field) throws Exception {
		boolean flag = false;
		field.setAccessible(true);
		Required r = field.getAnnotation(Required.class);
		if (r.dependsOn() != null && !r.dependsOn().isEmpty()) {
			Object value = null;
			for (Field field1 : fields) {
				field1.setAccessible(true);
				if (r.dependsOn().equals(field1.getName())) {
					value = field1.get(object);
					break;
				}
			}
			if (value == null) {
				return false;
			}
			String[] d = r.parentValue();
			for (String d1 : d) {
				if ((r.parentValueAny() && !value.toString().isEmpty())
						|| (!d1.isEmpty() && value.toString().equals(d1))) {
					flag = true;
					break;
				}
			}
		} else {
			flag = true;
		}
		return flag;
	}
}