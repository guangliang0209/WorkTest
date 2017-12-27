package wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import wechat.model.AccessToken;
import wechat.service.TokenService;
import wechat.util.CommonUtil;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by fengguangliang on 2017/4/26.
 */
public class TokenServiceImpl implements TokenService {

    @Override
    /**
     * @param corpId     应用组织编号
     * @param corpSecret 应用秘钥
     * @author : fengguangliang
     * @description:获取token的值
     */
    public AccessToken getToken(String corpId, String corpSecret) throws Exception {
        AccessToken accessToken = null;
        String param = "corpid=" + URLEncoder.encode(corpId) + "&corpsecret=" + URLEncoder.encode(corpSecret);
        try {
            JSONObject jsonObject = JSONObject.parseObject(CommonUtil.doGet(ACCESS_TOKEN_URL, param));
            // 如果请求成功
            if (null != jsonObject) {
                accessToken = new AccessToken();
                accessToken.setAccess_token(jsonObject.getString("access_token"));
                accessToken.setExpires_in(jsonObject.getInteger("expires_in"));
                System.out.println("获取token成功:" + jsonObject.getString("access_token") + "————" + jsonObject.getInteger("expires_in"));
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return accessToken;
    }
}
