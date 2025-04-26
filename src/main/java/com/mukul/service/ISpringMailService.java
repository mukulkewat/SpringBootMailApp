package com.mukul.service;

public interface ISpringMailService {
	public String purchaseOrder(String []items, Double[] price, String[] toEmails )throws Exception;

}
