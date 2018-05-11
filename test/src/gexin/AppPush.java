package gexin;

import com.alibaba.fastjson.JSONObject;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengguangliang on 2017/5/25.
 */
public class AppPush {
    private static String APP_ID;
    private static String APP_KEY;
    private static String MASTER_SECRET;
    private static String APP_SECRET;
    private final static String URL = "http://sdk.open.api.igexin.com/apiex.htm";
    private IGtPush push;

    public AppPush(String appId, String appKey, String masterSecret, String appSecret) {
        AppPush.APP_ID = appId;
        AppPush.APP_KEY = appKey;
        AppPush.MASTER_SECRET = masterSecret;
        AppPush.APP_SECRET = appSecret;
        push = new IGtPush(URL, appKey, masterSecret);
    }

    public void send(JSONObject param) {
        TransmissionTemplate template = getTemplate(param);
        List<String> appIds = new ArrayList<>();
        appIds.add(APP_ID);
        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);
        message.setData(template);
        message.setPushNetWorkType(0);//可选
        Target target = new Target();
        target.setAppId(APP_ID);
        target.setClientId("");//用户设备锁
        message.setOffline(true);
        IPushResult ret = push.pushMessageToSingle(message, target);
        System.out.println(ret.getResponse().toString());
    }

    /**
     * @return template
     * @author : fengguangliang
     * @description : 设置透传消息模板
     * @date : 2017/5/25
     */
    public static TransmissionTemplate getTemplate(JSONObject param) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(APP_ID);
        template.setAppkey(APP_KEY);
        template.setTransmissionContent(param.toJSONString());
        template.setTransmissionType(2);
        return template;
    }
}
