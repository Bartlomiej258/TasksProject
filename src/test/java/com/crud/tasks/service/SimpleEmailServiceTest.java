package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    void shouldSendEmail() {
        //given
        Mail mail = Mail.newBuilder()
                .mailTo("test@test.com")
                .subject("Test")
                .message("Test Message")
                .toCc("nowy@mail.com")
                .build();

        SimpleMailMessage mailMessage = simpleEmailService.createdMailMessage(mail);

        //when
        simpleEmailService.send(mail);

        //then
        verify(javaMailSender, times(1)).send(mailMessage);
    }
}