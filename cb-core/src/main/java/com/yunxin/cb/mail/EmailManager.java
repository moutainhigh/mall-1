/**
 *
 */
package com.yunxin.cb.mail;

import freemarker.template.Template;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.util.Map;

/**
 * @author gonglei
 */
public class EmailManager {

    private JavaMailSender mailSender;

    private String senderFrom;

    private FreeMarkerConfigurer freeMarkerConfigurer;

    public void sendMail(String[] to, String[] cc, String subject, Map<String, Object> root, String templateName,
                         String attachmentName, File attachmentFile) {
        String htmlText = "";
        try {
            //通过指定模板名获取FreeMarker模板实例
            Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendMail(to, cc, subject, htmlText, attachmentName, attachmentFile);
    }

    public void sendMail(String[] to, String[] cc, String subject, String content,
                         String attachmentName, File attachmentFile) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setTo(to);
            if (cc != null && cc.length > 0) {
                message.setCc(cc);
            }
            message.setSubject(subject);
            message.setFrom(senderFrom);
            message.setText(content, true);

            if (attachmentName != null) {
                FileSystemResource file = new FileSystemResource(attachmentFile);
                message.addAttachment(attachmentName, file);
            }
        };
        mailSender.send(preparator);
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String getSenderFrom() {
        return senderFrom;
    }

    public void setSenderFrom(String senderFrom) {
        this.senderFrom = senderFrom;
    }

    public FreeMarkerConfigurer getFreeMarkerConfigurer() {
        return freeMarkerConfigurer;
    }

    public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

}
