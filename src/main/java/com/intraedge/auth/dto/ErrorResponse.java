package com.intraedge.auth.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "error")
@XmlType(propOrder = { "code", "message", "developerMessage" })
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorResponse {
	
	private String message;
	private int code;
	private String developerMessage;
	
	public ErrorResponse(){
		
	}
	public ErrorResponse(String message, int code, String developerMessage) {
		super();
		this.message = message;
		this.code = code;
		this.developerMessage = developerMessage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDeveloperMessage() {
		return developerMessage;
	}
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
	
}
