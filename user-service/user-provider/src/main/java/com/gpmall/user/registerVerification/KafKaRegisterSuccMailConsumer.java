package com.gpmall.user.registerVerification;

import com.gpmall.commons.tool.email.DefaultEmailSender;
import com.gpmall.commons.tool.email.MailData;
import com.gpmall.commons.tool.email.emailConfig.EmailConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Administrator
 * 2019/8/22 0022
 * 18:32
 */
@Component
@Slf4j
public class KafKaRegisterSuccMailConsumer {

    private final static String topic = "user-register-succ-topic";

    private final static String group_id = "mail-group-id";

    @Autowired
    EmailConfig emailConfig;

    @Autowired
    DefaultEmailSender defaultEmailSender;

    /**
     * 指定消费某一个分区
     * @TopicPartition(topic=topic,partitions = {"1"})
     */
    @KafkaListener(topics = topic, containerFactory = "userRegisterSuccKafkaListenerContainerFactory", groupId = group_id)
    public void receiveInfo(Map userVerifyMap, Acknowledgment acknowledgment) {
        try {
            log.info("收到一条注册消息" + userVerifyMap);
            sendMail(userVerifyMap);
            //手动提交消息
            acknowledgment.acknowledge();
        } catch (Exception e) {
            log.error("Sending email failed", e);
        }
    }

    public void sendMail(Map userVerifyMap) throws Exception {
        MailData mailData = new MailData();
        mailData.setToAddresss(Arrays.asList((String) userVerifyMap.get("email")));
        mailData.setSubject(emailConfig.getSubject());
        mailData.setContent("用户激活邮件");
        Map<String, Object> viewObj = new HashMap<>();
        String url = emailConfig.getUserMailActiveUrl() + "?username=" + userVerifyMap.get("username")
                + "&uuid=" + userVerifyMap.get("key");
        log.info("Email active url={}", url);
        viewObj.put("url", url);
        viewObj.put("title", emailConfig.getSubject());
        mailData.setFileName("activeRegisterInfoHtmlTemplate.html");
        mailData.setDataMap(viewObj);
        //defaultEmailSender.sendHtmlMailUseTemplate(mailData);
    }
}
