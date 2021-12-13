package com.overclock.overclock.service;

public interface EmailSender {
    void sendMail(String to, String subject, String text);
    void sendForgotPasswordMail(String to, String link);
    void sendActivateAccountMail(String to, String link);
}
