package com.overclock.overclock.service.impl;

import com.overclock.overclock.service.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSenderImpl.class);
    private final JavaMailSender mailSender;

    public EmailSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            mailSender.send(message);
        } catch (MailSendException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void sendForgotPasswordMail(String to, String link) {
        String subject = "[Overclock] Reset password";
        String text = "Follow this link to reset your password: " + link + ".\n"
                + "If you don`t send such request, just ignore this message.";

        sendMail(to, subject, text);
    }

    @Override
    public void sendActivateAccountMail(String to, String link) {
        String subject = "[Overclock] Active your account";
        String text = "Please, follow this link to activate your account " + link + ".\n"
                + "If you don`t send such request, please contact us.";

        sendMail(to, subject, text);
    }
}
