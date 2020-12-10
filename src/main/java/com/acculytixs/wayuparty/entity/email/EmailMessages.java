package com.acculytixs.wayuparty.entity.email;

import java.io.Serializable;

public class EmailMessages implements Serializable {
	
	private static final long serialVersionUID = -519129838464344312L;
	
	private String loginUserName;
	
	private String toEmail;
	
	private String fromEmail;
	
	private String fromPassword;
	
	private String fromName;
	
	private String subject;
	
	private String messageBody;
	
    private String userName;
    
    private String password;
    
    private String smtphost;
    
    private Integer port;
    
    private String verificationLink;
    

    

	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	
	public String getFromPassword() {
		return fromPassword;
	}

	public void setFromPassword(String fromPassword) {
		this.fromPassword = fromPassword;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerificationLink() {
		return verificationLink;
	}

	public void setVerificationLink(String verificationLink) {
		this.verificationLink = verificationLink;
	}

	public String getSmtphost() {
		return smtphost;
	}

	public void setSmtphost(String smtphost) {
		this.smtphost = smtphost;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	
}
