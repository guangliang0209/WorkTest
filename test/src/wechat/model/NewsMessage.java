package wechat.model;

import java.util.List;

/**
 * Created by fengguangliang on 2017/4/25.
 */
public class NewsMessage extends Message {

    /**
     * 消息内容
     */
    private Articles news;

    public Articles getNews() {
        return news;
    }

    public void setNews(Articles news) {
        this.news = news;
    }

    public static class Articles {
        private List<Article> articles;

        public List<Article> getArticles() {
            return articles;
        }

        public void setArticles(List<Article> articles) {
            this.articles = articles;
        }
    }

    public static class Article {

        /**
         * 否	标题，不超过128个字节，超过会自动截断
         */
        private String title;

        /**
         * 否	描述，不超过512个字节，超过会自动截断
         */
        private String description;

        /**
         * 否	点击后跳转的链接。
         */
        private String url;

        /**
         * 否	图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80。如不填，在客户端不显示图片
         */
        private String picurl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }
    }
}
