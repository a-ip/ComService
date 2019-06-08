package work.boku.comservice.classes;

public class EventBean {

    private int event_id;

    private String event_name; // 活动名称

    private String event_detail; // 活动内容

    private String event_date; // 活动日期

    private int organizer; // 活动组织者

    private int max_people; // 活动最大人数

    public EventBean() {
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_detail() {
        return event_detail;
    }

    public void setEvent_detail(String event_detail) {
        this.event_detail = event_detail;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_time) {
        this.event_date = event_date;
    }

    public int getOrganizer() {
        return organizer;
    }

    public void setOrganizer(int organizer) {
        this.organizer = organizer;
    }

    public int getMax_people() {
        return max_people;
    }

    public void setMax_people(int max_people) {
        this.max_people = max_people;
    }
}
