package com.zheng.springboot;

import com.zheng.springboot.sender.EmailMessageSender;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * @Author Administrator
 * @Date 2018/5/23 11:53
 */
public class EmailSenderTest extends BaseServiceTest {
    @Autowired
    private EmailMessageSender sender;
    
    @Test
    public void simpleMessage() {
        String to = "13637992082@163.com";
        String text = "hello from spring boot project test !";
        String subject = "Test";
        boolean result = sender.sendSimpleMessage(to, text, subject);
        Assert.assertTrue(result);
    }
    
    @Test
    public void attachmentMessage() {
        String to = "13637992082@163.com";
        String text = "hello from spring boot project test !";
        String subject = "Attachment";
        File file = new File("C:\\Users\\Administrator\\Desktop\\19-150HF32448.jpg");
        boolean result = sender.sendAttachmentMessage(to, text, subject, file);
        Assert.assertTrue(result);
    }

    @Test
    public void inlineMessage() {
        String to = "13637992082@163.com";
        String text = "hello from spring boot project test !";
        String subject = "Inline";
        File file = new File("C:\\Users\\Administrator\\Desktop\\19-150HF32448.jpg");
        boolean result = sender.sendInlineMessage(to, text, subject, file, null);
        Assert.assertTrue(result);
    }
}
