package wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import wechat.model.FileMessage;
import wechat.model.ImageMessage;
import wechat.model.MpNewsMessage;
import wechat.model.NewsMessage;
import wechat.model.TextMessage;
import wechat.model.VideoMessage;
import wechat.model.VoiceMessage;
import wechat.service.SendWechatMsgService;
import wechat.util.CommonUtil;

import java.net.URLEncoder;

/**
 * @author : fengguangliang
 * @description : 发送微信消息
 * @date : 2017/4/25
 */
public class SendWechatMsgServiceImpl implements SendWechatMsgService {

    /**
     * @param textMessage: 消息体
     * @author : fengguangliang
     * @description : 发送text文本消息
     * @date : 2017/4/25
     */
    @Override
    public Boolean sendTextMsg(String token, TextMessage textMessage) {
        Boolean isSuccess = true;
        String url = SEND_URL.replace("ACCESS_TOKEN", URLEncoder.encode(token));
        String param = gson.toJson(textMessage);
        try {
            JSONObject object = JSONObject.parseObject(CommonUtil.doPost(url, param));
            if (object != null && object.getInteger("errcode") != 0) {
                isSuccess = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;
    }

    @Override
    public Boolean sendImageMessage(String token, ImageMessage imageMessage) {
        return null;
    }

    @Override
    public Boolean sendVoiceMessage(String token, VoiceMessage voiceMessage) {
        return null;
    }

    @Override
    public Boolean sendVideoMessage(String token, VideoMessage videoMessage) {
        return null;
    }

    @Override
    public Boolean sendFileMessage(String token, FileMessage fileMessage) {
        return null;
    }

    @Override
    public Boolean sendNewsMessage(String token, NewsMessage newsMessage) {
        Boolean isSuccess = true;
        String url = SEND_URL.replace("ACCESS_TOKEN", URLEncoder.encode(token));
        String param = gson.toJson(newsMessage);
        try {
            JSONObject object = JSONObject.parseObject(CommonUtil.doPost(url, param));
            if (object != null && object.getInteger("errcode") != 0) {
                isSuccess = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;
    }

    @Override
    public Boolean sendMpNewsMessage(String token, MpNewsMessage mpNewsMessage) {
        return null;
    }
}
