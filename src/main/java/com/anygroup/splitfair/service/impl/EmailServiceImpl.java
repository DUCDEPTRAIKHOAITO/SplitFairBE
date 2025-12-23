package com.anygroup.splitfair.service.impl;

import com.anygroup.splitfair.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendOtp(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("SplitFair - OTP Reset Password");
        message.setText("Mã OTP của bạn là: " + otp + "\nHết hạn trong 5 phút.");
        mailSender.send(message);
    }
}
