package wechat.model;

/**
 * Created by fengguangliang on 2017/4/25.
 */
public class FileMessage extends Message {
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public static class File {

        /**
         * 是	媒体文件id，可以调用上传临时素材或者永久素材接口获取
         */
        private String media_id;

        public String getMedia_id() {
            return media_id;
        }

        public void setMedia_id(String media_id) {
            this.media_id = media_id;
        }
    }
}
