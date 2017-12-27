package wechat.model;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.annotations.SerializedName;

/**
 * @author : fengguangliang
 * @description : 用户实体类
 * @date : 2017/4/24
 */
public class Person {
    /**
     * 必填，成员UserID。对应管理端的帐号，企业内必须唯一。不区分大小写，长度为1~64个字节
     */
    private String userid;
    /**
     * 必填，成员名称。长度为1~64个字节
     */
    private String name;
    /**
     * 必填，成员所属部门id列表,不超过20个
     */
    private int[] department;
    /**
     * 非必填，职位信息。长度为0~64个字节
     */
    private String position;
    /**
     * 非必填，手机号码。企业内必须唯一，mobile/weixinid/email三者不能同时为空
     */
    private String mobile;
    /**
     * 非必填，性别。1表示男性，2表示女性
     */
    private String gender;
    /**
     * 非必填，邮箱。长度为0~64个字节。企业内必须唯一
     */
    private String email;
    /**
     * 非必填，微信号。企业内必须唯一
     */
    private String weixinid;

    /**
     * 非必填,启用/禁用成员。1表示启用成员，0表示禁用成员
     */
    private int enable;
    /**
     * 非必填，成员头像的mediaid，通过多媒体接口上传图片获得的mediaid
     */
    @SerializedName("avatar")
    private String avatar_mediaid;
    /**
     * 非必填，扩展属性。扩展属性需要在WEB管理端创建后才生效，否则忽略未知属性的赋值
     */
    private JSONObject extattr;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getDepartment() {
        return department;
    }

    public void setDepartment(int[] department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeixinid() {
        return weixinid;
    }

    public void setWeixinid(String weixinid) {
        this.weixinid = weixinid;
    }

    public String getAvatar_mediaid() {
        return avatar_mediaid;
    }

    public void setAvatar_mediaid(String avatar_mediaid) {
        this.avatar_mediaid = avatar_mediaid;
    }

    public JSONObject getExtattr() {
        return extattr;
    }

    public void setExtattr(JSONObject extattr) {
        this.extattr = extattr;
    }
}
