package wechat.model;

/**
 * Created by fengguangliang on 2017/4/25.
 */
public class Tag {

    /**
     * 标签名称，长度限制为32个字（汉字或英文字母），标签名不可与其他标签重名。
     */
    private String tagname;

    /**
     * 标签id，整型，指定此参数时新增的标签会生成对应的标签id，不指定时则以目前最大的id自增。
     */
    private Integer tagid;

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public Integer getTagid() {
        return tagid;
    }

    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }
}
