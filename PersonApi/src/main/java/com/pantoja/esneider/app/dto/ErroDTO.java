package com.pantoja.esneider.app.dto;

public class ErroDTO {
	
	private int item;
	private String message;
	
	public ErroDTO() {
	}

	public ErroDTO(int item, String message) {
		this.item = item;
		this.message = message;
	}
	
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
	
}
