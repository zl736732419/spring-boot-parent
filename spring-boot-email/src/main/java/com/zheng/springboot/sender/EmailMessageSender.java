package com.zheng.springboot.sender;

import com.zheng.springboot.content.builder.DefaultMessageContentBuilder;
import com.zheng.springboot.content.builder.MessageContentBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @Author Administrator
 * @Date 2018/5/23 10:51
 */
@Component
public class EmailMessageSender {
    private Log log = LogFactory.getLog(EmailMessageSender.class);

    @Value("${spring.mail.from}")
    private String from;
    @Autowired
    private JavaMailSender sender;

    /**
     * 支持的图片类型
     */
    private static String IMAGE_TYPES = "png,jpg,jpeg,gif,bmp";
    
    /**
     * 发送普通文本邮件
     *
     * @param to
     * @param text
     * @param subject
     */
    public boolean sendSimpleMessage(String to, String text, String subject) {
        if (!checkValid(to, text)) {
            return false;
        }

        MimeMessagePreparator preparator = (mimeMessage) -> {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(text);
        };
        try {
            sender.send(preparator);
        } catch (MailException ex) {
            log.error(ex.getMessage());
            return false;
        }
        return true;
    }

    private boolean checkValid(String to, String text) {
        if (StringUtils.isEmpty(to)) {
            log.error("发送普通文本消息失败：接受者邮箱不能为空");
            return false;
        }

        if (StringUtils.isEmpty(text)) {
            log.error("发送普通文本消息失败，发送内容不能为空");
            return false;
        }

        return true;
    }

    /**
     * 发送带附件的邮件
     *
     * @param to
     * @param text
     * @param subject
     * @param file
     */
    public boolean sendAttachmentMessage(String to, String text, String subject, File file) {
        if (!Optional.ofNullable(file).isPresent() || !file.exists()) {
            return sendSimpleMessage(to, text, subject);
        }
        if (!checkValid(to, text)) {
            return false;
        }
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            helper.setFrom(new InternetAddress(from));
            helper.setTo(new InternetAddress(to));
            helper.setSubject(subject);
            helper.setText(text);
            FileSystemResource attachment = new FileSystemResource(file);
            String fileName = MimeUtility.encodeWord(file.getName());
            helper.addAttachment(fileName, attachment);
        } catch (Exception e) {
            log.error(e.getCause());
            return false;
        }

        try {
            sender.send(message);
        } catch (MailException ex) {
            log.error(ex.getMessage());
            return false;
        }
        
        return true;
    }

    /**
     * 发送邮件，邮件正文包含图片
     * @param to
     * @param text
     * @param subject
     * @param image
     * @return
     */
    public boolean sendInlineMessage(String to, String text, String subject, File image, MessageContentBuilder builder) {
        if (!Optional.ofNullable(image).isPresent() || !image.exists()) {
            return sendSimpleMessage(to, text, subject);
        }
        if (!checkValid(to, text)) {
            return false;
        }

        String name = image.getName().toLowerCase();
        if (!isImage(name)) {
            return sendAttachmentMessage(to, text, subject, image);
        }

        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress(from));
            helper.setTo(new InternetAddress(to));
            helper.setSubject(subject);
            if (!Optional.ofNullable(builder).isPresent()) {
                builder = new DefaultMessageContentBuilder();
            }
            
            String identifier = buildRandomIdentifier();
            String url = new StringBuilder("cid:").append(identifier).toString();
            String content = builder.build(text, url);
            helper.setText(content, true);
            
            // 注意内嵌图片一定要在文本赋值之后，不然邮件内容展示不出来指定的内嵌图片
            FileSystemResource resource = new FileSystemResource(image);
            helper.addInline(identifier, resource);
        } catch (MessagingException e) {
            log.error(e.getCause());
            return false;
        }

        try {
            sender.send(message);
        } catch (MailException ex) {
            log.error(ex.getMessage());
            return false;
        }
        
        return true;
    }

    private String buildRandomIdentifier() {
        String uuid = UUID.randomUUID().toString();
        String identifier = uuid.replaceAll("\\-", "");
        return identifier;
    }

    /**
     * 判断是否是图片类型文件
     * @param name
     * @return
     */
    private boolean isImage(String name) {
        int suffixIndex = name.lastIndexOf(".") + 1;
        if (suffixIndex >= name.length()) {
            return false;
        }
        String suffix = name.substring(suffixIndex);
        String[] types = IMAGE_TYPES.split(",");
        List<String> typeList = Arrays.asList(types);
        if (!typeList.contains(suffix)) {
            return false;
        }

        return true;
    }
}
