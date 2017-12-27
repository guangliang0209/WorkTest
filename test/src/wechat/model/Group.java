package wechat.model;

/**
 * @author : fengguangliang
 * @description : 分组实体类
 * @date : 2017/4/24
 */
public class Group {
    /**
     * 必须，部门名称。长度限制为32个字（汉字或英文字母），字符不能包括\:*?"<>｜
     */
    private String name;
    /**
     * 必须,父亲部门id。根部门id为1
     */
    private Integer parentid;
    /**
     * 非必须，在父部门中的次序值。order值小的排序靠前。
     */
    private Integer order;
    /**
     * 非必须，部门id，整型。指定时必须大于1，不指定时则自动生成
     */
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
