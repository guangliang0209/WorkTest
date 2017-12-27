package wechat.service;

import wechat.model.Person;

import java.util.List;

/**
 * Created by fengguangliang on 2017/4/25.
 */
public interface ContactsPersonService extends Contacts {

    /**
     * 创建成员url
     */
    String CREATE_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN";
    /**
     * 更新成员url
     */
    String UPDATE_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=ACCESS_TOKEN";
    /**
     * 删滁
     */
    String DELETE_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=ACCESS_TOKEN&userid=USERID";

    /**
     * 批量删除成员url
     */
    String BATCH_DELETE_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token=ACCESS_TOKEN";
    /**
     * 获取成员url
     */
    String GET_PERSON_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";
    /**
     * 获取部门成员url
     */
    String GET_GROUP_PERSON_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD&status=STATUS";

    String GET_GROUP_PERSON_DETAIL_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD&status=STATUS";

    Boolean create(String token, Person person);

    Boolean update(String token, Person person);

    Boolean delete(String token, String userId);

    Boolean groupDelete(String token, List<String> useridlist);

    Person getPerson(String token, String userid);

    List<Person> getGroupPersonDetail(String token, String departmentId, Integer fetchChild, Integer status);

}
