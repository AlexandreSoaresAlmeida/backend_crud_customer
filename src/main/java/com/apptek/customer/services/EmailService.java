package com.apptek.customer.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.apptek.customer.model.Cliente;

@Service
public interface EmailService {

	void sendEmail(SimpleMailMessage msg);

	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);

	void sendChangePasswordEmail(Cliente cliente, String newPass);
}