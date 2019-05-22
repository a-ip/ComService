package work.boku.comservice.classes;

import work.boku.comservice.activities.BaseActivity;

public class ResidentBean extends BaseActivity {

    private static String TAG = "ResidentBean";

    private String community_id;// 社区ID

    private String identity_number;// 身份证号

    private String name;// 姓名

    private String phone_number;// 手机号码


    public ResidentBean() {
    }

    public String getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(String community_id) {
        this.community_id = community_id;
    }

    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

}
