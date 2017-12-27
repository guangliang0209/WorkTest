package wechat.model;

/**
 * @author : fengguangliang
 * @description : token实体类
 * @date : 2017/4/24
 */
public class AccessToken {
    /**
     * 获取到的凭证。长度为64至512个字节
     */
    private String access_token;
    /**
     * 凭证的有效时间（秒）
     */
    private Integer expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }
}
