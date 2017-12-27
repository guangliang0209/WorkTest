package mail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;


public class Mail {
    private String username = null;
    private String password = null;
    private Authenticator auth = null;
    private MimeMessage mimeMessage = null;
    private Properties pros = null;
    private Multipart multipart = null;
    private BodyPart bodypart = null;

    public Mail() {
        initMessage();
    }

    /**
     * 初始化MimeMessage对象
     */
    private void initMessage() {
        setPros();
        this.auth = new Email_Autherticator();
        Session session = Session.getDefaultInstance(pros, auth);
        session.setDebug(true);
        mimeMessage = new MimeMessage(session);
    }

    /**
     * 设置email系统参数
     */
    private void setPros() {
        pros = new Properties();
        pros.setProperty("mail.smtp.host", "smtp.126.com");
        pros.setProperty("mail.smtp.auth", "true");
        pros.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        pros.setProperty("mail.smtp.port", "25");
        pros.setProperty("mail.smtp.socketFactory.port", "25");
        username = "fengguangliang520@126.com";
        password = "comsys1234";
    }

    /**
     * 设置发送邮件的基本参数
     *
     * @param sub  设置邮件主题
     * @param text 设置邮件文本内容
     * @param rec  设置邮件接收人
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public void setDefaultMessagePros(String sub, String text, String rec) throws MessagingException, UnsupportedEncodingException {
        mimeMessage.setSubject(sub);
        mimeMessage.setText(text);
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(rec));
        mimeMessage.setSentDate(new Date());
        mimeMessage.setFrom(new InternetAddress(username, username));
    }

    /**
     * 设置主题
     *
     * @param subject
     * @throws MessagingException
     */
    public void setSubject(String subject) throws MessagingException {
        mimeMessage.setSubject(subject);
    }

    /**
     * 设置日期
     *
     * @param date
     * @throws MessagingException
     */
    public void setDate(Date date) throws MessagingException {
        mimeMessage.setSentDate(new Date());
    }

    /**
     * 设置邮件头部
     *
     * @param arg0
     * @param arg1
     * @throws MessagingException
     */
    public void setHeader(String arg0, String arg1) throws MessagingException {
        mimeMessage.setHeader(arg0, arg1);
    }

    /**
     * 设置邮件接收人地址<单人发送>
     *
     * @param recipient
     * @throws MessagingException
     */
    public void setRecipient(String recipient) throws MessagingException {
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
    }

    /**
     * 设置邮件接收人地址<多人发送>
     *
     * @param recs
     * @return
     * @throws AddressException
     * @throws MessagingException
     */
    public String setRecipients(List<String> recs) throws AddressException, MessagingException {
        if (recs.isEmpty()) {
            return "接收人地址为空";
        }
        for (String str : recs) {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(str));
        }
        return "加入接收人地址成功";
    }

    /**
     * 设置邮件接收人地址<多人发送>
     *
     * @return
     * @throws MessagingException
     */
    @SuppressWarnings("static-access")
    public String setRecipients(StringBuffer sb) throws MessagingException {
        if (sb == null || "".equals(sb)) {
            return "字符串数据为空！";
        }
        Address[] address = InternetAddress.parse(sb.toString());
        mimeMessage.addRecipients(Message.RecipientType.TO, address);
        return password;
    }

    /**
     * 设置邮件发送人的名字
     *
     * @param from
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    public void setFrom(String from) throws UnsupportedEncodingException, MessagingException {
        mimeMessage.setFrom(new InternetAddress(username, from));
    }

    /**
     * 发送邮件<单人发送>
     *
     * @return
     * @throws MessagingException
     */
    public String sendMessage() throws MessagingException {
        Transport.send(mimeMessage);
        return "success";
    }

    /**
     * 设置附件
     *
     * @param file
     * @throws IOException
     * @throws MessagingException
     */
    public void setMultipart(String file) throws MessagingException, IOException {
        if (multipart == null) {
            multipart = new MimeMultipart();
        }
        multipart.addBodyPart(writeFiles(file));
        mimeMessage.setContent(multipart);
    }

    /**
     * 设置附件<添加多附件>
     *
     * @param fileList
     * @throws IOException
     * @throws MessagingException
     */
    public void setMultiparts(List<String> fileList) throws MessagingException, IOException {
        if (multipart == null) {
            multipart = new MimeMultipart();
        }
        for (String s : fileList) {
            multipart.addBodyPart(writeFiles(s));
        }
        mimeMessage.setContent(multipart);
    }

    /**
     * 发送文本内容，设置编码方式
     *
     * @param s
     * @param type
     * @throws MessagingException
     */
    public void setContent(String s, String type) throws MessagingException {
        if (multipart == null) {
            multipart = new MimeMultipart();
        }
        bodypart = new MimeBodyPart();
        bodypart.setContent(s, type);
        multipart.addBodyPart(bodypart);
        mimeMessage.setContent(multipart);
        mimeMessage.saveChanges();
    }

    public BodyPart writeFiles(String filePath) throws IOException, MessagingException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("文件不存在！请确定文件路径时候正确");
        }
        bodypart = new MimeBodyPart();
        DataSource dataSource = new FileDataSource(file);
        bodypart.setDataHandler(new DataHandler(dataSource));
        bodypart.setFileName(MimeUtility.encodeText(file.getName()));
        return bodypart;
    }


    /**
     * 验证账户密码
     *
     * @author hp
     */
    public class Email_Autherticator extends Authenticator {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }
}



