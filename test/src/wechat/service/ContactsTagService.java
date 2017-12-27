package wechat.service;

import wechat.model.Person;
import wechat.model.Tag;

import java.util.List;

/**
 * Created by fengguangliang on 2017/4/25.
 */
public interface ContactsTagService extends Contacts {

    String CREATE_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/create?access_token=ACCESS_TOKEN";

    String UPDATE_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/update?access_token=ACCESS_TOKEN";

    String DELETE_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/delete?access_token=ACCESS_TOKEN&tagid=TAGID";

    String GET_TAG_PERSON = "https://qyapi.weixin.qq.com/cgi-bin/tag/get?access_token=ACCESS_TOKEN&tagid=TAGID";

    String ADD_TAG_PERSON = "https://qyapi.weixin.qq.com/cgi-bin/tag/addtagusers?access_token=ACCESS_TOKEN";

    String DELETE_TAG_PERSON = "https://qyapi.weixin.qq.com/cgi-bin/tag/deltagusers?access_token=ACCESS_TOKEN";

    String GET_TAGS = "https://qyapi.weixin.qq.com/cgi-bin/tag/list?access_token=ACCESS_TOKEN";

    Boolean create(String token, Tag tag);

    Boolean update(String token, Tag tag);

    Boolean delete(String token, String tagId);

    List<Person> getTagPerson(String toekn, String tagId);

    Boolean addTagPerson(String token, String tagId, List<String> userList, List<Integer> partyList);

    Boolean deleteTagPerson(String token, String tagId, List<String> userList, List<Integer> partyList);

    List<Tag> getTagList(String token);
}
