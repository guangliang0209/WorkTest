package wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.reflect.TypeToken;
import wechat.model.Person;
import wechat.service.ContactsPersonService;
import wechat.util.CommonUtil;

import java.net.URLEncoder;
import java.util.List;

/**
 * @author : fengguangliang
 * @description : 通讯录成员管理类
 * @date : 2017/4/24
 */
public class ContactsPersonServiceImpl implements ContactsPersonService {


    /**
     * @param person 用户实体
     * @return true成功false失败
     * @description:创建成员
     * @author : fengguangliang
     */
    @Override
    public Boolean create(String token, Person person) {
        Boolean result = true;
        String param = gson.toJson(person);
        try {
            String url = CREATE_URL.replace("ACCESS_TOKEN", URLEncoder.encode(token, "utf-8"));
            JSONObject object = JSONObject.parseObject(CommonUtil.doPost(url, param));
            if (object != null && object.getInteger("errcode") != 0) {
                result = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * @param person 用户实体
     * @return true成功false失败
     * @description:更新成员
     * @author : fengguangliang
     */
    @Override
    public Boolean update(String token, Person person) {
        Boolean result = true;
        String param = gson.toJson(person);
        String url = UPDATE_URL.replace("ACCESS_TOKEN", URLEncoder.encode(token));
        try {
            JSONObject object = JSONObject.parseObject(CommonUtil.doPost(url, param));
            if (object != null && object.getInteger("errcode") != 0) {
                result = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * @param userId 员工userId，对应管理端的帐号
     * @return true成功false失败
     * @description : 删除成员
     */
    @Override
    public Boolean delete(String token, String userId) {
        Boolean result = true;
        String deleteUrl = DELETE_URL.replace("ACCESS_TOKEN", URLEncoder.encode(token)).replace("USERID", userId);
        try {
            JSONObject object = JSONObject.parseObject(CommonUtil.doGet(deleteUrl, null));
            if (object != null && object.getInteger("errcode") != 0) {
                result = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param useridlist:成员UserID列表。对应管理端的帐号。（最多支持200个）
     * @return true成功false失败
     * @author : fengguangliang
     * @description : 批量删除
     * @date : 2017/4/24
     */
    @Override
    public Boolean groupDelete(String token, List<String> useridlist) {
        Boolean result = true;
        JSONObject object = new JSONObject();
        object.put("useridlist", useridlist);
        String param = object.toJSONString();
        String url = BATCH_DELETE_URL.replace("ACCESS_TOKEN", URLEncoder.encode(token));
        try {
            JSONObject jsonObject = JSONObject.parseObject(CommonUtil.doPost(url, param));
            if (jsonObject != null && jsonObject.getInteger("errcode") != 0) {
                result = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * @param userid 员工UserID。对应管理端的帐号
     * @return 成员实体
     * @description:获取成员
     */
    @Override
    public Person getPerson(String token, String userid) {
        String url = GET_PERSON_URL.replace("ACCESS_TOKEN", URLEncoder.encode(token)).replace("USERID", userid);
        Person person = null;
        try {
            String s = CommonUtil.doGet(url, null);
            person = gson.fromJson(s, Person.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }

    /**
     * @param departmentId 必填，获取的部门id
     * @param fetchChild   不必填，1/0：是否递归获取子部门下面的成员
     * @param status       不必填，0获取全部成员，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加，未填写则默认为4
     * @return 拼接地址
     * @description :获取部门成员
     */
    @Override
    public List<Person> getGroupPersonDetail(String token, String departmentId, Integer fetchChild, Integer status) {
        if (fetchChild == null) {
            fetchChild = 0;
        }
        if (status == null) {
            status = 0;
        }
        String getGroupUrl = GET_GROUP_PERSON_DETAIL_URL.replace("ACCESS_TOKEN", URLEncoder.encode(token)).replace("DEPARTMENT_ID", departmentId);
        getGroupUrl = getGroupUrl.replace("FETCH_CHILD", fetchChild.toString());
        getGroupUrl = getGroupUrl.replace("STATUS", status.toString());
        List<Person> persons = null;
        try {
            JSONObject object = JSONObject.parseObject(CommonUtil.doGet(getGroupUrl, null));
            persons = gson.fromJson(object.getJSONArray("userlist").toJSONString(), new TypeToken<List<Person>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }
}
