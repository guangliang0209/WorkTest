package wechat.service;

import wechat.model.Group;

import java.util.List;

/**
 * Created by fengguangliang on 2017/4/25.
 */
public interface ContactsGroupService extends Contacts {

    /**
     * 创建分组url
     */
    String CREATE_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=ACCESS_TOKEN";
    /**
     * 更新分组url
     */
    String UPDATE_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=ACCESS_TOKEN";
    /**
     * 删除分组url
     */
    String DELETE_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=ACCESS_TOKEN&id=ID";
    /**
     * 获取分组列表url
     */
    String GETLIST_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN";

    Boolean create(String token, Group group);

    Boolean update(String token, Group group);

    Boolean delete(String token, String id);

    List<Group> getGroupList(String token, Integer id);
}
