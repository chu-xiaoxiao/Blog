package test.com.zyc.util; 

import com.zyc.util.MailUtil;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

/** 
* MailUtil Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮ�� 9, 2017</pre> 
* @version 1.0 
*/ 
public class MailUtilTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: loadFromYml(String url) 
* 
*/ 
@Test
public void testLoadFromYml() throws Exception {
        URL url = MailUtil.class.getClassLoader().getResource("mail.yml");
        MailUtil mailUtil = new MailUtil(url);
        mailUtil.sendMail("新的测试","看看测试内容","739781049@qq.com");
    }
@Test
public void testSend() throws FileNotFoundException, UnsupportedEncodingException, MessagingException {
    URL url = MailUtil.class.getClassLoader().getResource("mail.yml");
    MailUtil mailUtil = new MailUtil(url);
    mailUtil.sendVerifyCode(2000,"739781049@qq.com");
}
}
