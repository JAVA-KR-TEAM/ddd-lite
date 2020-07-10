package com.app.dddlite.infra.sms;

public interface SmsClient {
	void send(SmsMessage smsMessage);
}
