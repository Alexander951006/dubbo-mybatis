package com.example.dubbomybatisprovider.service.Impl;



import com.example.api.MailService;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.MailSender;

import javax.annotation.Resource;

@DubboService(interfaceClass = MailService.class, version = "1.0.0",timeout=1000)
public class MailServiceImpl implements MailService{
    @Value("${spring.mail.username}")
    private String fromPeo;

    @Resource
    private JavaMailSender mailSender;

    Logger logger = LoggerFactory.getLogger(this.getClass());



    public void sendSimpleMail(String toPeo,String title,String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromPeo);//是谁发送的
        message.setTo(toPeo);//发送给谁
        message.setSubject(title);//标题
        message.setText(content);//内容
//        System.out.println(from);
        mailSender.send(message);
        logger.info("邮件发送成功！");
    }

}
