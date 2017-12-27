package wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.reflect.TypeToken;
import wechat.model.Group;
import wechat.service.ContactsGroupService;
import wechat.util.CommonUtil;

import java.net.URLEncoder;
import java.util.List;

/**
 * @author : guangliang
 * @description : 通讯录部门管理类
 * @date : 2017/4/24
 */
public class ContactsGroupServiceImpl implements ContactsGroupService {

    /**
     * @param group 分组实体类
     * @return true成功false失败
     * @description:创建分组
     * @date : 2017/4/24
     */
    @Override
    public Boolean create(String token, Group group) {
        Boolean result = true;
        String param = gson.toJson(group);
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
     * @param group 分组实体类
     * @return json 字符串
     * @description:更新分组
     * @date : 2017/4/24
     */
    @Override
    public Boolean update(String token, Group group) {
        Boolean result = true;
        String param = gson.toJson(group);
        try {
            String url = UPDATE_URL.replace("ACCESS_TOKEN", URLEncoder.encode(token, "utf-8"));
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
     * @param id 部门id
     * @return 拼装参数后的删除地址
     * @description:删除部门
     * @date : 2017/4/24
     */
    @Override
    public Boolean delete(String token, String id) {
        Boolean result = true;
        try {
            String deleteUrl = DELETE_URL.replace("ACCESS_TOKEN", URLEncoder.encode(token, "utf-8")).replace("ID", id);
            JSONObject object = JSONObject.parseObject(CommonUtil.doGet(deleteUrl, null));
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
     * @param id 非必填，分组id，参数不能为负数
     * @return 部门列表
     * @author : fengguangliang
     * @description : 获取部门列表
     * @date : 2017/4/24
     */
    @Override
    public List<Group> getGroupList(String token, Integer id) {
        List<Group> groups = null;
        try {
            String url = GETLIST_URL.replace("ACCESS_TOKEN", URLEncoder.encode(token, "utf-8"));
            if (id != null) {
                url += "&id=" + id.toString();
            }
            JSONObject jsonObject = JSONObject.parseObject(CommonUtil.doPost(url, null));
            groups = gson.fromJson(jsonObject.getJSONArray("department").toJSONString(), new TypeToken<List<Group>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groups;
    }
}
