package wechat.model;

/**
 * Created by fengguangliang on 2017/4/25.
 */
public class TextMessage extends Message {

    /**
     * text消息体
     */
    private Content text;

    public static class Content {
        /**
         * 是	消息内容，最长不超过2048个字节，注意：主页型应用推送的文本消息在微信端最多只显示20个字（包含中英文）
         */
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }


    public Content getText() {
        return text;
    }

    public void setText(Content text) {
        this.text = text;
    }
}
