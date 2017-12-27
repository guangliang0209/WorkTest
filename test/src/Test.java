import com.alibaba.fastjson.JSONObject;
import gexin.AppPush;
import mail.Mail;
import wechat.model.AccessToken;
import wechat.model.NewsMessage;
import wechat.model.TextMessage;
import wechat.service.SendWechatMsgService;
import wechat.service.impl.ContactsGroupServiceImpl;
import wechat.service.impl.ContactsPersonServiceImpl;
import wechat.service.impl.SendWechatMsgServiceImpl;
import wechat.service.impl.TokenServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fengguangliang on 2016/5/10.
 */
public class Test {

    public static void main(String[] args) {
        String corpId = "wxc58581589a596f08";
        String corpSecret = "pbSCiYGAvvybmOG4oYkhPFJ-HP7miyJ5OeSxzVGkGWGbpMUpQr_kk5LzU2K-HZmi";
        TokenServiceImpl token = new TokenServiceImpl();
        SendWechatMsgService sendWechatMsgService = new SendWechatMsgServiceImpl();

        try {
            /*AccessToken accessToken = token.getToken(corpId, corpSecret);
            String tokenStr = accessToken.getAccess_token();

            System.out.println("==========发送图文方式企业微信==========");
            NewsMessage newsMessage = new NewsMessage();
            newsMessage.setTouser("fengguangliang");
            newsMessage.setMsgtype("news");
            newsMessage.setAgentid(0);

            NewsMessage.Articles articles = new NewsMessage.Articles();

            NewsMessage.Article article = new NewsMessage.Article();

            article.setTitle("中秋快乐");
            article.setDescription("祝您中秋快乐");
            article.setUrl("");
            article.setPicurl("http://res.mail.qq.com/node/ww/wwopenmng/images/independent/doc/test_pic_msg1.png");
            List<NewsMessage.Article> list = new ArrayList<>();
            list.add(article);
            articles.setArticles(list);
            newsMessage.setNews(articles);
            sendWechatMsgService.sendNewsMessage(tokenStr, newsMessage);*/



            /*TextMessage textMessage = new TextMessage();
            textMessage.setTouser("fengguangliang");
            textMessage.setMsgtype("text");
            textMessage.setAgentid(0);

            TextMessage.Content c = new TextMessage.Content();
            c.setContent("测试原有温馨信息是否能用");
            textMessage.setText(c);
            sendWechatMsgService.sendTextMsg(tokenStr, textMessage);*/
            /*List<Group> groups = contactsGroupServiceImpl.getGroupList(accessToken.getAccess_token(), null);
            System.out.println(groups);*/
            /*Group group = new Group();
            group.setName("康塞研发");
            group.setParentid(1);
            Boolean result = contactsGroupServiceImpl.create(accessToken.getAccess_token(), group);
            System.out.println(result);*/
//            contactsGroupServiceImpl.delete(tokenStr, "3");
//            contactsPersonServiceImpl.getGroupPersonDetail(tokenStr, "1", null, null);

            /*TextMessage textMessage = new TextMessage();
            textMessage.setTouser("@all");
            textMessage.setMsgtype("text");
            textMessage.setAgentid(0);

            TextMessage.Content content = new TextMessage.Content();
            content.setContent("hello everyone!");
            textMessage.setText(content);*/
//            textMessage.setSafe(1);

            /**发消息*/
//            sendWechatMsgServiceImpl.sendTextMsg(tokenStr, textMessage);

            /*Long date1 = new Date().getTime();
            Long date2 = new Date().getTime();
            Integer second = Math.toIntExact((date1 - date2) / 1000);

            System.out.println(second);*/

            /*String email = "hubibo@comsys.net.cn";
            Mail mail = new Mail();
            mail.setRecipient(email);
            mail.setSubject("title");
            mail.setDate(new Date());
            mail.setFrom("rabbit");
            mail.setContent("账号信息正确性测试", "text/html; charset=utf-8");
            mail.sendMessage();*/

            /*String appId = "4xSUZgaviiAQU3sxo5qLU5";
            String appKey = "VLZlGmZA2d9tbQipMChqiA";
            String masterSecret = "i2JyBo9DXo8TJixE7xMro9";
            String appSecret = "1L7PuY2M377ZYFohhogyo9";
            String appGroupId = "111";
            AppPush appPush = new AppPush(appId, appKey, masterSecret, appSecret);
            JSONObject object = new JSONObject();
            object.put("type", "1");
            object.put("appGroupId", appGroupId);
            JSONObject contentObj = new JSONObject();
            contentObj.put("title", "ump test title");
            contentObj.put("text", "ump test contentttttttttttttttwevwevr");
            contentObj.put("uri", "");
            contentObj.put("pluginId", "");
            contentObj.put("ext", "");
            object.put("content", contentObj);
            appPush.send(object);
            */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
