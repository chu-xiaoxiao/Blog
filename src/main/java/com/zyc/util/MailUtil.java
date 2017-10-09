package com.zyc.util;

import org.yaml.snakeyaml.Yaml;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.*;

/**
 * 邮件发送工具类
 * Created by YuChen Zhang on 17/10/09.
 */
public class MailUtil {
    /**
     * 邮箱用户名
     */
    private String mailAccount;
    /**
     * 邮箱密码
     */
    private String mailPassword;
    /**
     * 邮箱SMPT地址
     */
    private String mailSMTPAccount;

    public MailUtil() {

    }

    public MailUtil(String mailAccount, String mailPassword, String mailSMTPAccount) {
        this.mailAccount = mailAccount;
        this.mailPassword = mailPassword;
        this.mailSMTPAccount = mailSMTPAccount;
    }

    /**
     * 加载yml文件生成bean
     * @param url
     * @throws FileNotFoundException
     */
    public MailUtil(URL url) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        MailUtil mailUtil = yaml.loadAs(new FileInputStream(url.toString().substring(5)),MailUtil.class);
        this.mailAccount = mailUtil.mailAccount;
        this.mailPassword = mailUtil.mailPassword;
        this.mailSMTPAccount = mailUtil.mailSMTPAccount;
    }

    public String getMailAccount() {
        return mailAccount;
    }

    public void setMailAccount(String mailAccount) {
        this.mailAccount = mailAccount;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public String getMailSMTPAccount() {
        return mailSMTPAccount;
    }

    public void setMailSMTPAccount(String mailSMTPAccount) {
        this.mailSMTPAccount = mailSMTPAccount;
    }

    /**
     * 根据输入验证码长度生成验证码，发送邮件，并返回验证码
     * @param size
     */
    public String sendVerifyCode(Integer size,String receiveMail) throws UnsupportedEncodingException, MessagingException {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random(new Date().getTime());
        for(int i=0;i<size;i++){
            Integer temp = random.nextInt(62);
            if(temp<10){
                stringBuffer.append((char)(48+temp));
            }else if(temp<36){
                stringBuffer.append((char)(65-10+temp));
            }else{
                stringBuffer.append((char)(97-36+temp));
            }
        }
        this.sendMail("博客注册验证码","获取的验证码为"+stringBuffer.toString(),receiveMail);
        return stringBuffer.toString();
    }
    /**
     * 发送邮件
     * @param subject
     * @param message
     * @param receiveMail
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    public void sendMail(String subject,String message,String... receiveMail) throws UnsupportedEncodingException, MessagingException {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", this.getMailSMTPAccount());   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        MimeMessage mimeMessage = this.createMail(session,subject,message,receiveMail);
        Transport transport = session.getTransport();
        transport.connect(this.getMailAccount(), this.getMailPassword());
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
    }

    /**
     *创建一份邮件
     * @param session
     */
    private MimeMessage  createMail(Session session,String subject,String message,String... receiveMail) throws MessagingException, UnsupportedEncodingException {
        //创建一封邮件
        MimeMessage mimeMessage = new MimeMessage(session);
        //添加发送人
        mimeMessage.setFrom(new InternetAddress(this.getMailAccount(),"SSM","UTF-8"));
        //添加收件人
        List<InternetAddress> internetAddresses = new ArrayList<InternetAddress>();
        for(String temp : receiveMail){
            internetAddresses.add(new InternetAddress(temp,temp,"UTF-8"));
        }
        InternetAddress[] internetAddresses1 = internetAddresses.toArray(new InternetAddress[internetAddresses.size()]);
        mimeMessage.setRecipients(MimeMessage.RecipientType.TO,internetAddresses1);
        //添加主题
        mimeMessage.setSubject(subject,"UTF-8");
        //设置邮件正文
        mimeMessage.setContent(message,"text/html;charset=UTF-8");
        //设置发送时间
        mimeMessage.setSentDate(new Date());
        //保存设置
        mimeMessage.saveChanges();
        return mimeMessage;
    }
}
