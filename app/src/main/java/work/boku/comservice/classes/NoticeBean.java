package work.boku.comservice.classes;

public class NoticeBean {

    int notice_id;

    String notice_time;

    String notice_title;

    String notice_content;

    public NoticeBean() {
    }

    public NoticeBean(String notice_title, String notice_content) {
        this.notice_title = notice_title;
        this.notice_content = notice_content;
    }

    public NoticeBean(String notice_time, String notice_title, String notice_content) {
        this.notice_time = notice_time;
        this.notice_title = notice_title;
        this.notice_content = notice_content;
    }

    public NoticeBean(int notice_id, String notice_time, String notice_title, String notice_content) {
        this.notice_id = notice_id;
        this.notice_time = notice_time;
        this.notice_title = notice_title;
        this.notice_content = notice_content;
    }

    public int getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(int notice_id) {
        this.notice_id = notice_id;
    }

    public String getNotice_time() {
        return notice_time;
    }

    public void setNotice_time(String notice_time) {
        this.notice_time = notice_time;
    }

    public String getNotice_title() {
        return notice_title;
    }

    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }

    public String getNotice_content() {
        return notice_content;
    }

    public void setNotice_content(String notice_content) {
        this.notice_content = notice_content;
    }
}
