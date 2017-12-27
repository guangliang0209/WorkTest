package wechat.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import wechat.model.FileMessage;
import wechat.model.ImageMessage;
import wechat.model.MpNewsMessage;
import wechat.model.NewsMessage;
import wechat.model.TextMessage;
import wechat.model.VideoMessage;
import wechat.model.VoiceMessage;

/**
 * Created by fengguangliang on 2017/4/25.
 */
public interface SendWechatMsgService {
    Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSS")
            .create();

    String SEND_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";

    Boolean sendTextMsg(String token, TextMessage textMessage);

    Boolean sendImageMessage(String token, ImageMessage imageMessage);

    Boolean sendVoiceMessage(String token, VoiceMessage voiceMessage);

    Boolean sendVideoMessage(String token, VideoMessage videoMessage);

    Boolean sendFileMessage(String token, FileMessage fileMessage);

    Boolean sendNewsMessage(String token, NewsMessage newsMessage);

    Boolean sendMpNewsMessage(String token, MpNewsMessage mpNewsMessage);
}
