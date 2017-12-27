package wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.reflect.TypeToken;
import wechat.model.Person;
import wechat.model.Tag;
import wechat.service.ContactsTagService;
import wechat.util.CommonUtil;

import java.net.URLEncoder;
import java.util.List;

/**
 * Created by fengguangliang on 2017/4/25.
 */
public class ContactsTagServiceImpl implements ContactsTagService {

    /**
     * @param token: token
     * @param tag:   tag实体
     * @return true成功false失败
     * @author : fengguangliang
     * @description : 创建标签
     * @date : 2017/4/25
     */
    @Override
    public Boolean create(String token, Tag tag) {
        Boolean result = true;
        String url = CREATE_URL.replace("ACCESS_TOKEN", URLEncoder.encode(token));
        String param = gson.toJson(tag);
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
     * @param :
     * @return
     * @author : fengguangliang
     * @description : 更新操作
     * @date : 2017/4/25
     */
    @Override
    public Boolean update(String token, Tag tag) {
        Boolean result = true;
        String param = gson.toJson(tag);
        String url = UPDATE_URL.replace("ACCESS_TOKEN", URLEncoder.encode(token));
        try {
            JSONObject object = JSONObject.parseObject(CommonUtil.doPost(url, param));
            if (object != null && object.getInteger("errcode") != 0) {
                result = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param tagId: 标签id， 必填
     * @author : fengguangliang
     * @description : 删除标签
     * @date : 2017/4/25
     */
    @Override
    public Boolean delete(String token, String tagId) {
        Boolean result = true;
        String url = DELETE_URL.replace("ACCESS_TOKEN", URLEncoder.encode(token)).replace("TAGID", URLEncoder.encode(tagId));
        try {
            JSONObject object = JSONObject.parseObject(CommonUtil.doGet(url, null));
            if (object != null && object.getInteger("errcode") != 0) {
                result = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param tagId: 标签id， 必填
     * @return 标签下成员列表
     * @author : fengguangliang
     * @description : 获取标签下成员
     * @date : 2017/4/25
     */
    @Override
    public List<Person> getTagPerson(String token, String tagId) {
        String url = GET_TAG_PERSON.replace("ACCESS_TOKEN", URLEncoder.encode(token)).replace("TAGID", URLEncoder.encode(tagId));
        List<Person> persons = null;
        try {
            JSONObject object = JSONObject.parseObject(CommonUtil.doGet(url, null));
            persons = gson.fromJson(object.getJSONArray("userlist").toJSONString(), new TypeToken<List<Person>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }

    /**
     * @param tagId:必填，tagid
     * @param userList:不必填，企业成员ID列表，注意：userlist、partylist不能同时为空，单次请求长度不超过1000
     * @param partyList:不必填，企业部门ID列表，注意：userlist、partylist不能同时为空，单次请求长度不超过100
     * @return
     * @author : fengguangliang
     * @description : 新增标签下成员
     * @date : 2017/4/25
     */
    @Override
    public Boolean addTagPerson(String token, String tagId, List<String> userList, List<Integer> partyList) {
        Boolean result = true;
        JSONObject object = new JSONObject();
        object.put("tagid", tagId);
        if (!userList.isEmpty()) {
            object.put("userlist", userList);
        }

        if (!partyList.isEmpty()) {
            object.put("partylist", partyList);
        }
        String url = ADD_TAG_PERSON.replace("ACCESS_TOKEN", URLEncoder.encode(token));
        String param = object.toJSONString();
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
     * @param tagId: 必填
     * @return
     * @author : fengguangliang
     * @description : 删除标签下成员
     * @date : 2017/4/25
     */
    @Override
    public Boolean deleteTagPerson(String token, String tagId, List<String> userList, List<Integer> partyList) {
        Boolean result = true;
        JSONObject object = new JSONObject();
        object.put("tagid", tagId);
        if (!userList.isEmpty()) {
            object.put("userlist", userList);
        }

        if (!partyList.isEmpty()) {
            object.put("partylist", partyList);
        }
        String param = object.toJSONString();
        String url = DELETE_TAG_PERSON.replace("ACCESS_TOKEN", URLEncoder.encode(token));
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
     * @param :
     * @return
     * @author : fengguangliang
     * @description : 获取标签列表
     * @date : 2017/4/25
     */
    @Override
    public List<Tag> getTagList(String token) {
        String url = GET_TAGS.replace("ACCESS_TOKEN", URLEncoder.encode(token));
        List<Tag> tags = null;
        try {
            JSONObject object = JSONObject.parseObject(CommonUtil.doGet(url, null));
            tags = gson.fromJson(object.getJSONArray("taglist").toJSONString(), new TypeToken<List<Tag>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tags;
    }
}
