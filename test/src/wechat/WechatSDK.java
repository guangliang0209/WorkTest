package wechat;

import wechat.model.AccessToken;
import wechat.service.impl.TokenServiceImpl;

/**
 * Created by fengguangliang on 2017/4/25.
 */
public class WechatSDK {
    private String corpId;
    private String corpSecret;
    private TokenServiceImpl token = new TokenServiceImpl();

    public WechatSDK() {

    }

    private WechatSDK(String corpId, String corpSecret) {
        this.corpId = corpId;
        this.corpSecret = corpSecret;
    }

    public AccessToken initToken() {
        AccessToken accessToken = null;
        try {
            accessToken = token.getToken(this.corpId, this.corpSecret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }
}
