package wechat.model;

/**
 * Created by fengguangliang on 2017/4/25.
 */
public class VoiceMessage extends Message {

    private Voice voice;

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    public static class Voice {

        /**
         * 是	企业应用的id，整型。可在应用的设置页面查看
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
