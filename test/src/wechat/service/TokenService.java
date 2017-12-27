package wechat.service;

import wechat.model.AccessToken;

import java.io.IOException;

/**
 * Created by fengguangliang on 2017/4/26.
 */
public interface TokenService {
    String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

    AccessToken getToken(String corpId, String corpSecret) throws Exception;

}
