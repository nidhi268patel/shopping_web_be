package com.example.shopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
@EnableAsync
public class EmailServiceImpl {

	@Autowired
	private JavaMailSender mailSender;

	@Async
	public void sendStatusMail(String to, String name, Long orderId, String status, Long orderItemId) {

		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);

			helper.setTo(to);

			if ("SHIPPED".equals(status)) {
				helper.setSubject("Item Shipped 🚚");
				helper.setText("<h3>Hi " + name + "</h3>" + "<p>Your item from order #<b>" + orderId
						+ "</b> has been <b>shipped</b> successfully.</p>" + "<p><b>Item ID:</b> " + orderItemId
						+ "</p>" + "<p>🚚 It is on the way to your delivery address.</p>", true);
			}

			if ("DELIVERED".equals(status)) {
				helper.setSubject("Item Delivered ✅");
				helper.setText("<h3>Hi " + name + "</h3>" + "<p>Your item from order <b>" + orderId
						+ "</b> has been <b>delivered successfully</b>.</p>" + "<p><b>Item ID:</b> " + orderItemId
						+ "</p>" + "<p>🎉 We hope you enjoy your purchase. Thank you for shopping with us!</p>", true);
			}
			if ("PACKED".equals(status)) {
				helper.setSubject("Item Packed Packed 📦");
				helper.setText("<h3>Hi " + name + "</h3>" + "<p>Your item from order #<b>" + orderId
						+ "</b> has been <b>packed</b> successfully.</p>" + "<p><b>Item ID:</b> " + orderItemId + "</p>"
						+ "<p>🚚 It will be shipped soon. Stay tuned for updates!</p>", true);
			}

			if ("OUT_FOR_DELIVERY".equals(status)) {
				helper.setSubject("Item Out For delivery 🛵");
				helper.setText("<h3>Hi " + name + "</h3>" + "<p>Your item from order #<b>" + orderId
						+ "</b> is now <b>out for delivery</b>.</p>" + "<p><b>Item ID:</b> " + orderItemId + "</p>"
						+ "<p>🚚 It will be delivered to your address very soon.</p>", true);
			}
			if ("CANCELLED".equals(status)) {
				helper.setSubject("Item Cancelled ✅");
				helper.setText(
						"<h3>Hi " + name + "</h3>" + "<p>Your item from order <b>" + orderId + "</b> is Cancelled</p>"
								+ "<p><b>Item ID:</b> " + orderItemId + "</p>",

						true);
			}
			if ("CONFIRMED".equals(status)) {
				helper.setSubject("Order Confirmed 🎉");

				helper.setText("<h3>Hi " + name + "</h3>" + "<p>Thank you for your order! Your order #<b>" + orderId
						+ "</b> has been <b>confirmed</b>.</p>"
						+ "<p>We’ll notify you once your items are packed and shipped.</p>", true);
			}
			mailSender.send(msg);

		} catch (Exception e) {
			System.out.println("Email failed");
		}
	}
}
