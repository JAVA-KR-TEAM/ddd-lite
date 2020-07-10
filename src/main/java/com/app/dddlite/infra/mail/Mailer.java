package com.app.dddlite.infra.mail;

public interface Mailer {
	void send(MailMessage mailMessage);
}
