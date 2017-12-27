package mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.mail.MessagingException;

/**
 * Created by Administrator on 2017\6\27 0027.
 */
public class MailTest {
    public static void main(String[] args) {
        test();
    }

    public static Boolean test() {
        String email = "fengguangliang520@126.com";
        try {
            Mail mail = new Mail();
            mail.setRecipient(email);
            mail.setSubject("title");
            mail.setDate(new Date());
            mail.setFrom("rabbit");
            mail.setContent("邮件发送账号信息正确性测试", "text/html; charset=utf-8");
            mail.sendMessage();
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
