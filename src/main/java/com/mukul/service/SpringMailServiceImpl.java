package com.mukul.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service("purchaseService")
public class SpringMailServiceImpl implements ISpringMailService{
	@Autowired
	private JavaMailSender sender;
	@Value("${spring.mail.username}")
	private String fromEmail;
	@Override
	public String purchaseOrder(String[] items, Double[] prices, String[] toEmails) throws Exception {
		Double billAmt = 0.0;
		for(Double price: prices) {
			billAmt+= price;
		}
		String resultMsg = Arrays.toString(items)+" with prices"
						   + Arrays.toString(prices)+" are Purchased with BillAmount"+billAmt;
		String msg=sendMail(resultMsg,toEmails);
		return msg;
	}
	private String sendMail(String msg, String[] toEMails)throws Exception{
		//Create Message Mime
		MimeMessage message = sender.createMimeMessage();
		//Create Mime for Set the data
		MimeMessageHelper helper =new MimeMessageHelper(message,true);	
		helper.setFrom(fromEmail);
		//How many many member receive at one mail
		helper.setCc(toEMails);
		helper.setSubject("open it to know it");
		helper.setSentDate(new Date());
		helper.setText(msg);
		helper.addAttachment("Springmail.jpg", new ClassPathResource("Springmail.jpg"));
		//call send message to gmail
		sender.send(message);
		return "mail sent successfully";
	}
}
